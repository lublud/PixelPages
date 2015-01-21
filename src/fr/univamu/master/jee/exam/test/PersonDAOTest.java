package fr.univamu.master.jee.exam.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import fr.univamu.master.jee.exam.beans.Person;
import fr.univamu.master.jee.exam.dao.DAO;
import fr.univamu.master.jee.exam.dao.concret.PersonDAO;

public class PersonDAOTest {

	static DAO dao;
	Person p, pp;

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
		p.setLogin("lepain");
		p.setFirstName("Jean-Marie");
		p.setLastName("Le Pain");
		p.setBirthdate(simpleDateFormat.parse("02/29/1992"));
		p.setEmail("jmlp@bakery.com");
		p.setPassword("superDeadlyPassword");
		
		pp = new Person();
		pp.setLogin("jean");
		pp.setFirstName("Jean");
		pp.setLastName("Jean");
		pp.setBirthdate(simpleDateFormat.parse("02/29/1992"));
		pp.setEmail("jeanjean@bakery.com");
		pp.setPassword("superDeadlyPassword");
	} // setUp()

	@Test
	public void testAddFindPerson() {
		p = dao.addPerson(p);
		int tmp = dao.findPerson(p.getIdPerson()).getIdPerson();
		dao.removePerson(p.getIdPerson());
		assertEquals(p.getIdPerson(), tmp);
	} // testAddFindPerson()
	
	@Test
	public void testUpdatePerson() {
		String website = "jmlp.com";
		p = dao.addPerson(p);
		p.setWebsite(website);
		p = dao.updatePerson(p);
		String tmp = dao.findPerson(p.getIdPerson()).getWebsite();
		dao.removePerson(p.getIdPerson());
		assertEquals(website, tmp);
	} // testUpdatePerson()
	
	@Test
	public void testRemovePerson() {
		int tmp = -1;
		p = dao.addPerson(p);
		tmp = p.getIdPerson();
		p = dao.removePerson(p.getIdPerson());
		assertEquals(tmp, p.getIdPerson());
	} // testRemovePerson()
	
	@Test
	public void testFindAllPersons() {
		int tmp = dao.findAllPersons().size();
		p = dao.addPerson(p);
		pp = dao.addPerson(pp);
		List<Person> list = dao.findAllPersons();
		p = dao.removePerson(p.getIdPerson());
		pp = dao.removePerson(pp.getIdPerson());
		assertEquals(2, list.size() - tmp);
	} // testFindAllPersons()
	
	@Test
	public void testExistsPerson() {
		int tmp = -1;
		p = dao.addPerson(p);
		tmp = p.getIdPerson();
		p = dao.existsPerson(p.getLogin(), p.getPassword());
		int tmpp = p.getIdPerson();
		p = dao.removePerson(p.getIdPerson());
		assertEquals(tmp, tmpp);
	} // testExistsPerson()

	@Test
	public void testDoesNotExistPerson() {
		int tmp = -1;
		p = dao.addPerson(p);
		tmp = p.getIdPerson();
		p = dao.existsPerson(p.getLogin(), "PasBon!");
		dao.removePerson(tmp);
		assertNull(p);
	} // testDoesNotExistPerson()
	

}
