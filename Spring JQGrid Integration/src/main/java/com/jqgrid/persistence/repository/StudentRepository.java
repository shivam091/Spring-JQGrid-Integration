package com.jqgrid.persistence.repository;

import java.util.List;

import com.jqgrid.persistence.model.StudentEntity;

/**
 * Repository containing methods to manipulate data within the database table
 * @author shivam
 *
 */
public interface StudentRepository {
	
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
	 * 			ID of student
	 * @return {@link StudentEntity}
	 * @author shivam
	 */
	public StudentEntity findStudentById(Integer studentId);
	
	/**
	 * updates an existing student details
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
	 * finds details of all the students.
	 * @return list of {@link StudentEntity}
	 * @author shivam
	 */
	public List<StudentEntity> findAllStudents();
}