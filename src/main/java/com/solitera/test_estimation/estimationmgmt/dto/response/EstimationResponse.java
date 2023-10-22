package com.solitera.test_estimation.estimationmgmt.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EstimationResponse {

	private String estimationName;
	private String ideaPhase;
	private String inceptionPhase;
	private String developementPhase;
	
	public String getEstimationName() {
		return estimationName;
	}
	public void setEstimationName(String estimationName) {
		this.estimationName = estimationName;
	}
	public String getIdeaPhase() {
		return ideaPhase;
	}
	public void setIdeaPhase(String ideaPhase) {
		this.ideaPhase = ideaPhase;
	}
	public String getInceptionPhase() {
		return inceptionPhase;
	}
	public void setInceptionPhase(String inceptionPhase) {
		this.inceptionPhase = inceptionPhase;
	}
	public String getDevelopementPhase() {
		return developementPhase;
	}
	public void setDevelopementPhase(String developementPhase) {
		this.developementPhase = developementPhase;
	}
}
