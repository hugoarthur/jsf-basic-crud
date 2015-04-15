package br.com.jsf.mb;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import br.com.jsf.dao.PersonDao;
import br.com.jsf.entity.Person;

@ManagedBean
@ViewScoped
public class PersonMB {
	private Integer id;
	private String name;
	private Integer age;

	private List<Person> people;
	private Person deletedPerson;

	@Inject
	private PersonDao dao;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public List<Person> getPeople() {
		if (this.people == null) {
			this.people = new ArrayList<Person>();
		}
		return this.people;
	}

	public void setPeople(List<Person> people) {
		this.people = people;
	}

	public Person getDeletedPerson() {
		return deletedPerson;
	}

	public void setDeletedPerson(Person deletedPerson) {
		this.deletedPerson = deletedPerson;
	}

	public String create() {
		Person person = new Person(this.name, this.age);
		dao.create(person);
		getPeople().add(person);
		return "index.xhtml";
	}

	public void list() {
		this.people = dao.list();
	}

	public void delete() {
		getPeople().remove(this.deletedPerson);
		this.deletedPerson = null;
	}

}
