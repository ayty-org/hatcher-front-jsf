package org.ayty.bean;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.ayty.model.User;
import org.ayty.service.UserService2;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Named
@ViewScoped
public class UserBean2 implements Serializable {
	private static final long serialVersionUID = 1L;

	private User user = new User();
	private String loginReturn;
	private String username;

	@Inject
	UserService2 service;

	public String encerrarLogin() {
		System.out.println("Logout: " + getUsername());
		loginReturn = service.encerrarSessao();
		return loginReturn;
	}

	public String doLogin() {

		loginReturn = service.login(user.getLogin(), user.getPassword());
		return loginReturn;
	}

	public String postLogin() {
		service.postLogin(user.getLogin(), user.getPassword());
		loginReturn = service.login(user.getLogin(), user.getPassword());
		this.setUsername(user.getLogin());
		System.out.println("PostLogin user: " + getUsername());

		return loginReturn;
	}

}
