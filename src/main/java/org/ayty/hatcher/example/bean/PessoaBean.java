package org.ayty.hatcher.example.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.ayty.hatcher.example.model.Pessoa;
import org.ayty.hatcher.example.service.PessoaService;

@Named
@ViewScoped
public class PessoaBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	PessoaService service;

	public List<Pessoa> getPessoas() {
		return service.getPessoas();
	}
}
