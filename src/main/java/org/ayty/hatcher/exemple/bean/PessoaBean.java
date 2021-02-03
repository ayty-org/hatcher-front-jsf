package org.ayty.hatcher.exemple.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.ayty.hatcher.exemple.model.Pessoa;
import org.ayty.hatcher.exemple.service.PessoaService;

@Named
@ViewScoped
public class PessoaBean implements Serializable{

	@Inject
	PessoaService service;
	
	public List<Pessoa> getPessoas() {
		return service.getPessoas();
	}
}
