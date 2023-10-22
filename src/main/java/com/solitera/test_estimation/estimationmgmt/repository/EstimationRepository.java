package com.solitera.test_estimation.estimationmgmt.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.solitera.test_estimation.estimationmgmt.model.EstimationModel;

@Repository
public interface EstimationRepository extends JpaRepository<EstimationModel, Integer>{
	
	@Query("SELECT c FROM EstimationModel c WHERE c.createdBy = (SELECT u FROM UserModel u where u.id = :id) AND c.estimationName = :estimationName")
	Optional<EstimationModel> findByCreatedByAndEstimationName(Integer id, String estimationName);
	
	@Query("SELECT c FROM EstimationModel c WHERE c.createdBy = (SELECT u FROM UserModel u where u.id = :id) AND c.id = :estimationId")
	Optional<EstimationModel> findByEstimationId(Integer id, Integer estimationId);
	
	@Query("SELECT c FROM EstimationModel c WHERE c.createdBy = (SELECT u FROM UserModel u where u.id = :id)")
	Optional<EstimationModel> findByCreatedById(Integer id);
	
	@Query("SELECT c FROM EstimationModel c WHERE c.createdBy = (SELECT u FROM UserModel u where u.id = :id)")
	List<EstimationModel> findAllByCreatedBy(Integer id);
	
	@Query("SELECT COUNT(c) FROM EstimationModel c WHERE c.createdBy.id = :id")
	Integer countByCreatedBy(Integer id);

	@Query("SELECT COUNT(c) FROM EstimationModel c WHERE c.estimationName = :estimationName")
	Optional<EstimationModel> findByEstimationName(String estimationName);
	
}
