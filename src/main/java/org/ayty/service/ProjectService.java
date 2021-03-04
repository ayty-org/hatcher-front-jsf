package org.ayty.service;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.ayty.model.Project;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class ProjectService {

	private static final String REST_URI_POST_PROJECT = "http://localhost:8080/api/v1/hatcher/projects";
	private static final String REST_URI_GET_PROJECTS = "http://localhost:8080/api/v1/hatcher/projects";
	private static final String REST_URI_GET_PROJECT = "http://localhost:8080/api/v1/hatcher/projects/";
	private static final String REST_URI_PUT_PROJECT = "http://localhost:8080/api/v1/hatcher/projects/";
	private static final String REST_URI_DELETE_PROJECT = "http://localhost:8080/api/v1/hatcher/projects/";

	public List<Project> getProjects() {

		Client client = ClientBuilder.newClient();

		String URL = REST_URI_GET_PROJECTS;
		
		WebTarget target = client.target(URL);
		
		Response response = target.request().get();
		
		String json = response.readEntity(String.class);
		
		response.close();

		List<Project> list = new Gson().fromJson(json, new TypeToken<List<Project>>() {
		}.getType());
		return list;
	}

	public void postProject(String token, String name, String description, String logo, String startDate,
			String endDate) {

		String userRegisterJson = toJson(name, description, logo, startDate, endDate);
		Client client = ClientBuilder.newClient();

		Response response = client.target(REST_URI_POST_PROJECT).request(MediaType.APPLICATION_JSON)
				.header("Authorization", token).post(Entity.entity(userRegisterJson, MediaType.APPLICATION_JSON));
		response.close();

	}

	private String toJson(String name, String description, String logo, String startDate, String endDate) {
		Project project = new Project();

		project.setName(name);
		project.setDescription(description);
		project.setLogo(logo);
		project.setStartDate(startDate);
		project.setEndDate(endDate);

		String projectRegisterJson = new Gson().toJson(project);

		return projectRegisterJson;
	}

	public void deleteProject(int id, String token) {

		Client client = ClientBuilder.newClient();

		Response response = client.target(REST_URI_DELETE_PROJECT + id).request(MediaType.APPLICATION_JSON)
				.header("Authorization", token).delete();
		response.close();

	}

	public void updateProject(Long id, String token, String name, String description, String logo, String startDate,
			String endDate) {

		String projectRegisterJson = toJson(name, description, logo, startDate, endDate);
		Client client = ClientBuilder.newClient();

		Response response = client.target(REST_URI_PUT_PROJECT + id).request(MediaType.APPLICATION_JSON)
				.header("Authorization", token).put(Entity.entity(projectRegisterJson, MediaType.APPLICATION_JSON));
		response.close();
	}

	public Project getProjectById(Long id) {

		Client client = ClientBuilder.newClient();

		Response response = client.target(REST_URI_GET_PROJECT + id).request(MediaType.APPLICATION_JSON).get();
		Project project = new Gson().fromJson(response.readEntity(String.class), Project.class);
		response.close();
		System.out.println("project --->" + project.toString());
		return project;
	}

}
