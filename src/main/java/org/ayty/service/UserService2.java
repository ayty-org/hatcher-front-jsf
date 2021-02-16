package org.ayty.service;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.ayty.model.User;

import com.google.gson.Gson;

public class UserService2 implements Serializable {
	private static final long serialVersionUID = 1L;

	private static final String REST_URI_POST_LOGIN = "http://localhost:8080/hatcher/auth";
	private static final String REST_URI_GET_USER = "http://localhost:8080/hatcher/listUsers";
	private static final String REST_URI_POST_USER = "http://localhost:8080/hatcher/register";

	private final HttpServletRequest httpServletRequest;
	private final FacesContext facesContext;

	User userLogin = new User();

	public UserService2() {
		this.facesContext = FacesContext.getCurrentInstance();
		this.httpServletRequest = (HttpServletRequest) this.facesContext.getExternalContext().getRequest();
	}

	public String login(String username, String password) {

		userLogin.setLogin(username);
		userLogin.setPassword(password);
		if (userLogin.getLogin().isEmpty() && userLogin.getPassword().isEmpty()) {
			return "indexLogin";
		} else {
			this.httpServletRequest.getSession().setAttribute("sessionUsername", userLogin.getLogin());
			System.out.println("Sessão aberta: " + userLogin.getLogin());
			return "sucessoLogin";
		}

	}

	public String encerrarSessao() {
		this.httpServletRequest.getSession().removeAttribute("sessionUsername");
		this.httpServletRequest.getSession().invalidate();
		return "indexLogin";
	}

	private String toJsonLogin(String username, String password) {
		User userLogin = new User();
		userLogin.setLogin(username);
		userLogin.setPassword(password);

		String userLoginJson = new Gson().toJson(userLogin);

		return userLoginJson;
	}

	public void postLogin(String username, String password) {
		String userLoginJson = toJsonLogin(username, password);
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(REST_URI_POST_LOGIN);
		client.target(REST_URI_POST_LOGIN).request(MediaType.APPLICATION_JSON)
				.post(Entity.entity(userLoginJson, MediaType.APPLICATION_JSON));

	}

}