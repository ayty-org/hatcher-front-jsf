package org.ayty.service;

import java.io.Serializable;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.ayty.model.Login;

import com.google.gson.Gson;

public class LoginService implements Serializable {
	private static final long serialVersionUID = 1L;

	private static final String REST_URI = "http://localhost:3000/users";
	private Client client;

	public void init() {
		client = ClientBuilder.newClient();
	}

	private String toJson(String username, String password) {
		Login userLogin = new Login();
		userLogin.setUsername(username);
		userLogin.setPassword(password);

		String userLoginJson = new Gson().toJson(userLogin);

		return userLoginJson;
	}

	public void postLogin(String username, String password) {
		String userLoginJson = toJson(username, password);
		init();
		WebTarget target = client.target(REST_URI);
		client.target(REST_URI).request(MediaType.APPLICATION_JSON)
				.post(Entity.entity(userLoginJson, MediaType.APPLICATION_JSON));

	}

}
