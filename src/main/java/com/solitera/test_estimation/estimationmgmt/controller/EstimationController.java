package com.solitera.test_estimation.estimationmgmt.controller;

import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.itextpdf.text.DocumentException;
import com.solitera.test_estimation.estimationmgmt.dto.request.CalculateEstimationDto;
import com.solitera.test_estimation.estimationmgmt.dto.request.EstimationDto;
import com.solitera.test_estimation.estimationmgmt.dto.request.RenameEstimationDto;
import com.solitera.test_estimation.estimationmgmt.dto.request.SendEstimationDto;
import com.solitera.test_estimation.estimationmgmt.dto.response.CalculatedCost;
import com.solitera.test_estimation.estimationmgmt.dto.response.EstimationResponse;
import com.solitera.test_estimation.estimationmgmt.service.EstimationService;
import com.solitera.test_estimation.estimationmgmt.validators.globalvalidators.EstimationGlobalValidator;
import com.solitera.test_estimation.usermgmt.dto.response.MessageResponse;
import com.solitera.test_estimation.usermgmt.utils.UtilsFunc;

@RestController
@RequestMapping("/test_estimation")
public class EstimationController {
	
	@Autowired
	EstimationService estimationService;
	
	@Autowired
	private EstimationGlobalValidator handleValidation;
	
	@Autowired 
	private UtilsFunc func;
	
	@GetMapping
	@CrossOrigin(origins = "http://localhost:3000")
	private ResponseEntity< List<EstimationResponse>> getAllEstimationList() throws JsonMappingException, JsonProcessingException{
		 List<EstimationResponse> response = this.estimationService.getAllEstimation();
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PostMapping("/createEstimation")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<MessageResponse> saveEstimation(@RequestBody EstimationDto estimationDto,BindingResult bres){
		handleValidation.validate(estimationDto, bres);
		func.checkBindingResultError(bres);
		this.estimationService.saveEstimation(estimationDto);
		return new ResponseEntity<MessageResponse>(new MessageResponse("Estimation has been created"),HttpStatus.CREATED);
	}
	
	@PutMapping("/renameEstimationName/{estimationName}")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<MessageResponse> updateEstimation(@PathVariable String estimationName ,@RequestBody RenameEstimationDto updateEstimationDto ,BindingResult bres){
		handleValidation.validate(updateEstimationDto, bres);
		func.checkBindingResultError(bres);
		this.estimationService.renameEstimationName(estimationName,updateEstimationDto);
		return new ResponseEntity<MessageResponse>(new MessageResponse("EstimationName has been updated"),HttpStatus.OK);
	}
	
	@GetMapping("/estimationDetails/{estimationName}")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<EstimationResponse> SingleEstimationResponse(@PathVariable String estimationName) throws JsonMappingException, JsonProcessingException{
		EstimationResponse response =  this.estimationService.getEstimationDetails(estimationName);
		return new ResponseEntity<EstimationResponse>(new EstimationResponse(response.getEstimationName(),response.getIdeaPhase(),response.getInceptionPhase(),response.getDevelopementPhase()
				),HttpStatus.OK);
	}

	@PostMapping("/phase/{estimationName}")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<CalculatedCost> calculateEstimationData(@PathVariable String estimationName,@RequestBody CalculateEstimationDto calculateEstimationDto){
		CalculatedCost response = this.estimationService.calculateAllPhase(estimationName, calculateEstimationDto);
		return new ResponseEntity<CalculatedCost>(response,HttpStatus.OK);

	}
	
	@DeleteMapping("/estimation/{estimationName}")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<MessageResponse> deleteEstimation(@PathVariable String estimationName){
		this.estimationService.deleteEstimation(estimationName);
		return new ResponseEntity<MessageResponse>(new MessageResponse("Estimation deleted successfully"),HttpStatus.OK);
	}
	
	@PostMapping("/sendEstimation")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<MessageResponse> sendEstimationData(@RequestBody SendEstimationDto sendEstimationDto)throws DocumentException, MessagingException{
		this.estimationService.sendEstimationDetails(sendEstimationDto);
		return new ResponseEntity<MessageResponse>(new MessageResponse("Data sent to registered mail"),HttpStatus.OK);
	}
	
}

