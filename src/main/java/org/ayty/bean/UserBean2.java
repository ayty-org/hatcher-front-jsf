package org.ayty.bean;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.ayty.model.AccessToken;
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

	private AccessToken accessToken = new AccessToken();
	private String token;

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

	public void setAccessToken(AccessToken accessToken) {
		this.accessToken = accessToken;
	}

	public String postLogin() {
		token = service.postLogin(user.getLogin(), user.getPassword());
		System.out.println("Post login token: " + token);
		loginReturn = service.login(user.getLogin(), user.getPassword());
		this.setUsername(user.getLogin());

		return loginReturn;
	}

	public List<User> getUsers() {
		return service.getUsers();

	}

	public String postUser() {
		System.out.println("postUser token: " + token);
		service.postUser(token, user.getLogin(), user.getPassword(), user.getEmail(), user.getFullName(),
				user.getImage());
		return "indexLogout";
	}
	
	public String deleteUser() {
		//List<User> users = service.getUsers().stream().filter(u-> u.getLogin().equals(user.getLogin())).collect(Collectors.toList());
		//System.out.println("Bean: "+users.size());
		service.deleteUser(service.getUsers().stream().filter(u-> u.getLogin().equals(user.getLogin())).collect(Collectors.toList()), user.getLogin());
		
		return "userpage";
	}

}
