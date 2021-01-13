package com.centene.enrollment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.centene.enrollment.model.Enrollee;

/**
 * @Description: Interface for Enrollee Repository
 * @author Srikanth Palutla
 *
 */
@Repository
public interface EnrolleeRepository extends JpaRepository<Enrollee, Long>{

}
