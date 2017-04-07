package com.niit.restsample.DAO;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.restsample.model.Person;

@Repository
public class PersonDAOImpl implements PersonDAO {
	@Autowired
	private SessionFactory sessionFactory;

	public Person addPerson(Person person) {
		Session session = sessionFactory.openSession();
		session.save(person);
		session.flush();
		session.close();
		return person;
	}

	public Person getPerson(int PersonId) {
		Session session = sessionFactory.openSession();
		Person person = (Person) session.get(Person.class, PersonId);
		session.close();
		return person;
	}

	public Person updatePerson(Person person) {
		// update
		Session session = sessionFactory.openSession();
		session.update(person);
		session.flush();
		// select for the same person id
		Person updatedPerson = (Person) session.get(Person.class, person.getPersonId());
		session.close();
		return updatedPerson;
	}

	public void deletePerson(int PersonId) {
		Session session = sessionFactory.openSession();
		// select * from person where id=155
		Person person = (Person) session.get(Person.class, PersonId);
		// delete from person where id=155
		session.delete(person);
		session.flush();
		session.close();
	}
}
