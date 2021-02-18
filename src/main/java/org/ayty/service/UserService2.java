package org.ayty.service;

import java.io.Serializable;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.ayty.model.AccessToken;
import org.ayty.model.User;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

public class UserService2 implements Serializable {
	private static final long serialVersionUID = 1L;

	private static final String REST_URI_POST_LOGIN = "http://localhost:8080/api/v1/hatcher/auth";
	private static final String REST_URI_GET_USERS = "http://localhost:8080/api/v1/hatcher/listUsers";
	private static final String REST_URI_POST_USER = "http://localhost:8080/api/v1/hatcher/register";

	private final HttpServletRequest httpServletRequest;
	private final FacesContext facesContext;

	User userLogin = new User();

	AccessToken accessToken = new AccessToken();

	public UserService2() {
		this.facesContext = FacesContext.getCurrentInstance();
		this.httpServletRequest = (HttpServletRequest) this.facesContext.getExternalContext().getRequest();
	}

	public String login(String username, String password) {

		userLogin.setLogin(username);
		userLogin.setPassword(password);
		System.out.println(userLogin.toString());
		if (userLogin.getLogin().isEmpty() || userLogin.getPassword().isEmpty()) {
			return "indexLogin";
		} else {
			this.httpServletRequest.getSession().setAttribute("sessionUsername", userLogin.getLogin());
			System.out.println("Sess�o aberta: " + userLogin.getLogin());
			return "indexLogout";
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

	public String postLogin(String username, String password) {
		String userLoginJson = toJsonLogin(username, password);
		Client client = ClientBuilder.newClient();

		Response response = client.target(REST_URI_POST_LOGIN).request(MediaType.APPLICATION_JSON)
				.post(Entity.entity(userLoginJson, MediaType.APPLICATION_JSON));

		if (response.getStatus() == Status.UNAUTHORIZED.getStatusCode()) {
//			accessToken = new Gson().fromJson(response.readEntity(String.class), AccessToken.class);
			return "";
		} else {
			accessToken = new Gson().fromJson(response.readEntity(String.class), AccessToken.class);
			return accessToken.getToken();
		}
	}

	public List<User> getUsers() {

		Client client = ClientBuilder.newClient();

		String URL = REST_URI_GET_USERS;

		Response response = client.target(URL).request(MediaType.APPLICATION_JSON).get();

		String json = response.readEntity(String.class);

		JsonObject convertedObject = new Gson().fromJson(json, JsonObject.class);

		System.out.println(convertedObject.get("content"));

		response.close();

		List<User> list = new Gson().fromJson(convertedObject.get("content"), new TypeToken<List<User>>() {
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

		return userRegisterJson;
	}

	public void postUser(String token, String username, String password, String email, String fullname, String image) {

		String userRegisterJson = toJson(username, password, email, fullname, image);
		Client client = ClientBuilder.newClient();

		Response response = client.target(REST_URI_POST_USER).request(MediaType.APPLICATION_JSON)
				.header("Authorization", token).post(Entity.entity(userRegisterJson, MediaType.APPLICATION_JSON));

	}

}