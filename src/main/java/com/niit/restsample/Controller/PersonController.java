package com.niit.restsample.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.niit.restsample.DAO.PersonDAO;
import com.niit.restsample.Service.PersonService;
import com.niit.restsample.model.Person;
import com.niit.restsample.model.Error;

@Controller
public class PersonController {
	@Autowired
	private PersonDAO personDao;

	@RequestMapping(value = "/person", method = RequestMethod.POST)
	// RequestBody - client to server - JSON to java object
	// ResponseBody - server to client - Java object to JSON
	public @ResponseBody Person savePerson(@RequestBody Person person) {
		return personDao.addPerson(person);
	}

	@RequestMapping(value = "/person/{PersonId}", method = RequestMethod.GET)
	// ? - Any Type of Object
	public ResponseEntity<?> getPerson(@PathVariable(value = "PersonId") int PersonId) {
		Person person = personDao.getPerson(PersonId);
		if (person == null) {
			Error error = new Error(1, "Person Id [" + PersonId + "]" + " doesn't exists");
			return new ResponseEntity<Error>(error, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Person>(person, HttpStatus.OK);

	}

	@RequestMapping(value = "/person", method = RequestMethod.PUT)
	public ResponseEntity<?> updatePerson(@RequestBody Person person) {
		Person currentPerson = personDao.getPerson(person.getPersonId());
		if (currentPerson == null) {
			Error error = new Error(1, "Person Id [" + person.getPersonId() + "]" + " doesn't exists");
			return new ResponseEntity<Error>(error, HttpStatus.NOT_FOUND);
		}
		Person updatedPerson = personDao.updatePerson(person);
		return new ResponseEntity<Person>(updatedPerson, HttpStatus.OK);

	}

	@RequestMapping(value = "/person/{PersonId}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deletePerson(@PathVariable int PersonId) {
		Person person = personDao.getPerson(PersonId);
		if (person == null) {
			Error error = new Error(1, "Person Id [" + PersonId + "]" + " doesn't exists");
			return new ResponseEntity<Error>(error, HttpStatus.NOT_FOUND);
		}
		personDao.deletePerson(PersonId);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
