package com.jqgrid.persistence.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Persistence class to manipulate data within the database table
 * @author shivam
 *
 */
@Entity
@Table(name = "STUDENT_DETAILS", schema = "student")
public class StudentEntity implements Serializable {

	private static final long serialVersionUID = -9025482195617462917L;

	@Id
	@Column(name = "STUDENT_ID", unique = true)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer studentId;
	
	@NotEmpty
	@Column(name = "STUDENT_FIRST_NAME", length = 30)
	private String studentFirstName;
	
	@NotEmpty
	@Column(name = "STUDENT_LAST_NAME", length = 30)
	private String studentLastName;
	
	@NotEmpty
	@Column(name = "STUDENT_BIRTH_DATE", length = 50)
	private String studentBirthDate;
	
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

	public String getStudentBirthDate() {
		return studentBirthDate;
	}

	public void setStudentBirthDate(String studentBirthDate) {
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