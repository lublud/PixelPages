package fr.univamu.master.jee.exam.dao;

import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import fr.univamu.master.jee.exam.beans.Person;

/**
 * <b>DAO</b>
 * <p>
 * List the methods (abstract) useful to communicate with the database. <br>
 * Uses: 
 * <ul>
 * <li>An entity manager</li>
 * </ul>
 * 
 * @author Tom Chassagne &amp;&amp; Ludovic Lubeigt
 */
public abstract class DAO {
	
    /**
     * EntityManagerFactory.
     * 
     * @see Person#getIdPerson()
     * @see DAO#getFactory()
     */
	private EntityManagerFactory factory = null;

    /**
     * Used to connect with the database as explained in persistence.xml.
     */
	public void init() {
		factory = Persistence.createEntityManagerFactory("PixelPages");
	} // init()

    /**
     * Close the connection.
     */
	public void close() {
		if (factory != null) {
			factory.close();
		}
	} // close()
	
    /**
     * Find all person in the database.
     * 
     * @see Person
     * 
     * @return List of person from the database
     */
	public abstract List<Person> findAllPersons();

    /**
     * Find a specific person in the database.
     * 
     * @param id
     *            id of the person to be searched
     * 
     * @see Person
     * 
     * @return The person if exists, null otherwise. 
     */
	public abstract Person findPerson(int id);

    /**
     * Update a specific person in the database.
     * 
     * @param p
     *            The person to be updated
     * 
     * @see Person
     * 
     * @return The updated person 
     */
	public abstract Person updatePerson(Person p);
	
    /**
     * Add a specific person in the database.
     * 
     * @param p
     *            The person to be added
     * 
     * @see Person
     * 
     * @return The added person 
     */
	public abstract Person addPerson(Person p);
	
    /**
     * Remove a specific person in the database.
     * 
     * @param id
     *            id of the person to be searched
     * 
     * @see Person
     * 
     * @return The removed person 
     */
	public abstract Person removePerson(int id);
	
    /**
     * Check a person exists in the database.
     * 
     * @param login
     *            login of the person.
     * @param passwd
     *            password of the person.
     * 
     * @see Person
     * 
     * @return The person if exists, null otherwise. 
     */
	public abstract Person existsPerson(String login, String passwd);

    /**
     * Get the entity manager factory.
     * 
     * @return factory.
     */
	public EntityManagerFactory getFactory() {
		return factory;
	}
}
