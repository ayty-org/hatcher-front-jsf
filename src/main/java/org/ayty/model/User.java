package org.ayty.model;

import lombok.Data;

@Data
public class User {
	private String login;
	private String fullname;
	private String email;

	private String password;
	private String image;
	private String perfil;
	private Boolean admin;
}