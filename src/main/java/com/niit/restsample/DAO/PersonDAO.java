package com.niit.restsample.DAO;

import java.util.List;

import com.niit.restsample.model.Person;

public interface PersonDAO {
	Person addPerson(Person person);

	Person getPerson(int PersonId);

	Person updatePerson(Person person);

	void deletePerson(int PersonId);

}
