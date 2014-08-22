package com.jqgrid.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jqgrid.persistence.model.StudentEntity;
import com.jqgrid.service.StudentService;
import com.jqgrid.web.controller.response.JQGridTable;
import com.jqgrid.web.controller.response.JQResponse;

/**
 * Handles requests for the application home page.
 */
@RestController
@RequestMapping(value = "rest/student/")
public class StudentController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(StudentController.class);

	@Autowired StudentService studentService;
	
	@RequestMapping(value = "/getStudentDetails.json", method = RequestMethod.POST, produces = {"application/json"})
	@ResponseStatus(value = HttpStatus.OK)
	public @ResponseBody JQGridTable getStudentDetails(@RequestParam(value = "_search", required = false) Boolean search, /* returns true if search operation is to be performed */
    		@RequestParam(value="filters", required=false) String filters, /* filters applied to search */
    		@RequestParam(value="page", required=false) Integer page,  /* total no. of pages */
    		@RequestParam(value="rows", required=false) Integer rows,  /* total no. of rows */
    		@RequestParam(value="sidx", required=false) String sidx, /* name of sort field */
    		@RequestParam(value="sord", required=false) String sord) /* direction of sorting (asc or desc)*/{ 
		List<StudentEntity> studentRecords = new ArrayList<StudentEntity>();
		JQGridTable jQGridReponse = new JQGridTable();
		try {
			LOGGER.info("Service consumed to return records of all the students.");
			studentRecords = studentService.findAllStudents();
			if(studentRecords != null) {
				jQGridReponse.setRows(studentRecords);
				jQGridReponse.setRecords(new Integer(studentRecords.size()).toString());
				jQGridReponse.setTotal(new String("1"));
				jQGridReponse.setPage(new String("1"));
			}
		}
		catch(Exception e) {
			LOGGER.error("Error has been raised: ", e);
		}
		return jQGridReponse;
	}
	
	@RequestMapping(value = "/saveStudentDetails.json", method = RequestMethod.POST, produces = {"application/json"})
	@ResponseStatus(value = HttpStatus.OK)
	public @ResponseBody JQResponse saveStudentDetails(@RequestParam("studentId") String studentId, 
			@RequestParam("studentFirstName") String studentFirstName,
			@RequestParam("studentLastName") String studentLastName, 
			@RequestParam("studentBirthDate") String studentBirthDate,
			@RequestParam("studentQualification") String studentQualification) {
			StudentEntity studentEntity = new StudentEntity();
			studentEntity.setStudentFirstName(studentFirstName);
			studentEntity.setStudentLastName(studentLastName);
			studentEntity.setStudentBirthDate(studentBirthDate);
			studentEntity.setStudentQualification(studentQualification);
			if(studentService.saveStudentDetails(studentEntity) != null) {
				return new JQResponse(true);
			} else {
				return new JQResponse(false);
			}
	}
	
	@RequestMapping(value = "/updateStudentDetails.json", method = RequestMethod.POST, produces = {"application/json"})
	@ResponseStatus(value = HttpStatus.OK)
	public @ResponseBody JQResponse updateStudentDetails(@RequestParam("studentId") Integer studentId,
			@RequestParam("studentFirstName") String studentFirstName, 
			@RequestParam("studentLastName") String studentLastName, 
			@RequestParam("studentBirthDate") String studentBirthDate,
			@RequestParam("studentQualification") String studentQualification) {
			StudentEntity studentEntity = studentService.findStudentById(studentId);
			studentEntity.setStudentFirstName(studentFirstName);
			studentEntity.setStudentLastName(studentLastName);
			studentEntity.setStudentBirthDate(studentBirthDate);
			studentEntity.setStudentQualification(studentQualification);
			if(studentService.updateStudentDetails(studentEntity) != null) {
				return new JQResponse(true);
			} else {
				return new JQResponse(false);
			}
	}
	
	@RequestMapping(value = "/deleteStudentDetails.json", method = RequestMethod.POST, produces = {"application/json"})
	@ResponseStatus(value = HttpStatus.OK)
	public @ResponseBody JQResponse deleteStudentDetails(@RequestParam("studentId") Integer studentId) {
		if (studentService.deleteStudentDetails(studentService.findStudentById(studentId)) != null) {
			return new JQResponse(true);			
		}	
		else {
			return new JQResponse(false);
		}
	}
}