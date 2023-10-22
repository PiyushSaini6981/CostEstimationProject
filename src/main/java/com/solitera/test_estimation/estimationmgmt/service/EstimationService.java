package com.solitera.test_estimation.estimationmgmt.service;


import java.util.List;

import javax.mail.MessagingException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.itextpdf.text.DocumentException;
import com.solitera.test_estimation.estimationmgmt.dto.request.CalculateEstimationDto;
import com.solitera.test_estimation.estimationmgmt.dto.request.EstimationDto;
import com.solitera.test_estimation.estimationmgmt.dto.request.RenameEstimationDto;
import com.solitera.test_estimation.estimationmgmt.dto.request.SendEstimationDto;
import com.solitera.test_estimation.estimationmgmt.dto.response.CalculatedCost;
import com.solitera.test_estimation.estimationmgmt.dto.response.EstimationResponse;

public interface EstimationService {

	public  List<EstimationResponse> getAllEstimation()throws JsonMappingException, JsonProcessingException;
	public void saveEstimation(EstimationDto estimationDto);
	public void renameEstimationName(String estimationName ,RenameEstimationDto updateEstimation);
	public void deleteEstimation(String estimationName);
	public CalculatedCost calculateAllPhase(String estimationName ,CalculateEstimationDto calculateEstimationDto);
	public EstimationResponse getEstimationDetails(String estimationName) throws JsonMappingException, JsonProcessingException;
	public void sendEstimationDetails(SendEstimationDto sendEstimationDto)throws DocumentException, MessagingException;
	
}
