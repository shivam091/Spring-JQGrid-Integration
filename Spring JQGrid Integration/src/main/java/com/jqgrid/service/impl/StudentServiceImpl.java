package com.jqgrid.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jqgrid.persistence.model.StudentEntity;
import com.jqgrid.persistence.repository.StudentRepository;
import com.jqgrid.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired StudentRepository studentRepository;
	
	@Override
	public StudentEntity saveStudentDetails(StudentEntity studentEntity) {
		return studentRepository.saveStudentDetails(studentEntity);
	}

	@Override
	public StudentEntity findStudentById(Integer studentId) {
		return studentRepository.findStudentById(studentId);
	}

	@Override
	public StudentEntity updateStudentDetails(StudentEntity studentEntity) {
		return studentRepository.updateStudentDetails(studentEntity);
	}

	@Override
	public StudentEntity deleteStudentDetails(StudentEntity studentEntity) {
		return studentRepository.deleteStudentDetails(studentEntity);
	}

	@Override
	public List<StudentEntity> findAllStudents() {
		return studentRepository.findAllStudents();
	}
}