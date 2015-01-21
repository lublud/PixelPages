package fr.univamu.master.jee.exam.beans;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

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
	@Column(name = "lastName", length = 26400, nullable = false)
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

	private static String SHA256(String text) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		byte[] hash = digest.digest(text.getBytes("UTF-8"));
		String res = "";
		for (int i = 0; i < hash.length; ++i)
			res += byteToHex(hash[i]);
		return res;
	} // SHA256()

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
