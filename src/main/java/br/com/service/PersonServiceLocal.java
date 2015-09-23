package br.com.service;

import java.util.List;

import javax.ejb.Local;

import br.com.jsf.entity.Person;

@Local
public interface PersonServiceLocal {

	public Person create(Person person);

	public Person update(Person person);

	public Person find(Person person);

	public Person delete(Person person);

	public List<Person> list();

}
