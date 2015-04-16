package br.com.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.jsf.dao.PersonDao;
import br.com.jsf.entity.Person;

@Stateless
public class PersonService {

	@Inject
	private PersonDao dao;

	public Person create(Person person) {
		dao.create(person);
		return person;
	}

	public Person update(Person person) {
		dao.update(person);
		return person;
	}

	public Person find(Person person) {
		dao.find(person);
		return person;
	}

	public Person delete(Person person) {
		dao.delete(person);
		return person;
	}

	public List<Person> list() {
		return dao.list();
	}
}
