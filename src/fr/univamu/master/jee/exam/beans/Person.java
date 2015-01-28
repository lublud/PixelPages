package fr.univamu.master.jee.exam.beans;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import fr.univamu.master.jee.exam.dao.DAO;
import fr.univamu.master.jee.exam.dao.concret.PersonDAO;
import fr.univamu.master.jee.exam.exception.DateException;

@Entity(name = "Person")
public class Person implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id()
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idPerson;

	@Basic(optional = false)
	@Column(name = "login", length = 16, nullable = false, unique = true)
	private String login;

	@Basic(optional = false)
	@Column(name = "firstName", length = 64, nullable = false)
	private String firstName;

	@Basic(optional = false)
	@Column(name = "lastName", length = 64, nullable = false)
	private String lastName;

	@Basic(optional = true)
	@Column(name = "email", length = 128, nullable = false)
	private String email;

	@Basic(optional = true)
	@Column(name = "website", length = 128)
	private String website;

	@Basic()
	@Temporal(TemporalType.DATE)
	@Column(name = "birthdate", nullable = false)
	private Date birthdate;

	@Basic(optional = false)
	@Column(name = "password", length = 64, nullable = false)
	private String password;

	public Person() {
		super();
	} // Person()

	public Person canConnect(String login, String passwd) {
		DAO dao = new PersonDAO();
		dao.init();
		Person p = null;
		try {
			p = dao.existsPerson(login, SHA256(passwd));
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			dao.close();
		}
		dao.close();
		return p;
	} // canConnect()

	private static String byteToHex(byte b) {
		char hexDigit[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f' };
		char[] array = { hexDigit[(b >> 4) & 0x0f], hexDigit[b & 0x0f] };
		return new String(array);
	} // byteToHex()

	private static String SHA256(String text) throws NoSuchAlgorithmException,
			UnsupportedEncodingException {
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		byte[] hash = digest.digest(text.getBytes("UTF-8"));
		String res = "";
		for (int i = 0; i < hash.length; ++i)
			res += byteToHex(hash[i]);
		return res;
	} // SHA256()

	private static boolean isLeapYear(int year) {
		if (year % 4 == 0 && year % 100 != 0)
			return true;
		else if (year % 400 == 0)
			return true;
		return false;

	} // isLeapYear()

	public boolean checkBirthDate(String date) throws DateException {
		if (10 < date.length())
			throw new DateException("Error date: format is not correct.");

		String[] parts = date.split("/");
		if (3 != parts.length)
			throw new DateException("Error date: format is not correct.");

		int day = Integer.parseInt(parts[0]);
		int month = Integer.parseInt(parts[1]);
		int year = Integer.parseInt(parts[2]);

		if (year > Integer.parseInt(new SimpleDateFormat("yyyy")
				.format(new Date())))
			throw new DateException(
					"Error date: you were not born in the future.");

		if (month > 12 || month < 1)
			throw new DateException("Error date: unknown month.");

		if (day < 1)
			throw new DateException("Error date: unknown day.");

		if (day > 31 && (1 == month || 3 == month || 5 == month || 7 == month
				|| 8 == month || 10 == month || 12 == month))
			throw new DateException("Error date: unknown day.");
		if (day > 30 && (4 == month || 6 == month || 9 == month || 11 == month))
			throw new DateException("Error date: unknown day.");
		if (day > 29 && (2 == month && isLeapYear(year)))
			throw new DateException("Error date: unknown day.");
		if (day > 28 && 2 == month)
			throw new DateException("Error date: unknown day.");

		return true;
	} // checkBirthDate()
	
	public boolean checkPasswd (String passwd) {
		Pattern p = Pattern.compile("((?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%!/]).{8,32})");
		Matcher m = p.matcher(passwd);
		return m.matches();
	} // checkPasswd()
	
	public boolean checkEmail (String email) {
		Pattern p = Pattern.compile("(^[a-zA-Z0-9._-]+@[a-zA-Z0-9._-]{2,}\\.[a-zA-Z]{2,4}$)");
		Matcher m = p.matcher(email);
		return m.matches();
	} // checkEmail()

	public int getIdPerson() {
		return idPerson;
	} // getIdPerson()

	public void setIdPerson(int idPerson) {
		this.idPerson = idPerson;
	} // setIdPerson()

	public String getLogin() {
		return login;
	} // getLogin()

	public void setLogin(String login) {
		this.login = login;
	} // setLogin()

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

	public String getPassword() {
		return password;
	} // getBirthdate()

	public void setPassword(String password) {
		try {
			this.password = SHA256(password);
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} // setPassword()

}
