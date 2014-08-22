package com.jqgrid.persistence.repository.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jqgrid.persistence.model.StudentEntity;
import com.jqgrid.persistence.repository.StudentRepository;

@Repository
public class StudentRepositoryImpl implements StudentRepository {

	@Autowired SessionFactory sessionFactory;
	
	private final static Logger LOGGER = Logger.getLogger(StudentRepositoryImpl.class.getName());
	
	public static final String TENANT_CODE = "student";
	
	@Override
	public StudentEntity saveStudentDetails(StudentEntity studentEntity) {
		LOGGER.info("Start repository Save");
		Session session = sessionFactory.withOptions().tenantIdentifier(TENANT_CODE).openSession();
		LOGGER.info("Session from SessionFactory for Tenant " + TENANT_CODE);
		Transaction tx = session.beginTransaction();
		LOGGER.info("Transaction started");
		
		session.save(studentEntity);
		session.flush();
		LOGGER.info("Stored student details: " + studentEntity);
		
		tx.commit();
		session.close();
		LOGGER.info("Transaction commited.");
		
		return studentEntity;
	}

	@Override
	public StudentEntity findStudentById(Integer studentId) {
		LOGGER.info("Start repository findByRegistration_id");
		Session session = sessionFactory.withOptions().tenantIdentifier(TENANT_CODE).openSession();
		LOGGER.info("Session from SessionFactory for Tenant " + TENANT_CODE);
		Criteria criteria = session.createCriteria(StudentEntity.class);
		Criterion cn = Restrictions.and(Restrictions.eq("studentId", studentId));
		criteria.add(cn);
		StudentEntity result = (StudentEntity) criteria.uniqueResult();

		session.close();
		LOGGER.info("Session closed for Tenant " + TENANT_CODE);
		
		return result;
	}

	@Override
	public StudentEntity updateStudentDetails(StudentEntity studentEntity) {
		LOGGER.info("Start repository update");
		Session session = sessionFactory.withOptions().tenantIdentifier(TENANT_CODE).openSession();
		LOGGER.info("Session from SessionFactory for Tenant " + TENANT_CODE);
		Transaction tx = session.beginTransaction();
		LOGGER.info("Transaction started");
		
		session.saveOrUpdate(studentEntity);
		session.flush();
		LOGGER.info("Student details updated at ID: " + studentEntity.getStudentId());
		
		tx.commit();
		session.close();
		LOGGER.info("Transaction commited");
		return studentEntity;
	}

	@Override
	public StudentEntity deleteStudentDetails(StudentEntity studentEntity) {
			LOGGER.info("Start repository Save");
			Session session = sessionFactory.withOptions().tenantIdentifier(TENANT_CODE).openSession();
			LOGGER.info("Session from SessionFactory for Tenant " + TENANT_CODE);
			Transaction tx = session.beginTransaction();
			LOGGER.info("Transaction started");
			
			session.delete(studentEntity);
			session.flush();
			LOGGER.info("Student details stored at ID: " + studentEntity.getStudentId());
			
			tx.commit();
			session.close();
			LOGGER.info("Transaction commited.");
			
			return studentEntity;
	}

	@Override
	public List<StudentEntity> findAllStudents() {
		// LOGGER.info("Start repository findAll");
		Session session = sessionFactory.withOptions().tenantIdentifier(TENANT_CODE).openSession();
		LOGGER.info("Session from SessionFactory for Tenant " + TENANT_CODE);
		Criteria criteria = session.createCriteria(StudentEntity.class);
		@SuppressWarnings("unchecked")
		List<StudentEntity> result = (List<StudentEntity>) criteria.list();

		session.close();
		LOGGER.info("Session closed for Tenant " + TENANT_CODE);
		
		return result;
	}
}