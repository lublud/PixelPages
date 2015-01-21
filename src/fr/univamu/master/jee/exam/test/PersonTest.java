package fr.univamu.master.jee.exam.test;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import fr.univamu.master.jee.exam.beans.Person;
import fr.univamu.master.jee.exam.exception.DateException;

public class PersonTest {
	Person p;

	@Before
	public void setUp() {
		p = new Person();
	} // setUp()

	@Test
	public void testBirthDate() throws DateException {
		assertTrue(p.checkBirthDate("31/7/2013"));
	} // testBirthDate()

	@Test(expected = DateException.class)
	public void testYearBirthDate() throws DateException {
		p.checkBirthDate("31/07/7013");
	} // testYearBirthDate()

	@Test(expected = DateException.class)
	public void testMonthHighBirthDate() throws DateException {
		p.checkBirthDate("31/17/2013");
	} // testMonthHighBirthDate()

	@Test(expected = DateException.class)
	public void testMonthLowBirthDate() throws DateException {
		p.checkBirthDate("31/00/2013");
	} // testMonthLowBirthDate()

	@Test(expected = DateException.class)
	public void testDayLowBirthDate() throws DateException {
		p.checkBirthDate("00/12/2013");
	} // testMonthLowBirthDate()
	
	@Test(expected = DateException.class)
	public void testDayHigh31BirthDate() throws DateException {
		p.checkBirthDate("32/12/2013");
	} // testDayHigh31BirthDate()
	
	@Test(expected = DateException.class)
	public void testDayHigh30BirthDate() throws DateException {
		p.checkBirthDate("31/06/2013");
	} // testDayHigh30BirthDate()
	
	@Test(expected = DateException.class)
	public void testDayHigh29BirthDate() throws DateException {
		p.checkBirthDate("30/02/2012");
	} // testDayHigh29BirthDate()
	
	@Test(expected = DateException.class)
	public void testDayHigh28BirthDate() throws DateException {
		p.checkBirthDate("29/02/2013");
	} // testDayHigh28BirthDate()
	
	@Test(expected = NumberFormatException.class)
	public void testNotNumBirthDate() throws DateException {
		p.checkBirthDate("1/jan/2012");
	} // testNotNumBirthDate()
	
	@Test(expected = DateException.class)
	public void testWrongFormatBirthDate() throws DateException {
		p.checkBirthDate("29-02-2013");
	} // testWrongFormatBirthDate()
}
