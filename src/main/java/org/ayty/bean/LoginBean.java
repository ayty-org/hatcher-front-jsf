package org.ayty.bean;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.ayty.model.Login;
import org.ayty.service.LoginService;

import lombok.Data;

@Data
@Named
@ViewScoped
public class LoginBean implements Serializable {
	private static final long serialVersionUID = 1L;


	private Login login = new Login();

	@Inject
	private LoginService loginService;

	public void postLogin() {
		loginService.postLogin(login.getUsername(), login.getPassword());
	}

}