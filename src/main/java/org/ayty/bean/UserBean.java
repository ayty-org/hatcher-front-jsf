package org.ayty.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.ayty.model.User;
import org.ayty.service.UserService;

import lombok.Getter;

@Getter
@Named
@ViewScoped
public class UserBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private User user = new User();

	@Inject
	UserService service;

	public List<User> getUsers() {
		return service.getUsers();

	}

	public String postUser() {
		service.postUser(user.getLogin(), user.getPassword(), user.getEmail(), user.getFullName(), user.getImage());
		return "successSignup";
	}

	public String postLogin() {
		service.postLogin(user.getLogin(), user.getPassword());
		return "index";
	}

}
