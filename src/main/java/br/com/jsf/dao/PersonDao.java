package br.com.jsf.dao;

import javax.inject.Named;

import br.com.jsf.entity.Person;

@Named
public class PersonDao extends AbstractDao<Person> {

	public PersonDao() {
		super(Person.class);
	}

}
