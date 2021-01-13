package com.centene.enrollment.service;

import java.util.List;
import java.util.Optional;

import com.centene.enrollment.model.Dependent;
import com.centene.enrollment.model.Enrollee;
/**
 * @Description: Interface for Enrollee Service
 * @author Srikanth Palutla
 *
 */
public interface EnrolleeService {
	
	/**
	 * Get list of all enrollees
	 * @return List<Enrollee>
	 */
	List<Enrollee> getEnrollees();
	
	/**
	 * Get enrollee details for particular Id
	 * @param id
	 * @return Enrollee
	 */
	Optional<Enrollee> getEnrolleeById(Long id);
	
	/**
	 * Create enrollee details
	 * @param enrollee
	 * @return Enrollee
	 */
	Enrollee createEnrollee(Enrollee enrollee);
	
	/**
	 * Update enrollee details
	 * @param enrollee
	 * @return
	 */
	Enrollee updateEnrollee(Enrollee enrollee);
	
	/**
	 * Delete enrollee details
	 * @param id
	 */
	void deleteEnrollee(Long id);
	
	/**
	 * Get list of all dependents under particular enrollee id
	 * @param id
	 * @return List<Dependent>
	 */
	List<Dependent> getDependentsByEnrolleeId(Long id);
}


