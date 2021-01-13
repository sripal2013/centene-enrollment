package com.centene.enrollment.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.centene.enrollment.exceptions.EnrolleeNotFoundException;
import com.centene.enrollment.model.Dependent;
import com.centene.enrollment.model.Enrollee;
import com.centene.enrollment.repository.EnrolleeRepository;
import com.centene.enrollment.service.EnrolleeService;
/**
 * @Description:  Enrollee Service layer to interact with repository to handle business logic
 * @author Srikanth Palutla
 *
 */
@Service
public class EnrolleeServiceImpl implements EnrolleeService {
	
	@Autowired
	EnrolleeRepository enrolleeRepository;
	
	@Override
	public List<Enrollee> getEnrollees() {
		List<Enrollee> enrolleeList = new ArrayList<>();
    	Iterable<Enrollee> enrollees= enrolleeRepository.findAll();
    	enrollees.forEach(enrolleeList::add);
    	
    	return enrolleeList;
	}

	@Override
	public Optional<Enrollee> getEnrolleeById(Long id) {
		Optional<Enrollee> enrollee = enrolleeRepository.findById(id);
		if(!enrollee.isPresent()) {
        	throw new EnrolleeNotFoundException("No Enrollee Record Found");
        }
		return enrollee;
	}

	@Override
	public Enrollee createEnrollee(Enrollee enrollee) {
		return enrolleeRepository.save(enrollee);
	}

	@Override
	public Enrollee updateEnrollee(Enrollee enrollee) {
		Optional<Enrollee> record = enrolleeRepository.findById(enrollee.getId());
		if(!record.isPresent()) {
        	throw new EnrolleeNotFoundException("No Enrollee Record Found");
        }
		Enrollee existedRecord = record.get();
		if(Objects.nonNull(enrollee.getName())){
			existedRecord.setName(enrollee.getName());
		}
		existedRecord.setActivationStatus(enrollee.isActivationStatus());
		if(Objects.nonNull(enrollee.getDateOfBirth())){
			existedRecord.setDateOfBirth(enrollee.getDateOfBirth());
		}
		if(Objects.nonNull(enrollee.getPhoneNumber())){
			existedRecord.setPhoneNumber(enrollee.getPhoneNumber());
		}
		return enrolleeRepository.save(existedRecord);
	}

	@Override
	public void deleteEnrollee(Long id) {
		enrolleeRepository.deleteById(id);
	}
	
	@Override
	public List<Dependent> getDependentsByEnrolleeId(Long id) {
		Optional<Enrollee> enrollee = enrolleeRepository.findById(id);
		if(!enrollee.isPresent()) {
        	throw new EnrolleeNotFoundException("No Enrollee Record Found");
        }
		return enrollee.get().getDependents();
	}


}
