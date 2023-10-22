package com.solitera.test_estimation.estimationmgmt.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.solitera.test_estimation.usermgmt.model.UserModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table
public class EstimationModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Integer id;
	@Column
	private String estimationName;
	@Lob
	@Column
	private String ideaPhase;
	@Lob
	@Column
	private String inceptionPhase;
	@Lob
	@Column
	private String developementPhase;
	@JoinColumn(name = "created_by" , referencedColumnName = "id")
	@ManyToOne
	private UserModel createdBy;
	@Column
	private Timestamp createdAt;
	

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
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
	public UserModel getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(UserModel createdBy) {
		this.createdBy = createdBy;
	}
}
