package org.ayty.model;

import java.time.LocalDate;

import lombok.Data;

@Data
public class Project {
	
	private Long id;
	private String name;
	private String description;
	private String logo;
	private LocalDate startDate;
	private LocalDate endDate;
}
