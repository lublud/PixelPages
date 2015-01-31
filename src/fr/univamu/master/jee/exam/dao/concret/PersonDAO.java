package fr.univamu.master.jee.exam.dao.concret;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import fr.univamu.master.jee.exam.beans.Person;
import fr.univamu.master.jee.exam.dao.DAO;

/**
 * <b>PersonDAO</b>
 * <p>
 * Implementation of the methods to communicate with the database.
 * </p>
 * 
 * @author Tom Chassagne &amp;&amp; Ludovic Lubeigt
 */
public class PersonDAO extends DAO {


    /**
     * Find all person in the database.
     * 
     * @see Person
     * 
     * @return List of person from the database
     */
	public List<Person> findAllPersons() {
		EntityManager em = null;
		try {
			em = getFactory().createEntityManager();
			em.getTransaction().begin();
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Person> q = cb.createQuery(Person.class);
			Root<Person> c = q.from(Person.class);
			q.select(c);
			q.orderBy(cb.asc(c.get("lastName")));
			TypedQuery<Person> res = em.createQuery(q);
			return res.getResultList();
		} finally {
			if (em != null) {
				em.close();
			}
		}

	} // findAllPersons()

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
	@Override
	public Person existsPerson(String login, String passwd) {
		EntityManager em = null;
		Person p = null;
		try {
			em = getFactory().createEntityManager();
			em.getTransaction().begin();
			Query q = em
					.createQuery("SELECT p FROM Person p WHERE login = ? and password = ?");

			q.setParameter(1, login);
			q.setParameter(2, passwd);

			p = (Person) q.getSingleResult();

			em.getTransaction().commit();
			System.err.println("findPerson: " + p.getIdPerson() + " - "
					+ p.getFirstName());
			return p;
		} catch (NoResultException e) {
			return null;
		} finally {
			if (em != null) {
				em.close();
			}
		}
	} // existsPerson()
}
