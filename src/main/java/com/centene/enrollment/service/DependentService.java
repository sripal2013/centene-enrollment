package com.centene.enrollment.service;

import java.util.Optional;

import com.centene.enrollment.model.Dependent;
import com.centene.enrollment.model.Enrollee;

/**
 * @Description: Interface for Dependent Service
 * @author Srikanth Palutla
 *
 */
public interface DependentService {
	
	/**
	 * Get dependent details based on Id
	 * @param dependentId
	 * @return Dependent
	 */
	Optional<Dependent> getDependent(Long dependentId);
	
	/**
	 * Create dependent details based on enrolleeId
	 * @param enrollee
	 * @param dependent
	 * @return Dependent
	 */
	Dependent createDependent(Enrollee enrollee, Dependent dependent);
	
	/**
	 * Update dependent details based on enrolleeId
	 * @param id
	 * @param dependent
	 * @return Dependent
	 */
	Dependent updateDependent(Dependent dependent);
	
	/**
	 * Delete particular dependents details under enrolleeId
	 * @param dependentId
	 */
	void deleteDependent(Long dependentId);
}
