package fr.univamu.master.jee.exam.beans;

import java.util.List;

import fr.univamu.master.jee.exam.dao.DAO;
import fr.univamu.master.jee.exam.dao.concret.PersonDAO;

public class ListPerson {
	private List<Person> listPerson;
	
	public ListPerson() {
		DAO dao = new PersonDAO();
		dao.init();
		listPerson = dao.findAllPersons();
	}

	public List<Person> getListPerson() {
		return listPerson;
	}
	
}
