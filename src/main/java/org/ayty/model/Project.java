package org.ayty.model;

import lombok.Data;

@Data
public class Project {
	
	private Long id;
	private String name;
	private String description;
	private String logo;
	private String startDate;
	private String endDate;
}
