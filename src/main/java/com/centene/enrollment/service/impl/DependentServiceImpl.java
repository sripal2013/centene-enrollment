package com.centene.enrollment.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.centene.enrollment.exceptions.EnrolleeNotFoundException;
import com.centene.enrollment.model.Dependent;
import com.centene.enrollment.model.Enrollee;
import com.centene.enrollment.repository.DependentRepository;
import com.centene.enrollment.service.DependentService;

@Service

/**
 * @author Srikanth Palutla
 *
 */
public class DependentServiceImpl implements DependentService {

	@Autowired
	DependentRepository dependentRepository;

	/**
	 * Get dependent details based on Id
	 *
	 * @param dependentId
	 * @return Dependent
	 */

	@Override
	public Optional<Dependent> getDependent(Long dependentId) {
		Optional<Dependent> dependent = dependentRepository.findById(dependentId);
		if (!dependent.isPresent()) {
			throw new EnrolleeNotFoundException("No Dependent Record Found");
		}

		return dependent;
	}

	/**
	 * Create dependent details based on enrolleeId
	 *
	 * @param enrollee
	 * @param dependent
	 * @return Dependent
	 */

	@Override
	public Dependent createDependent(Enrollee enrollee, Dependent dependent) {
		dependent.setEnrollee(enrollee);
		return dependentRepository.save(dependent);
	}

	/**
	 * Update dependent details based on enrolleeId
	 *
	 * @param id
	 * @param dependent
	 * @return Dependent
	 */

	@Override
	public Dependent updateDependent(Dependent dependent) {
		Optional<Dependent> existdependent = dependentRepository.findById(dependent.getId());
		if (!existdependent.isPresent()) {
			throw new EnrolleeNotFoundException("No Dependent Record Found");
		}
		existdependent.get().setDateOfBirth(dependent.getDateOfBirth());
		existdependent.get().setName(dependent.getName());
		return dependentRepository.save(existdependent.get());
	}

	/**
	 * Delete particular dependents details under enrolleeId
	 *
	 * @param dependentId
	 */

	@Override
	public void deleteDependent(Long dependentId) {
		Optional<Dependent> dependent = dependentRepository.findById(dependentId);
		if (!dependent.isPresent()) {
			throw new EnrolleeNotFoundException("No Dependent Record Found");
		}
		dependentRepository.deleteById(dependentId);
	}

}
