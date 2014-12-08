package fr.univamu.master.jee.exam.beans;

import java.util.Date;

public class Person {
	private int idPerson;
	private String firstName;
	private String lastName;
	private String email;
	private String website;
	private Date birthdate;
	private String password;
	
	
	public Person() {
		super();
	} // Person()


	public int getIdPerson() {
		return idPerson;
	} // getIdPerson()


	public void setIdPerson(int idPerson) {
		this.idPerson = idPerson;
	} // setIdPerson()


	public String getFirstName() {
		return firstName;
	} // getFirstName()


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	} // setFirstName()


	public String getLastName() {
		return lastName;
	} // getLastName()


	public void setLastName(String lastName) {
		this.lastName = lastName;
	} // setLastName()


	public String getEmail() {
		return email;
	} // getEmail()


	public void setEmail(String email) {
		this.email = email;
	} // setEmail()


	public String getWebsite() {
		return website;
	} // getWebsite()


	public void setWebsite(String website) {
		this.website = website;
	} // setWebsite()


	public Date getBirthdate() {
		return birthdate;
	} // getBirthdate()


	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	} // setBirthdate()


	public void setPassword(String password) {
		this.password = password;
	} // setPassword()
	
	
	
	
}
