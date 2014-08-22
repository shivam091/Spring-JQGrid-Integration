package com.jqgrid.persistence.repository;

import java.util.List;

import com.jqgrid.persistence.model.StudentEntity;

public interface StudentRepository {
	public StudentEntity saveStudentDetails(StudentEntity studentEntity);
	public StudentEntity findStudentById(Integer studentId);
	public StudentEntity updateStudentDetails(StudentEntity studentEntity);
	public StudentEntity deleteStudentDetails(StudentEntity studentEntity);
	public List<StudentEntity> findAllStudents();
}
