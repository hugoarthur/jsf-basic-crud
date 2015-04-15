package br.com.jsf.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.jsf.entity.Pessoa;

public class PessoaDao extends AbstractDao<Pessoa> {
	@Override
	public List<Pessoa> list() {
	    List<Pessoa> pessoas = new ArrayList<Pessoa>();
	    pessoas.add(new Pessoa("HA-HA-HA-HA-HA", 24));
	    pessoas.add(new Pessoa("PEPITO", 24));
		return pessoas;
	}
}
