package org.ayty.service;

import java.io.Serializable;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.ayty.model.User;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class UserService implements Serializable {
	private static final long serialVersionUID = 1L;

	private static final String REST_URI_POST_LOGIN = "http://localhost:8080/hatcher/auth";
	private static final String REST_URI_GET_USER = "http://localhost:8080/hatcher/listUsers";
	private static final String REST_URI_POST_USER = "http://localhost:8080/hatcher/register";

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

	public List<User> getUsers() {

		Client client = ClientBuilder.newClient();

		String URL = REST_URI_GET_USER;

		WebTarget target = client.target(URL);

		Response response = target.request().get();

		String json = response.readEntity(String.class);
		response.close();

		List<User> list = new Gson().fromJson(json, new TypeToken<List<User>>() {
		}.getType());
		return list;
	}

	private String toJson(String username, String password, String email, String fullname, String image) {

		User user = new User();
		user.setProfile("ALUNO");
		user.setAdmin(false);
		user.setEmail(email);
		user.setFullName(fullname);
		user.setImage(image);
		user.setPassword(password);
		user.setLogin(username);

		String userRegisterJson = new Gson().toJson(user);
		System.out.println(userRegisterJson);

		return userRegisterJson;
	}

	public void postUser(String username, String password, String email, String fullname, String image) {
		String userRegisterJson = toJson(username, password, email, fullname, image);
		System.out.println(userRegisterJson);
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(REST_URI_POST_USER);
		client.target(REST_URI_POST_USER).request(MediaType.APPLICATION_JSON)
				.post(Entity.entity(userRegisterJson, MediaType.APPLICATION_JSON));

	}
}