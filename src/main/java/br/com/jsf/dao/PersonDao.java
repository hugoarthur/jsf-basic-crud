package br.com.jsf.dao;

import br.com.jsf.entity.Person;

public class PersonDao extends AbstractDao<Person> {

	public PersonDao() {
		super(Person.class);
	}

}
