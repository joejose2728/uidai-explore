package uidai.explore.hibernate.nf1.data;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class AadharRecPerDayKey implements Serializable{

	@Column(name="date")
	private Date date;
	
	@Column(name="gender")
	private char gender;
	
	@Column(name="age")
	private int age;
	
	@Column(name="ag_id")
	private long agId;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char c) {
		this.gender = c;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public long getAgId() {
		return agId;
	}

	public void setAgId(long agId) {
		this.agId = agId;
	}
	
	
}
