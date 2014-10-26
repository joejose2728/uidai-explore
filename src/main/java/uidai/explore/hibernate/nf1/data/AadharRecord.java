package uidai.explore.hibernate.nf1.data;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "aadhar_record")
public class AadharRecord {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="aid", updatable=false, nullable= false)
	private long aid;
	private String registrar;	
	private String enrollmentAgency;
	private String state;
	private String district;
	private String subDistrict;
	private long pinCode;
	private char gender;
	private int age;
	private int aadharGenerated;
	private int enrollmentRejected;
	private int emailProvided;
	private int phoneNumberProvided;
	private Date enrollmentDate;
	
	
	public long getAid() {
		return aid;
	}
	public void setAid(long aid) {
		this.aid = aid;
	}
	public String getRegistrar() {
		return registrar;
	}
	public void setRegistrar(String registrar) {
		this.registrar = registrar;
	}
	public String getEnrollmentAgency() {
		return enrollmentAgency;
	}
	public void setEnrollmentAgency(String enrollmentAgency) {
		this.enrollmentAgency = enrollmentAgency;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getSubDistrict() {
		return subDistrict;
	}
	public void setSubDistrict(String subDistrict) {
		this.subDistrict = subDistrict;
	}
	public long getPinCode() {
		return pinCode;
	}
	public void setPinCode(long pinCode) {
		this.pinCode = pinCode;
	}
	public char getGender() {
		return gender;
	}
	public void setGender(char gender) {
		this.gender = gender;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getAadharGenerated() {
		return aadharGenerated;
	}
	public void setAadharGenerated(int aadharGenerated) {
		this.aadharGenerated = aadharGenerated;
	}
	public int getEnrollmentRejected() {
		return enrollmentRejected;
	}
	public void setEnrollmentRejected(int enrollmentRejected) {
		this.enrollmentRejected = enrollmentRejected;
	}
	public int getEmailProvided() {
		return emailProvided;
	}
	public void setEmailProvided(int emailProvided) {
		this.emailProvided = emailProvided;
	}
	public int getPhoneNumberProvided() {
		return phoneNumberProvided;
	}
	public void setPhoneNumberProvided(int phoneNumberProvided) {
		this.phoneNumberProvided = phoneNumberProvided;
	}
	public Date getEnrollmentDate() {
		return enrollmentDate;
	}
	public void setEnrollmentDate(Date enrollmentDate) {
		this.enrollmentDate = enrollmentDate;
	}
	
	
}
