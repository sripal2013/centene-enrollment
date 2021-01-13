package com.centene.enrollment.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;
/**
 * @Description: Entity class for Enrollee
 * @author Srikanth Palutla
 *
 */
@Entity
@Table(name = "enrollee")
public class Enrollee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @Column
    private boolean activationStatus;

    @Column
    private String dateOfBirth;

   	@Column
    private String phoneNumber;

    @OneToMany(mappedBy = "enrollee", cascade = {
            CascadeType.ALL
    })
    
    @JsonManagedReference
    private List<Dependent> dependents;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActivationStatus() {
        return activationStatus;
    }

    public void setActivationStatus(boolean activationStatus) {
        this.activationStatus = activationStatus;
    }
    
    public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Dependent> getDependents() {
        return dependents;
    }

    public void setDependents(List<Dependent> dependents) {
        this.dependents = dependents;
    }

}
