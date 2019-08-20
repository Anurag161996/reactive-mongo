package com.tech.recative.reactivemongo.model;

import java.util.Date;



public class PersonEvent {
	public PersonEvent(Person person2, Date date2) {
		this.person = person2;
		this.date = date2; 
		}
	private Person person;
	private Date date;
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	
}
