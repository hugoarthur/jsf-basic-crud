package br.com.jsf.mb;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import br.com.jsf.dao.PessoaDao;
import br.com.jsf.entity.Pessoa;

@ManagedBean
@SessionScoped
public class PessoaMB {
	private Integer id;
	private String name;
	private Integer age;

	private List<Pessoa> pessoas;
	private Pessoa pessoaExcluida;

	@Inject
	private PessoaDao dao;

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

	public List<Pessoa> getPessoas() {
		if (this.pessoas == null) {
			this.pessoas = new ArrayList<Pessoa>();
		}
		return this.pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

	public Pessoa getPessoaExcluida() {
		return pessoaExcluida;
	}

	public void setPessoaExcluida(Pessoa pessoaExcluida) {
		this.pessoaExcluida = pessoaExcluida;
	}

	public String cadastrar() {
		Pessoa pessoa = new Pessoa(this.name, this.age);
		new PessoaDao().create(pessoa);
		getPessoas().add(pessoa);
		return "index.xhtml";
	}

	public void consultar() {
		getPessoas().addAll(new PessoaDao().list());
	}

	public void deletar() {
		getPessoas().remove(this.pessoaExcluida);
		this.pessoaExcluida = null;
	}

}
