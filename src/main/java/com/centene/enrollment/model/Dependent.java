package com.centene.enrollment.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * @Description: Entity class for Dependent
 * @author Srikanth Palutla
 *
 */
@Entity
@Table(name = "dependent")
public class Dependent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @Column
    private String dateOfBirth;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "enrollee_id", nullable = false)
    @JsonBackReference
    private Enrollee enrollee;


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

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setEnrollee(Enrollee enrollee) {
        this.enrollee = enrollee;
    }

    public Enrollee getEnrollee() {
        return enrollee;
    }


}

