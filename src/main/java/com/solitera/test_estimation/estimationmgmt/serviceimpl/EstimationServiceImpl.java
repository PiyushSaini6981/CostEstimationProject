package com.solitera.test_estimation.estimationmgmt.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.itextpdf.text.DocumentException;
import com.solitera.test_estimation.estimationmgmt.dto.request.CalculateEstimationDto;
import com.solitera.test_estimation.estimationmgmt.dto.request.EstimationDto;
import com.solitera.test_estimation.estimationmgmt.dto.request.RenameEstimationDto;
import com.solitera.test_estimation.estimationmgmt.dto.request.SendEstimationDto;
import com.solitera.test_estimation.estimationmgmt.dto.response.CalculatedCost;
import com.solitera.test_estimation.estimationmgmt.dto.response.EstimationResponse;
import com.solitera.test_estimation.estimationmgmt.model.EstimationModel;
import com.solitera.test_estimation.estimationmgmt.repository.EstimationRepository;
import com.solitera.test_estimation.estimationmgmt.service.EstimationService;
import com.solitera.test_estimation.estimationmgmt.utils.authentication.EstimationAuthentication;
import com.solitera.test_estimation.estimationmgmt.utils.estimationcalculation.EstimationCalculation;
import com.solitera.test_estimation.usermgmt.dto.request.MyuserDetails;
import com.solitera.test_estimation.usermgmt.exception.AlreadyExistException;
import com.solitera.test_estimation.usermgmt.exception.BadRequestException;
import com.solitera.test_estimation.usermgmt.exception.InvalidRequestException;
import com.solitera.test_estimation.usermgmt.exception.NotFoundException;
import com.solitera.test_estimation.usermgmt.repository.UserRepository;
import com.solitera.test_estimation.usermgmt.utils.mail.Emailsender;


@Service
public class EstimationServiceImpl implements EstimationService{
	
	@Autowired
	private EstimationRepository repository;
	
	@Autowired
	private UserRepository userReposiotry;

	@Autowired
	private EstimationAuthentication authentication;
	
	@Autowired
	private EstimationCalculation calculation;
	
	@Autowired
	private Emailsender mailSender;

	
	public List<EstimationResponse> getAllEstimation() throws JsonMappingException, JsonProcessingException {
	    MyuserDetails userDetails = authentication.authenticate();
	    List<EstimationModel> estimations = repository.findAllByCreatedBy(userDetails.getId());
	    List<EstimationResponse> response = new ArrayList<>();
	    for (EstimationModel model : estimations) {
	        EstimationResponse estimationresponse = new EstimationResponse();
	        String json = model.getIdeaPhase();
	        ObjectMapper object = new ObjectMapper();
	        estimationresponse.setEstimationName(model.getEstimationName());
	        if (json != null) {
	            Map<String, Object> jsonMap = object.readValue(json, new TypeReference<Map<String, Object>>(){});
	            estimationresponse.setIdeaPhase(Double.toString((Double)jsonMap.get("projectTestEstimate")));
	        }
	        json = model.getDevelopementPhase();
	        if (json != null) {
	            Map<String, Object> jsonMap = object.readValue(json, new TypeReference<Map<String, Object>>(){});
	            estimationresponse.setDevelopementPhase(Double.toString((Double)jsonMap.get("projectTestEstimate")));
	        }
	        json = model.getInceptionPhase();
	        if (json != null) {
	            Map<String, Object> jsonMap = object.readValue(json, new TypeReference<Map<String, Object>>(){});
	            estimationresponse.setInceptionPhase(Double.toString((Double)jsonMap.get("projectTestEstimate")));
	        }
	        response.add(estimationresponse);
	    }
	    return response;
	}


	@Override
	public void saveEstimation(EstimationDto estimationDto) {
	    MyuserDetails userDetails = authentication.authenticate();
	    Optional<EstimationModel> estimation = repository.findByCreatedByAndEstimationName(userDetails.getId(), estimationDto.getEstimationName());
	    Integer count = repository.countByCreatedBy(userDetails.getId());
	    if (estimation.isPresent()) {
	        throw new AlreadyExistException("EstimationName Already Registered");
	    } else {
	        if (count != null && count >= 10) { 
	            throw new NotFoundException("Unable to create more estimations");
	        } else {
	            EstimationModel model = new EstimationModel();
	            model.setEstimationName(estimationDto.getEstimationName());
	            model.setCreatedAt(new java.sql.Timestamp(System.currentTimeMillis()));
	            model.setCreatedBy(userReposiotry.findById(userDetails.getId()).get());
	            repository.save(model);
	        }
	    }
	}
	
	@Override
	public void renameEstimationName(String estimationName ,RenameEstimationDto updateEstimation) {
	    MyuserDetails userDetails = authentication.authenticate();
		Optional<EstimationModel> findEstimationName = repository.findByCreatedByAndEstimationName(userDetails.getId(),estimationName);
		if(findEstimationName.isPresent()) {
			Optional<EstimationModel> findEstimation = repository.findByCreatedByAndEstimationName(userDetails.getId(),updateEstimation.getEstimationName());
			if(findEstimation.isPresent()) {
		        throw new AlreadyExistException("EstimationName Already Registered");
			}else {
			EstimationModel model = findEstimationName.get();
			model.setEstimationName(updateEstimation.getEstimationName());
			repository.save(model);
			}
		}else {
			throw new NotFoundException("Estimation name not found");
		}		
	}
	
	@Override
	public CalculatedCost calculateAllPhase(String estimationName,CalculateEstimationDto calculateEstimationDto) {
		MyuserDetails userDetails = authentication.authenticate();
		Optional<EstimationModel> findEstimation = repository.findByCreatedByAndEstimationName(userDetails.getId(),estimationName);
		CalculatedCost calculate = null;
		if(findEstimation.isPresent()) {
			EstimationModel model = findEstimation.get();
			if(calculateEstimationDto.getEstimationType().equals("ideaPhase")) {
				calculate = calculation.ideaPhase(calculateEstimationDto.getRequirementDetailsDto());
				model.setIdeaPhase(new Gson().toJson(calculate));
			}else if(calculateEstimationDto.getEstimationType().equals("developmentPhase")) {
				calculate = calculation.developmentPhase(calculateEstimationDto.getRequirementDetailsDto());
				model.setDevelopementPhase(new Gson().toJson(calculate));
			}else if(calculateEstimationDto.getEstimationType().equals("inceptionPhase")) {
				calculate = calculation.inceptionPhase(calculateEstimationDto.getRequirementDetailsDto());
				model.setInceptionPhase(new Gson().toJson(calculate));
			}else {
				throw new InvalidRequestException("Invalid EstimationPhase Name");
			}
			repository.save(model);
		}else {
			throw new NotFoundException("Estimation name not found");
		}
		return calculate;
	}
	
	@Override
	public EstimationResponse getEstimationDetails(String estimationName) throws JsonMappingException, JsonProcessingException {
	    MyuserDetails userDetails = authentication.authenticate();
		Optional<EstimationModel> findEstimationName = repository.findByCreatedByAndEstimationName(userDetails.getId(), estimationName);
	    EstimationResponse response = new EstimationResponse();
	    if(findEstimationName.isPresent()) {
	        EstimationModel model = findEstimationName.get();
	        String json = model.getIdeaPhase();
	        ObjectMapper object = new ObjectMapper();
	        response.setEstimationName(model.getEstimationName());
	        if(json != null) {
		        Map<String, Object> jsonMap = object.readValue(json, new TypeReference<Map<String, Object>>(){});
		        response.setIdeaPhase(Double.toString((Double)jsonMap.get("projectTestEstimate")));
	        }
	        json = model.getDevelopementPhase();
	        if(json != null) {
	            Map<String, Object> jsonMap = object.readValue(json, new TypeReference<Map<String, Object>>(){});
	            response.setDevelopementPhase(Double.toString((Double)jsonMap.get("projectTestEstimate")));	
	        }
	        json = model.getInceptionPhase();
	        if(json != null) {
		        Map<String, Object> jsonMap = object.readValue(json, new TypeReference<Map<String, Object>>(){});
		        response.setInceptionPhase(Double.toString((Double)jsonMap.get("projectTestEstimate")));
	        }
	        
	    }else {
			throw new NotFoundException("Estimation name not found");
	    }
	    return response;
	}

	@Override
	public void sendEstimationDetails(SendEstimationDto sendEstimationDto) throws DocumentException, MessagingException {
		MyuserDetails userDetails = authentication.authenticate();
		Optional<EstimationModel> findEstimationName = repository.findByCreatedByAndEstimationName(userDetails.getId(),sendEstimationDto.getEstimationName());
		if(findEstimationName.isPresent()) {
			EstimationModel model = findEstimationName.get();
			if( sendEstimationDto.getEstimationPhaseName().equals("ideaPhase")) {
				if(model.getIdeaPhase() != null) {
					mailSender.sendEstimationDetailsToMail(userDetails.getUsername(), model.getIdeaPhase());
				}else {
					throw new BadRequestException("Estimation data is required");
				}
			}else if( sendEstimationDto.getEstimationPhaseName().equals("inceptionPhase")) {
				if(model.getInceptionPhase() != null) {
					mailSender.sendEstimationDetailsToMail(userDetails.getUsername(), model.getInceptionPhase());
				}else {
					throw new BadRequestException("Estimation data is required");
				}
			}else if( sendEstimationDto.getEstimationPhaseName().equals("developmentPhase")) {
				if(model.getDevelopementPhase() != null){
					mailSender.sendEstimationDetailsToMail(userDetails.getUsername(), model.getDevelopementPhase());
				}else {
					throw new BadRequestException("Estimation data is required");
				}
			}else {
				throw new InvalidRequestException("Invalid Estimation Phase Name");
			}
		}else {
			throw new NotFoundException("Estimation name not found");
		}
	}

	@Override
	public void deleteEstimation(String estimationName) {
	    MyuserDetails userDetails = authentication.authenticate();
		Optional<EstimationModel> findEstimationName = repository.findByCreatedByAndEstimationName(userDetails.getId(), estimationName);
		if(findEstimationName.isPresent()) {
			repository.delete(findEstimationName.get());
		}else {
			throw new NotFoundException("Estimation name not found");
		}
	}
	
}





