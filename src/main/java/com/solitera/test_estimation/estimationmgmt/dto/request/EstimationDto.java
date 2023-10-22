package com.solitera.test_estimation.estimationmgmt.dto.request;

import java.sql.Timestamp;

public class EstimationDto {

	private String estimationName;
	private String ideaPhase;
	private String inceptionPhase;
	private String developementPhase;
	private Timestamp createdAt;
	
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
	public Timestamp getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
}
