package com.centene.enrollment.controller;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.centene.enrollment.model.Dependent;
import com.centene.enrollment.model.Enrollee;
import com.centene.enrollment.service.DependentService;
import com.centene.enrollment.service.EnrolleeService;

/**
 * @Description: Enrollment Controller with all end points mappings
 * @author Srikanth Palutla
 *
 */
@RestController
@RequestMapping("/enrollment")
public class EnrollmentController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	EnrolleeService enrolleeService;

	@Autowired
	DependentService dependentService;

	/**
	 * End point to Get all enrollees
	 * @return List<Enrollee>
	 */
	@GetMapping("all")
    public List<Enrollee> getEnrollees() {
		logger.info("Started getEnrollees ");
    	return enrolleeService.getEnrollees();
    }

	/**
	 * End Point to Get Enrollee based Id
	 * @param id
	 * @return Enrollee
	 */
	@GetMapping("/{id}")
    public ResponseEntity<Enrollee> getEnrollee(@PathVariable("id") Long id) {
		Optional<Enrollee> enrollee = enrolleeService.getEnrolleeById(id);
        if(enrollee.isPresent()) {
        	logger.info("Enrolle Details : " , enrollee.get());
        }
        return new ResponseEntity<Enrollee>(enrollee.get(), HttpStatus.OK);
	}

	/**
	 * End Point to Create Enrollee
	 * @param enrollee
	 * @return
	 */
	@PostMapping("create")
    public ResponseEntity<Enrollee> createEnrollee(@RequestBody Enrollee enrollee) {
        return new ResponseEntity<Enrollee>(enrolleeService.createEnrollee(enrollee), HttpStatus.CREATED);
    }

	/**
	 * End point to update enrollee
	 * @param enrollee
	 * @return
	 */
	@PutMapping("update")
    public ResponseEntity<Enrollee> updateEnrollee(@RequestBody Enrollee enrollee) {
		if(Objects.isNull(enrollee.getId())){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
        return new ResponseEntity<Enrollee>(enrolleeService.updateEnrollee(enrollee), HttpStatus.OK);
    }

	/**
	 * End point to delete enrollee
	 * @param id
	 */
	@DeleteMapping("{id}")
	public void deleteEnrollee(@PathVariable("id") Long id) {
		 enrolleeService.deleteEnrollee(id);
	}

	/**
	 * End point to get all dependents based on enrollee Id
	 * @param enrolleeId
	 * @return
	 */
	@GetMapping("{id}/dependents")
    public List<Dependent> getDependents(@PathVariable("id") Long enrolleeId) {
        return enrolleeService.getDependentsByEnrolleeId(enrolleeId);
    }

	/**
	 * End Point to create dependent based on enrollee id
	 * @param enrolleeId
	 * @param dependent
	 * @return
	 */
	@PostMapping("{id}/dependent")
    public ResponseEntity<Dependent> createDependent(@PathVariable("id") Long enrolleeId, @RequestBody Dependent dependent) {
		Dependent response = null;
		//verify enrollee exists before adding dependent - enrollee service will throw record not found if doesn't exist
		Optional<Enrollee> enrollee = enrolleeService.getEnrolleeById(enrolleeId);
		if(enrollee.isPresent()){
			response = dependentService.createDependent(enrollee.get(), dependent);
		}
		return new ResponseEntity<Dependent>(response, HttpStatus.CREATED);
    }

	/**
	 * End point for update depedent
	 * @param id
	 * @param dependent
	 * @return
	 */
	@PutMapping("{id}/dependent")
    public ResponseEntity<Dependent> updateDependent(@PathVariable("id") Long id, @RequestBody Dependent dependent){
		//verify enrollee exists before adding dependent - enrollee service will throw record not found if doesn't exist
		Dependent response = null;
		Optional<Enrollee> enrollee = enrolleeService.getEnrolleeById(id);
		if(enrollee.isPresent()){
			response = dependentService.updateDependent(dependent);
		}
		return new ResponseEntity<Dependent>(response, HttpStatus.OK);
    }

	/**
	 * End point for get dependent details based on Id
	 * @param id
	 * @return
	 */
	@GetMapping("dependent/{id}")
    public ResponseEntity<Dependent> getDependent(@PathVariable("id") Long id) {
    	Optional<Dependent> dependent = dependentService.getDependent(id);
        if(dependent.isPresent()) {
        	return new ResponseEntity<Dependent>(dependent.get(), HttpStatus.OK);
        }
        return new ResponseEntity<Dependent>(HttpStatus.NOT_FOUND);
    }

	/**
	 * End point to delete dependent based on id
	 * @param id
	 * @param dependentId
	 * @return
	 */
    @DeleteMapping("{id}/dependent/{dependentId}")
    public ResponseEntity<Void> deleteDependent(@PathVariable("id") Long id, @PathVariable("dependentId") Long dependentId) {
    	//verify enrollee exists before adding dependent - enrollee service will throw record not found if doesn't exist
    	Optional<Enrollee> enrollee = enrolleeService.getEnrolleeById(id);
    	if(enrollee .isPresent()){
    		dependentService.deleteDependent(dependentId);
    	}
    	return new ResponseEntity<Void>(HttpStatus.OK);
    }

}
