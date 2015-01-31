package fr.univamu.master.jee.exam.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import fr.univamu.master.jee.exam.beans.Person;

public class PersonTest {
	Person p;

	@Before
	public void setUp() {
		p = new Person();
	} // setUp()

	@Test
	public void testBirthDate() {
		assertTrue(p.checkBirthDate("31/7/2013"));
	} // testBirthDate()

	@Test
	public void testYearBirthDate() {
		assertFalse(p.checkBirthDate("31/07/7013"));
	} // testYearBirthDate()

	@Test
	public void testMonthHighBirthDate() {
		assertFalse(p.checkBirthDate("31/17/2013"));
	} // testMonthHighBirthDate()

	@Test
	public void testMonthLowBirthDate() {
		assertFalse(p.checkBirthDate("31/00/2013"));
	} // testMonthLowBirthDate()

	@Test
	public void testDayLowBirthDate() {
		assertFalse(p.checkBirthDate("00/12/2013"));
	} // testMonthLowBirthDate()
	
	@Test
	public void testDayHigh31BirthDate() {
		assertFalse(p.checkBirthDate("32/12/2013"));
	} // testDayHigh31BirthDate()
	
	@Test
	public void testDayHigh30BirthDate() {
		assertFalse(p.checkBirthDate("31/06/2013"));
	} // testDayHigh30BirthDate()
	
	@Test
	public void testDayHigh29BirthDate() {
		assertFalse(p.checkBirthDate("30/02/2012"));
	} // testDayHigh29BirthDate()
	
	@Test
	public void testDayHigh28BirthDate() {
		assertFalse(p.checkBirthDate("29/02/2013"));
	} // testDayHigh28BirthDate()
	
	@Test(expected=NumberFormatException.class)
	public void testNotNumBirthDate() {
		p.checkBirthDate("1/jan/2012");
	} // testNotNumBirthDate()
	
	@Test
	public void testWrongFormatBirthDate() {
		assertFalse(p.checkBirthDate("29-02-2013"));
	} // testWrongFormatBirthDate()
	
	@Test
	public void testPasswd() {
		assertTrue(p.checkPasswd("superDeadlyPassw0rd!"));
	} //testPasswd()
	
	@Test
	public void testShortPasswd() {
		assertFalse(p.checkPasswd("P4$$wd"));
	} // testShortPasswd()
	
	@Test
	public void testLongPasswd() {
		assertFalse(p.checkPasswd("ThisPasswordIsWayTooLongToBeAccepted!AndAlso0+0!=1"));
	} // testLongPasswd()
	
	@Test
	public void testLowerCaseMissingPasswd() {
		assertFalse(p.checkPasswd("IMSCREAMMMM1NNNG!"));
	} // testLowerCaseMissingPasswd()
	
	@Test
	public void testUpperCaseMissingPasswd() {
		assertFalse(p.checkPasswd("imn0tscreaming##"));
	} // testUpperCaseMissingPasswd()
	
	@Test
	public void testNumberMissingPasswd() {
		assertFalse(p.checkPasswd("Password!"));
	} // testNumberMissingPasswd()
	
	@Test
	public void testSpecCharMissingPasswd() {
		assertFalse(p.checkPasswd("Passw0rd"));
	} // testSpecCharMissingPasswd()
	
	@Test
	public void testEmail() {
		assertTrue(p.checkEmail("jmlp@bakery.com"));
	} // testEmail()
	
	@Test
	public void testWrongFirstPartEmail() {
		assertFalse(p.checkEmail("=)@bakery.com"));
	} // testWrongFirstPartEmail()
	
	@Test
	public void testWrongSecondPartEmail() {
		assertFalse(p.checkEmail("jmlp@(=.com"));
	} // testWrongSecondPartEmail()
	
	@Test
	public void testWrongThirdPartEmail() {
		assertFalse(p.checkEmail("jmlp@bakery.a"));
	} // testWrongThirdPartEmail()
}
