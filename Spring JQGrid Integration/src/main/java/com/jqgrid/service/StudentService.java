package com.jqgrid.service;

import java.util.List;

import com.jqgrid.persistence.model.StudentEntity;

public interface StudentService {
	public StudentEntity saveStudentDetails(StudentEntity studentEntity);
	public StudentEntity findStudentById(Integer studentId);
	public StudentEntity updateStudentDetails(StudentEntity studentEntity);
	public StudentEntity deleteStudentDetails(StudentEntity studentEntity);
	public List<StudentEntity> findAllStudents();
}
