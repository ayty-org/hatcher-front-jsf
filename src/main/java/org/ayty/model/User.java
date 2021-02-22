package org.ayty.model;

import lombok.Data;

@Data
public class User {
	private Integer id;
	private String login;
	private String password;
	private String email;
	private String fullname;
	private String image;
	private String profile;
}