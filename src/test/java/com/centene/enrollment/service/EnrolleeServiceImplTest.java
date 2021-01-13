package com.centene.enrollment.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.centene.enrollment.model.Enrollee;
import com.centene.enrollment.repository.EnrolleeRepository;
import com.centene.enrollment.service.impl.EnrolleeServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
public class EnrolleeServiceImplTest {
	
	@Mock
	EnrolleeRepository enrolleeRepository;
	
	@InjectMocks
	EnrolleeServiceImpl enrolleeServiceImpl;
	
	@Before
	public void init(){
		MockitoAnnotations.initMocks(this);
	}
	
	private Enrollee mockEnrollee(String name, String dateOfBirth, String phone){
    	Enrollee enrollee = new Enrollee();
        enrollee.setId(1);
        enrollee.setName(name);
        enrollee.setDateOfBirth(dateOfBirth);
        enrollee.setActivationStatus(true);
        enrollee.setPhoneNumber(phone);
        
        return enrollee;
    }
	
	@Test
	public void testGetEnrollees(){
		List<Enrollee> enrolleeList = new ArrayList<Enrollee>();
        enrolleeList.add(mockEnrollee("John","12-12-1980","123-433-3244"));
        enrolleeList.add(mockEnrollee("Scott","1-12-1984","123-433-1111"));
        Mockito.when(enrolleeRepository.findAll()).thenReturn(enrolleeList);
        List<Enrollee> result = enrolleeServiceImpl.getEnrollees();
        assertEquals(2, result.size());
	}
	
	@Test
	public void testGetEnrolleeById(){
		Optional<Enrollee> enrollee = Optional.of(mockEnrollee("John","12-12-1980","123-433-3244"));
		Mockito.when(enrolleeRepository.findById(Mockito.anyLong())).thenReturn(enrollee);
		Optional<Enrollee> result = enrolleeServiceImpl.getEnrolleeById(12L);
		assertEquals("John", result.get().getName());
	}
	
	@Test
	public void testCreateEnrollee(){
		Enrollee enrollee = mockEnrollee("JohnK","12-12-1980","123-433-3244");
		Mockito.when(enrolleeRepository.save(Mockito.any(Enrollee.class))).thenReturn(enrollee);
		Enrollee result = enrolleeServiceImpl.createEnrollee(enrollee);
		assertEquals("JohnK", result.getName());
	}
	
	@Test
	public void testDeleteEnrollee(){
		doNothing().when(enrolleeRepository).deleteById(Mockito.anyLong());
		enrolleeServiceImpl.deleteEnrollee(1L);
	}
	
	@Test
	public void testUpdateEnrollee(){
		Enrollee enrollee = mockEnrollee("JohnK","12-12-1980","123-433-3244");
		Mockito.when(enrolleeRepository.findById(Mockito.anyLong())).thenReturn( Optional.of(enrollee));
		Mockito.when(enrolleeRepository.save(Mockito.any(Enrollee.class))).thenReturn(enrollee);
		Enrollee result = enrolleeServiceImpl.updateEnrollee(enrollee);
		assertEquals("JohnK", result.getName());
	}
	
	

}
