package com.jqgrid.persistence.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "STUDENT_DETAILS", schema = "student")
public class StudentEntity implements Serializable{

	private static final long serialVersionUID = -9025482195617462917L;

	@Id
	@Column(name = "STUDENT_ID", unique = true)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer studentId;
	
	@NotEmpty
	@Column(name = "STUDENT_FIRST_NAME", length = 20)
	private String studentFirstName;
	
	@NotEmpty
	@Column(name = "STUDENT_LAST_NAME", length = 20)
	private String studentLastName;
	
	@NotNull
	@Column(name = "STUDENT_BIRTH_DATE", length = 20)
	private Date studentBirthDate;
	
	@NotEmpty
	@Column(name = "QUALIFATION", length = 50)
	private String studentQualification;

	public Integer getStudentId() {
		return studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	public String getStudentFirstName() {
		return studentFirstName;
	}

	public void setStudentFirstName(String studentFirstName) {
		this.studentFirstName = studentFirstName;
	}

	public String getStudentLastName() {
		return studentLastName;
	}

	public void setStudentLastName(String studentLastName) {
		this.studentLastName = studentLastName;
	}

	public Date getStudentBirthDate() {
		return studentBirthDate;
	}

	public void setStudentBirthDate(Date studentBirthDate) {
		this.studentBirthDate = studentBirthDate;
	}

	public String getStudentQualification() {
		return studentQualification;
	}

	public void setStudentQualification(String studentQualification) {
		this.studentQualification = studentQualification;
	}

	@Override
	public String toString() {
		return "StudentEntity [studentId=" + studentId + ", studentFirstName="
				+ studentFirstName + ", studentLastName=" + studentLastName
				+ ", studentBirthDate=" + studentBirthDate
				+ ", studentQualification=" + studentQualification + "]";
	}
}