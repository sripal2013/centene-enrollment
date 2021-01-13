package com.centene.enrollment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.centene.enrollment.model.Dependent;
/**
 * @Description: Interface for Dependent Repository 
 * @author Srikanth Palutla
 *
 */
@Repository
public interface DependentRepository extends JpaRepository<Dependent, Long> {
	
}
