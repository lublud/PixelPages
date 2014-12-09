package fr.univamu.master.jee.exam.dao.concret;

import java.util.Collection;

import javax.persistence.EntityManager;

import fr.univamu.master.jee.exam.beans.Person;
import fr.univamu.master.jee.exam.dao.DAO;

public class PersonDAO extends DAO {

	public Collection<Person> findAllPersons() {
		// TODO Auto-generated method stub
		return null;
	} // findAllPersons()

	public Person findPerson(int id) {
		EntityManager em = null;
		Person p = null;
		try {
			em = getFactory().createEntityManager();
			em.getTransaction().begin();
			p = em.find(Person.class, id);
			em.getTransaction().commit();
			System.err.println("findPerson: " + p.getIdPerson() + " - "
					+ p.getFirstName());
			return p;
		} finally {
			if (em != null) {
				em.close();
			}
		}

	} // findPerson()

	public Person updatePerson(Person p) {
		EntityManager em = null;
		try {
			em = getFactory().createEntityManager();
			em.getTransaction().begin();
			em.merge(p);
			em.getTransaction().commit();
			System.err.println("updatePerson with id = " + p.getIdPerson());
			return p;
		} finally {
			if (em != null) {
				em.close();
			}
		}

	} // updatePerson()

	@Override
	public Person addPerson(Person p) {
		EntityManager em = null;
		try {
			em = getFactory().createEntityManager();
			em.getTransaction().begin();
			em.persist(p);
			em.getTransaction().commit();
			System.err.println("addPerson with id = " + p.getIdPerson());
			return p;
		} finally {
			if (em != null) {
				em.close();
			}
		}

	} // addPerson()

	@Override
	public Person removePerson(int id) {
		EntityManager em = null;
		Person p = null;
		try {
			em = getFactory().createEntityManager();
			p = em.find(Person.class, id);
			em.getTransaction().begin();
			em.remove(p);
			em.getTransaction().commit();
			System.err.println("removePerson: " + p.getIdPerson() + " - "
					+ p.getFirstName());
			return p;
		} finally {
			if (em != null) {
				em.close();
			}
		}
	} // removePerson()

}
