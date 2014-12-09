package fr.univamu.master.jee.exam.test;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import fr.univamu.master.jee.exam.beans.Person;
import fr.univamu.master.jee.exam.dao.DAO;
import fr.univamu.master.jee.exam.dao.concret.PersonDAO;

public class PersonDAOTest {

	static DAO dao;
	Person p;

	@BeforeClass
	public static void beforeAll() {
		dao = new PersonDAO();
		dao.init();
	} // beforeAll()

	@AfterClass
	public static void afterAll() {
		dao.close();
	} // afterAll()

	@Before
	public void setUp() throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
		p = new Person();
		p.setFirstName("Jean-Marie");
		p.setLastName("Le Pain");
		p.setBirthdate(simpleDateFormat.parse("02/29/1992"));
		p.setEmail("jmlp@bakery.com");
		p.setPassword("superDeadlyPassword");
	} // setUp()

	@Test
	public void testAddFindPerson() {
		dao.addPerson(p);
		assertEquals(p.getIdPerson(), dao.findPerson(p.getIdPerson()).getIdPerson());
	}

}
