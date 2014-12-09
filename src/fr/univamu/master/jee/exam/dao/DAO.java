package fr.univamu.master.jee.exam.dao;

import java.util.Collection;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import fr.univamu.master.jee.exam.beans.Person;

public abstract class DAO {
	
	private EntityManagerFactory factory = null;

	public void init() {
		factory = Persistence.createEntityManagerFactory("PixelPages");
	} // init()

	public void close() {
		if (factory != null) {
			factory.close();
		}
	} // close()
	
	public abstract Collection<Person> findAllPersons();

	public abstract Person findPerson(int id);

	public abstract Person updatePerson(Person p);
	
	public abstract Person addPerson(Person p);
	
	public abstract Person removePerson(int id);

	public EntityManagerFactory getFactory() {
		return factory;
	}
}
