package com.solitera.test_estimation.usermgmt.repository;

import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.solitera.test_estimation.usermgmt.model.CountryModel;


@Repository
public interface CountryRepository extends JpaRepository<CountryModel, Integer>{

	@Query("SELECT C FROM CountryModel C WHERE C.countryName = :country")
	Optional<CountryModel> findByCountryName(String country);
}
