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
 * controller to handle request coming from grid and sending response back to grid
 * @author shivam
 */
@RestController
@RequestMapping(value = "rest/student/")
public class StudentController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(StudentController.class);

	@Autowired StudentService studentService;
	
	/**
	 * returns details of all the students
	 * @param search
	 * 			returns true if search operation is to be performed
	 * @param filters
	 * 			filters applied to search
	 * @param page
	 * 			total no. of pages
	 * @param rows
	 * 			total no. of rows
	 * @param sidx
	 * 			name of the field to be sorted
	 * @param sord
	 * 			direction of sorting (ascending or descending)
	 * @return  {@link JQGridTable} A simple POJO that maps to the JSON structure of a jqGrid.
	 * @author shivam
	 */
	@RequestMapping(value = "/getStudentDetails.json", method = RequestMethod.POST, produces = {"application/json"})
	@ResponseStatus(value = HttpStatus.OK)
	public @ResponseBody JQGridTable getStudentDetails(@RequestParam(value = "_search", required = false) Boolean search,
    		@RequestParam(value="filters", required=false) String filters, 
    		@RequestParam(value="page", required=false) Integer page,  
    		@RequestParam(value="rows", required=false) Integer rows,  
    		@RequestParam(value="sidx", required=false) String sidx, 
    		@RequestParam(value="sord", required=false) String sord) { 
		List<StudentEntity> studentRecords = new ArrayList<StudentEntity>();
		 // Initialize our custom user response wrapper
		JQGridTable jQGridTable = new JQGridTable();
		
		try {
			LOGGER.info("Service consumed to return records of all the students.");
			
			// Retrieve all users from the service
			studentRecords = studentService.findAllStudents();
			if(studentRecords != null) {
				// Assign the result from the service to this response
				jQGridTable.setRows(studentRecords);
				
				// Assign the total number of records found. This is used for paging
				jQGridTable.setRecords(new Integer(studentRecords.size()).toString());
				
				// Same. Assign a dummy total pages
				jQGridTable.setTotal(new String("1"));
				
				// Since our service is just a simple service for teaching purposes
		        // We didn't really do any paging. But normally your DAOs or your persistence layer should support this
		        // Assign a dummy page
				jQGridTable.setPage(new String("1"));
			}
		}
		catch(Exception e) {
			LOGGER.error("Error has been raised: ", e);
		}
		// Return the response
        // Spring will automatically convert our JQGridTable as JSON object. 
        // This is triggered by the @ResponseBody annotation. 
        // It knows this because the JqGrid has set the headers to accept JSON format when it made a request
        // Spring by default uses Jackson to convert the object to JSON
		return jQGridTable;
	}
	
	/**
	 * 
	 * @param studentId
	 * 			id for unique identification of student
	 * @param studentFirstName
	 * 			first name of student.
	 * @param studentLastName
	 * 			last name of student
	 * @param studentBirthDate
	 * 			birth date of student
	 * @param studentQualification
	 * 			qualification of student
	 * @return {@link JQResponse} true if operation is successful otherwise false
	 * @author shivam
	 */
	@RequestMapping(value = "/saveStudentDetails.json", method = RequestMethod.POST, produces = {"application/json"})
	@ResponseStatus(value = HttpStatus.OK)
	public @ResponseBody JQResponse saveStudentDetails(@RequestParam("studentId") String studentId, 
			@RequestParam("studentFirstName") String studentFirstName,
			@RequestParam("studentLastName") String studentLastName, 
			@RequestParam("studentBirthDate") String studentBirthDate,
			@RequestParam("studentQualification") String studentQualification) {
			LOGGER.info("Service consumed to create new student.");
			StudentEntity studentEntity = new StudentEntity();
			JQResponse jqResponse = new JQResponse();
			studentEntity.setStudentFirstName(studentFirstName);
			studentEntity.setStudentLastName(studentLastName);
			studentEntity.setStudentBirthDate(studentBirthDate);
			studentEntity.setStudentQualification(studentQualification);
			
			if(studentService.saveStudentDetails(studentEntity) != null) {
				jqResponse.setSuccess(true);
				jqResponse.setMessagse("Operation is successful");
			} else {
				jqResponse.setSuccess(false);
				jqResponse.setMessagse("Operation is failed");
			}
			return jqResponse;
	}
	
	/**
	 * 
	 * @param studentId
	 * 			id for unique identification of student
	 * @param studentFirstName
	 * 			first name of student.
	 * @param studentLastName
	 * 			last name of student
	 * @param studentBirthDate
	 * 			birth date of student
	 * @param studentQualification
	 * 			qualification of student
	 * @return {@link JQResponse} true if operation is successful otherwise false
	 * @author shivam
	 */
	@RequestMapping(value = "/updateStudentDetails.json", method = RequestMethod.POST, produces = {"application/json"})
	@ResponseStatus(value = HttpStatus.OK)
	public @ResponseBody JQResponse updateStudentDetails(@RequestParam("studentId") Integer studentId,
			@RequestParam("studentFirstName") String studentFirstName, 
			@RequestParam("studentLastName") String studentLastName, 
			@RequestParam("studentBirthDate") String studentBirthDate,
			@RequestParam("studentQualification") String studentQualification) {
			LOGGER.info("Service consumed to update details of existung student.");
			StudentEntity studentEntity = studentService.findStudentById(studentId);
			JQResponse jqResponse = new JQResponse();
			studentEntity.setStudentFirstName(studentFirstName);
			studentEntity.setStudentLastName(studentLastName);
			studentEntity.setStudentBirthDate(studentBirthDate);
			studentEntity.setStudentQualification(studentQualification);
			
			if(studentService.updateStudentDetails(studentEntity) != null) {
				jqResponse.setSuccess(true);
				jqResponse.setMessagse("Operation is successful");
			} else {
				jqResponse.setSuccess(false);
				jqResponse.setMessagse("Operation is failed");
			}
			return jqResponse;
	}
	
	/**
	 * 
	 * @param studentId
	 * 			id for unique identification of student
	 * @return {@link JQResponse} true if operation is successful otherwise false
	 * @author shivam
	 */
	@RequestMapping(value = "/deleteStudentDetails.json", method = RequestMethod.POST, produces = {"application/json"})
	@ResponseStatus(value = HttpStatus.OK)
	public @ResponseBody JQResponse deleteStudentDetails(@RequestParam("studentId") Integer studentId) {
		LOGGER.info("Service consumed to delete existing student.");
		JQResponse jqResponse = new JQResponse();
		
		if (studentService.deleteStudentDetails(studentService.findStudentById(studentId)) != null) {
			jqResponse.setSuccess(true);
			jqResponse.setMessagse("Operation is successful");			
		}	
		else {
			jqResponse.setSuccess(true);
			jqResponse.setMessagse("Operation is successful");
		}
		return jqResponse;
	}
}