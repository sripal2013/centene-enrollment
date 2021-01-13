package com.centene.enrollment.service;

import static org.junit.Assert.assertEquals;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.centene.enrollment.model.Dependent;
import com.centene.enrollment.model.Enrollee;
import com.centene.enrollment.repository.DependentRepository;
import com.centene.enrollment.service.impl.DependentServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
public class DependentServiceImplTest {

	@Mock
	DependentRepository dependentRepository;

	@InjectMocks
	DependentServiceImpl dependentServiceImpl;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	private Dependent mockDependent(String name, String dateOfBirth) {
		Dependent dependent = new Dependent();
		dependent.setId(1);
		dependent.setName(name);
		dependent.setDateOfBirth(dateOfBirth);
		return dependent;
	}

	private Enrollee mockEnrollee(String name, String dateOfBirth, String phone) {
		Enrollee enrollee = new Enrollee();
		enrollee.setId(1);
		enrollee.setName(name);
		enrollee.setDateOfBirth(dateOfBirth);
		enrollee.setActivationStatus(true);
		enrollee.setPhoneNumber(phone);

		return enrollee;
	}

	@Test
	public void testGetDependent() {
		Optional<Dependent> dependent = Optional.of(mockDependent("Matt", "12-5-2010"));
		Mockito.when(dependentRepository.findById(Mockito.anyLong())).thenReturn(dependent);
		Optional<Dependent> result = dependentServiceImpl.getDependent(1L);
		assertEquals(true, result.isPresent());
	}

	@Test
	public void testCreateDependent() {
		Dependent dependent = mockDependent("Matt", "12-5-2010");
		Enrollee enrollee = mockEnrollee("John", "12-12-1980", "123-433-3244");
		Mockito.when(dependentRepository.save(Mockito.any(Dependent.class))).thenReturn(dependent);
		Dependent result = dependentServiceImpl.createDependent(enrollee, dependent);
		assertEquals(true, result != null);
	}

	@Test
	public void testUpdateDependent() {
		Dependent dependent = mockDependent("Matt", "12-5-2010");
		Mockito.when(dependentRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(dependent));
		Mockito.when(dependentRepository.save(Mockito.any(Dependent.class))).thenReturn(dependent);
		Dependent result = dependentServiceImpl.updateDependent(dependent);
		assertEquals("Matt", result.getName());
	}

}
