package com.jqgrid.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.junit.runners.MethodSorters;

import com.jqgrid.persistence.model.StudentEntity;
import com.jqgrid.service.StudentService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/main-dispatcher-servlet.xml", "classpath:spring/mvc-dispatcher-servlet.xml" })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StudentRepositoryTests {

	@Autowired
	private StudentService studentService;
	
	private StudentEntity studentEntity;
	
	@Before
	public void setup() {
		studentEntity = new StudentEntity();
		studentEntity.setStudentFirstName("ABC");
		studentEntity.setStudentLastName("XYZ");
		studentEntity.setStudentBirthDate(new Date());
		studentEntity.setStudentQualification("PQR");
	}
	
	@Test
	public void aSaveStudentDetailsTests() {
		assertNotNull(studentService.saveStudentDetails(studentEntity));
	}
	
	@Test
	public void bUpdateStudentDetailsTests() {
		StudentEntity result = studentService.findAllStudents().get(0);
		assertNotNull(result);
		
		result.setStudentQualification("Qualification");
		result = studentService.updateStudentDetails(result);
		assertEquals("Qualification", result.getStudentQualification());
	}
	
	@Test
	public void cFindStudentDetailsByIdTests() {
		assertNotNull(studentService.findStudentById(1));
	}
	
	@Test
	public void bFindAllStudentDetailsTests() {
		assertTrue(studentService.findAllStudents().size() > 0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void bDeleteStudentDetailsTests() {
		StudentEntity studentEntity = studentService.findStudentById(1);
		assertNotNull(studentService.deleteStudentDetails(studentEntity));
	}
}