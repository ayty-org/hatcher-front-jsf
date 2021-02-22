package org.ayty.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.ayty.model.AccessToken;
import org.ayty.model.User;
import org.ayty.service.UserService2;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@ManagedBean
@SessionScoped
public class UserBean2 implements Serializable {
	private static final long serialVersionUID = 1L;

	private User user = new User();
	private String loginReturn;
	private String username;

	private Integer idUsuario;

	private AccessToken accessToken = new AccessToken();
	private String token;

	@Inject
	UserService2 service;

	public String encerrarLogin() {
		loginReturn = service.encerrarSessao();
		return loginReturn;
	}

	public String postLogin() {
		token = service.postLogin(user.getLogin(), user.getPassword());
		loginReturn = service.loginSession(user.getLogin(), user.getPassword());
		this.setUsername(user.getLogin());

		return loginReturn;
	}

	public List<User> getUsers() {
		return service.getUsers();

	}

	public String postUser() {
		service.postUser(token, user.getLogin(), user.getPassword(), user.getEmail(), user.getFullname(),
				user.getImage());
		return "indexLogout";
	}

	public String postUserAndDoLogin() {
		service.postUser(token, user.getLogin(), user.getPassword(), user.getEmail(), user.getFullname(),
				user.getImage());
		token = service.postLogin(user.getLogin(), user.getPassword());
		loginReturn = service.loginSession(user.getLogin(), user.getPassword());
		this.setUsername(user.getLogin());
		return loginReturn;
	}

	public String deleteUser() {
		int id = Integer.parseInt(
				FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idUsuario"));
		service.deleteUser(id, token);

		return "userpage";
	}

	public String getIdAndRedirectToUpdateUser() {
		idUsuario = Integer.parseInt(
				FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idUsuario"));
		setIdUsuario(idUsuario);
		return "updateUser";
	}

	public String updateUser() {
		service.updateUser(idUsuario, token, user.getLogin(), user.getPassword(), user.getEmail(), user.getFullname(),
				user.getImage());

		return "userpage";

	}

}
