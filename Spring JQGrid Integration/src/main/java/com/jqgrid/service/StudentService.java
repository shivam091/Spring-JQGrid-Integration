package com.jqgrid.service;

import java.util.List;

import com.jqgrid.persistence.model.StudentEntity;

/**
 * service for processing student details returned from repository
 * @author shivam
 *
 */
public interface StudentService {
	/**
	 * adds a new student
	 * @param {@link StudentEntity}
	 * 			object of StudentEntity
	 * @return {@link StudentEntity}
	 * @author shivam
	 */
	public StudentEntity saveStudentDetails(StudentEntity studentEntity);
	
	/**
	 * finds an existing student by id.
	 * @param studentId
	 * 			student id
	 * @return {@link StudentEntity}
	 * @author shivam
	 */
	public StudentEntity findStudentById(Integer studentId);
	
	/**
	 * updates details of existing student returned from repository
	 * @param {@link StudentEntity}
	 * 			object of StudentEntity
	 * @return {@link StudentEntity}
	 * @author shivam
	 */
	public StudentEntity updateStudentDetails(StudentEntity studentEntity);
	
	/**
	 * deletes an existing student details
	 * @param {@link StudentEntity}
	 * 			object of StudentEntity
	 * @return {@link StudentEntity}
	 * @author shivam
	 */
	public StudentEntity deleteStudentDetails(StudentEntity studentEntity);
	
	/**
	 * passes details of all the students to controller.
	 * @return list of {@link StudentEntity}
	 * @author shivam
	 */
	public List<StudentEntity> findAllStudents();
}
