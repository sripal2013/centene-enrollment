package com.centene.enrollment.controller;

import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.centene.enrollment.EnrollmentApplication;
import com.centene.enrollment.model.Dependent;
import com.centene.enrollment.model.Enrollee;
import com.centene.enrollment.service.DependentService;
import com.centene.enrollment.service.EnrolleeService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = EnrollmentApplication.class)
@WebAppConfiguration
public class EnrollmentControllerTest {
	
    protected MockMvc mockMvc;
	
	@Autowired
	WebApplicationContext webApplicationContext;
 
    @MockBean
    private EnrolleeService enrolleeService;
    
    @MockBean
    private DependentService dependentService;
    
    ObjectMapper mapper = new ObjectMapper();
    
    @Before
    public void setUp(){
    	mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    	mapper = new ObjectMapper();
    }
    
    @Test
    public void testGetAllEnrollees() throws Exception {
    	List<Enrollee> enrolleeList = new ArrayList<Enrollee>();
        enrolleeList.add(mockEnrollee("John","12-12-1980","123-433-3244"));
        enrolleeList.add(mockEnrollee("Scott","1-12-1984","123-433-1111"));
        Mockito.when(enrolleeService.getEnrollees()).thenReturn(enrolleeList);
        mockMvc.perform(get("/enrollment/all")).andExpect(status().isOk()).andExpect(jsonPath("$", Matchers.hasSize(2)))
                .andExpect(jsonPath("$[0].name", Matchers.equalTo("John")));
    }
    
    
    @Test
    public void testGetEnrollee() throws Exception {    	
        Optional<Enrollee> enrollee = Optional.of(mockEnrollee("John","12-12-1980","123-433-3244"));
        Mockito.when(enrolleeService.getEnrolleeById(Mockito.anyLong())).thenReturn(enrollee);
        mockMvc.perform(MockMvcRequestBuilders.get("/enrollment/1")).andExpect(status().isOk())
                .andExpect(jsonPath("$.name", Matchers.equalTo("John")));
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
    public void testCreateEnrollee() throws Exception {
    	 Enrollee enrollee = mockEnrollee("John","12-12-1980","123-433-3244");
        String response = mapper.writeValueAsString(enrollee);
        Mockito.when(enrolleeService.createEnrollee(Mockito.any(Enrollee.class))).thenReturn(enrollee);
        mockMvc.perform(post("/enrollment/create").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                .content(response).accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", Matchers.equalTo("John")));
    }
    
    @Test
    public void testUpdateEnrollee() throws Exception {
    	Enrollee enrollee = mockEnrollee("John","12-12-1980","123-433-3244");
        String response = mapper.writeValueAsString(enrollee);
        Mockito.when(enrolleeService.updateEnrollee(Mockito.any(Enrollee.class))).thenReturn(enrollee);
        mockMvc.perform(put("/enrollment/update").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                .content(response).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(jsonPath("$.name", Matchers.equalTo("John")));
    }
    
    @Test
    public void testDeleteEnrollee() throws Exception {
        doNothing().when(enrolleeService).deleteEnrollee(Mockito.anyLong());
        mockMvc.perform(delete("/enrollment/1")).andExpect(status().isOk());
    }
    
    private Dependent mockDependent(String name, String dateOfBirth){
    	Dependent dependent = new Dependent();
    	dependent.setId(1);
    	dependent.setName(name);
    	dependent.setDateOfBirth(dateOfBirth);        
        return dependent;
    }
    
    
    @Test
    public void testGetDependent() throws Exception {
        Optional<Dependent> dependent = Optional.of(mockDependent("Matt", "12-5-2010"));
        Mockito.when(dependentService.getDependent(Mockito.anyLong())).thenReturn(dependent);
        mockMvc.perform(get("/enrollment/dependent/1")).andExpect(status().isOk())
                .andExpect(jsonPath("$.name", Matchers.equalTo("Matt")));
    }
    
    @Test
    public void testGetAllDependents() throws Exception {
    	List<Dependent> list = new ArrayList<Dependent>();
    	list.add(mockDependent("Matt", "12-5-2010"));
    	list.add(mockDependent("Sri", "12-5-2012"));
        Mockito.when(enrolleeService.getDependentsByEnrolleeId(Mockito.anyLong())).thenReturn(list);
        mockMvc.perform(get("/enrollment/1/dependents")).andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", Matchers.equalTo("Matt")));
    }
    
   
    @Test
    public void testCreateDependent() throws Exception {
    	Dependent dep = mockDependent("Matt", "12-5-2010");
    	List<Dependent> list = new ArrayList<Dependent>();
    	list.add(dep);
    	Enrollee enrollee = mockEnrollee("John","12-12-1980","123-433-3244");
    	enrollee.setDependents(list);
        Optional<Enrollee> enrollees = Optional.of(enrollee);
        Mockito.when(enrolleeService.getEnrolleeById(Mockito.anyLong())).thenReturn(enrollees);
        String json = mapper.writeValueAsString(dep);
        Mockito.when(dependentService.createDependent(Mockito.any(Enrollee.class), Mockito.any(Dependent.class))).thenReturn(dep);
        mockMvc.perform(post("/enrollment/1/dependent").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                .content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", Matchers.equalTo("Matt")));
    }
    
    @Test
    public void testUpdateDependent() throws Exception {
    	Dependent dep = mockDependent("Matt", "12-5-2010");
    	List<Dependent> list = new ArrayList<Dependent>();
    	list.add(dep);
    	Enrollee enrollee = mockEnrollee("John","12-12-1980","123-433-3244");
    	enrollee.setDependents(list);
        Optional<Enrollee> enrollees = Optional.of(enrollee);
        Mockito.when(enrolleeService.getEnrolleeById(Mockito.anyLong())).thenReturn(enrollees);
        String json = mapper.writeValueAsString(dep);
        Mockito.when(dependentService.updateDependent(Mockito.any(Dependent.class))).thenReturn(dep);
        mockMvc.perform(put("/enrollment/1/dependent").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                .content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(jsonPath("$.name", Matchers.equalTo("Matt")));
    }
    
    @Test
    public void testDeleteDependent() throws Exception {
    	Enrollee enrollee = mockEnrollee("John","12-12-1980","123-433-3244");
        Optional<Enrollee> enrollees = Optional.of(enrollee);
        Mockito.when(enrolleeService.getEnrolleeById(Mockito.anyLong())).thenReturn(enrollees);
        doNothing().when(dependentService).deleteDependent(Mockito.anyLong());
        mockMvc.perform(delete("/enrollment/1/dependent/2")).andExpect(status().isOk());
    }

    

}
