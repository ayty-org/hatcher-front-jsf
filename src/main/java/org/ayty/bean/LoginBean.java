package org.ayty.bean;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.ayty.service.LoginService;

import lombok.Data;

@Data
@Named
@ViewScoped
public class LoginBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private String username;
	private String password;
	@Inject
	private LoginService loginService;

	public void postLogin() {
		loginService.postLogin(username, password);
	}

}