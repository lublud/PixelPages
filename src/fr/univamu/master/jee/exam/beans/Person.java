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

/**
 * <b>Person</b>
 * <p>
 * It represents a person in the directory.<br>
 * A person is characterized by:
 * <ul>
 * <li>An id</li>
 * <li>A login</li>
 * <li>A first name</li>
 * <li>A last name</li>
 * <li>A first name</li>
 * <li>An email</li>
 * <li>A website</li>
 * <li>A birth date</li>
 * <li>A (hashed) password</li>
 * </ul>
 * 
 * @author Tom Chassagne &amp;&amp; Ludovic Lubeigt
 */
@Entity(name = "Person")
public class Person implements Serializable {
	private static final long serialVersionUID = 1L;

    /**
     * ID of a person. Automatically generated.
     * 
     * @see Person#getIdPerson()
     */
	@Id()
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idPerson;

    /**
     * login of a person.
     * 
     * @see Person#setLogin(String)
     * @see Person#getLogin()
     */
	@Basic(optional = false)
	@Column(name = "login", length = 16, nullable = false, unique = true)
	private String login;

    /**
     * First name of a person.
     * 
     * @see Person#setFirstName(String)
     * @see Person#getFirstName()
     */
	@Basic(optional = false)
	@Column(name = "firstName", length = 64, nullable = false)
	private String firstName;

    /**
     * Last name of a person.
     * 
     * @see Person#setLastName(String)
     * @see Person#getLastName()
     */
	@Basic(optional = false)
	@Column(name = "lastName", length = 64, nullable = false)
	private String lastName;

    /**
     * email of a person.
     * 
     * @see Person#setEmail(String)
     * @see Person#getEmail()
     */
	@Basic(optional = true)
	@Column(name = "email", length = 128, nullable = false)
	private String email;

    /**
     * website of a person.
     * 
     * @see Person#setWebsite(String)
     * @see Person#getWebsite()
     */
	@Basic(optional = true)
	@Column(name = "website", length = 128)
	private String website;

    /**
     * birth date of a person.
     * 
     * @see Person#setBirthdate(Date)
     * @see Person#getBirthdate()
     */
	@Basic()
	@Temporal(TemporalType.DATE)
	@Column(name = "birthdate", nullable = false)
	private Date birthdate;

    /**
     * password of a person.
     * 
     * @see Person#setPassword(String)
     * @see Person#getPassword()
     */
	@Basic(optional = false)
	@Column(name = "password", length = 64, nullable = false)
	private String password;


    /**
     * Constructor for Person.
     */
	public Person() {
		super();
	} // Person()

    /**
     * Check a person exists in the database.
     * 
     * @param login
     *            login of the person.
     * @param passwd
     *            password of the person.
     * 
     * @see PersonDAO#existsPerson
     * 
     * @return The person if exists, null otherwise. 
     */
	public Person canConnect(String login, String passwd) {
		DAO dao = new PersonDAO();
		dao.init();
		Person p = null;
		try {
			p = dao.existsPerson(login, SHA256(passwd));
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			e.printStackTrace();
			dao.close();
		}
		dao.close();
		return p;
	} // canConnect()

    /**
     * Changes byte to hex.
     * 
     * @param b
     *            byte code.
     * 
     * @return hew string of b
     */
	private static String byteToHex(byte b) {
		char hexDigit[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f' };
		char[] array = { hexDigit[(b >> 4) & 0x0f], hexDigit[b & 0x0f] };
		return new String(array);
	} // byteToHex()

    /**
     * Hash a string using SHA256 algorithm.
     * 
     * @param text
     *            text to be hashed.
     * 
     * @see Person#byteToHex
     * 
     * @return The hash of the text as a hex String 
     * 
     * @throws NoSuchAlgorithmException
     */
	private static String SHA256(String text) throws NoSuchAlgorithmException,
			UnsupportedEncodingException {
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		byte[] hash = digest.digest(text.getBytes("UTF-8"));
		String res = "";
		for (int i = 0; i < hash.length; ++i)
			res += byteToHex(hash[i]);
		return res;
	} // SHA256()

    /**
     * Check a number is a leap year.
     * 
     * @param year
     *            year to be checked
     * 
     * @return true if it is a lear year, false otherwise. 
     */
	private static boolean isLeapYear(int year) {
		if (year % 4 == 0 && year % 100 != 0)
			return true;
		else if (year % 400 == 0)
			return true;
		return false;

	} // isLeapYear()

    /**
     * Check a birth date
     * 
     * @param date
     *            date to be checked.
     * 
     * @return true is the date is correct, false otherwise.
     */
	public boolean checkBirthDate(String date) {
		if (10 < date.length())
			return false;

		String[] parts = date.split("/");
		if (3 != parts.length)
			return false;

		int day = Integer.parseInt(parts[0]);
		int month = Integer.parseInt(parts[1]);
		int year = Integer.parseInt(parts[2]);

		if (year > Integer.parseInt(new SimpleDateFormat("yyyy")
				.format(new Date())))
			return false;

		if (month > 12 || month < 1)
			return false;

		if (day < 1)
			return false;

		if (day > 31
				&& (1 == month || 3 == month || 5 == month || 7 == month
						|| 8 == month || 10 == month || 12 == month))
			return false;
		if (day > 30 && (4 == month || 6 == month || 9 == month || 11 == month))
			return false;
		if (day > 29 && (2 == month && isLeapYear(year)))
			return false;
		if (day > 28 && 2 == month)
			return false;

		return true;
	} // checkBirthDate()

    /**
     * Check password
     * 
     * @param passwd
     *            password to be checked.
     * 
     * @return true is the password is correct.
     */
	public boolean checkPasswd(String passwd) {
		Pattern p = Pattern
				.compile("((?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%!/]).{8,32})");
		Matcher m = p.matcher(passwd);
		return m.matches();
	} // checkPasswd()

    /**
     * Check email
     * 
     * @param email
     *            email to be checked.
     * 
     * @return true is the email is correct.
     */
	public boolean checkEmail(String email) {
		Pattern p = Pattern
				.compile("(^[a-zA-Z0-9._-]+@[a-zA-Z0-9._-]{2,}\\.[a-zA-Z]{2,4}$)");
		Matcher m = p.matcher(email);
		return m.matches();
	} // checkEmail()

    /**
     * Get the person's id.
     * 
     * @return the id of a person.
     */
	public int getIdPerson() {
		return idPerson;
	} // getIdPerson()

    /**
     * Get the person's login.
     * 
     * @return the login of a person.
     */
	public String getLogin() {
		return login;
	} // getLogin()

    /**
     * Set the person's login.
     * 
     * @param login
     *            login to be set.
     */
	public void setLogin(String login) {
		this.login = login;
	} // setLogin()

    /**
     * Get the person's first name.
     * 
     * @return the first name of a person.
     */
	public String getFirstName() {
		return firstName;
	} // getFirstName()

    /**
     * Set the person's first name.
     * 
     * @param firstName
     *            first name to be set.
     */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	} // setFirstName()

    /**
     * Get the person's last name.
     * 
     * @return the last name of a person.
     */
	public String getLastName() {
		return lastName;
	} // getLastName()

    /**
     * Set the person's last name.
     * 
     * @param lastName
     *            last name to be set.
     */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	} // setLastName()

    /**
     * Get the person's email.
     * 
     * @return the email of a person.
     */
	public String getEmail() {
		return email;
	} // getEmail()

    /**
     * Set the person's email.
     * 
     * @param email
     *            email to be set.
     */
	public void setEmail(String email) {
		this.email = email;
	} // setEmail()

    /**
     * Get the person's website.
     * 
     * @return the website of a person.
     */
	public String getWebsite() {
		return website;
	} // getWebsite()

    /**
     * Set the person's website.
     * 
     * @param website
     *            website to be set.
     */
	public void setWebsite(String website) {
		this.website = website;
	} // setWebsite()

    /**
     * Get the person's birth date.
     * 
     * @return the birth date of a person.
     */
	public Date getBirthdate() {
		return birthdate;
	} // getBirthdate()

    /**
     * Set the person's birth date.
     * 
     * @param birthdate
     *            birth date to be set.
     */
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	} // setBirthdate()

    /**
     * Get the person's password.
     * 
     * @return the password of a person.
     */
	public String getPassword() {
		return password;
	} // getBirthdate()

    /**
     * Set a hash of the person's password. 
     * 
     * @param password
     *            password to be set.
     */
	public void setPassword(String password) {
		try {
			this.password = SHA256(password);
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} // setPassword()

}
