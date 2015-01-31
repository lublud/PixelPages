package fr.univamu.master.jee.exam.beans;

import java.util.List;

import fr.univamu.master.jee.exam.dao.DAO;
import fr.univamu.master.jee.exam.dao.concret.PersonDAO;

/**
 * <b>ListPerson</b>
 * <p>
 * List of person existing in the database.
 * 
 * @author Tom Chassagne &amp;&amp; Ludovic Lubeigt
 */
public class ListPerson {
	
    /**
     * A list of Person
     * 
     * @See Person
     * @see ListPerson#listPerson()
     * @see ListPerson#getListPerson()
     */
	private List<Person> listPerson;
	
    /**
     * Constructor for Person.
     * <p>
     * Connect to the data base and find all persons.
     * Set listPerson with the retrieved list.
     * 
     * @see PersonDAO#findAllPersons()
     */
	public ListPerson() {
		DAO dao = new PersonDAO();
		dao.init();
		listPerson = dao.findAllPersons();
	}

    /**
     * get the list of persons.
     * 
     * @return the list of persons.
     */
	public List<Person> getListPerson() {
		return listPerson;
	}
	
}
