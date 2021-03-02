package org.ayty.bean;

import java.time.LocalDate;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.ayty.model.Project;
import org.ayty.service.ProjectService;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ManagedBean
@SessionScoped
public class ProjectBean {

	@Inject
	ProjectService service;
	private String token;
	private Project project = new Project();
	private Project projectUpdate = new Project();
	private Long idProject;
	private Project showProject = new Project();

	public List<Project> getProjects() {
		return service.getProjects();
	}

	public String postProject() {
		service.postProject(token, project.getName(), project.getDescription(), project.getLogo(),
				project.getStartDate(), project.getEndDate());
		return "pageProjects";
	}

	public String deleteProject() {
		int id = Integer.parseInt(
				FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idProject"));
		service.deleteProject(id, token);

		return "userpage";
	}

	public String getIdAndRedirectToUpdateProject() {

		projectUpdate.setId(Long.parseLong(
				FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idProject")));
		projectUpdate
				.setName(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("name"));
		projectUpdate.setDescription(
				FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("description"));
		projectUpdate
				.setLogo(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("logo"));
		projectUpdate.setStartDate(LocalDate.parse(
				FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("satarDate")));
		projectUpdate.setEndDate(LocalDate
				.parse(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("endDate")));

		return "updateProject";
	}
	
//lEMBRAR DE APAGAR	
	public String testeVW() {
		return "viewProject";
	}
	
	//Apagar depois
	public String testeUp() {
		return "updateProject";
	}

	public String updateProject() {
		service.updateProject(projectUpdate.getId(), token, projectUpdate.getName(), projectUpdate.getDescription(),
				projectUpdate.getLogo(), projectUpdate.getStartDate(), projectUpdate.getEndDate());

		return "userpage";

	}

	public String getProjectById() {

		idProject = Long.parseLong(
				FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idProject"));
		setShowProject(service.getProjectById(idProject));
		return "viewProject";
	}

	public void atualizarSucesso() {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Projeto atualizado", "Projeto atualizado com sucesso"));
	}

	public void deleteUserSucesso() {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Projeto deletado", "Projeto deletado com sucesso"));
	}

	public void sucessoCadastro() {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Projeto cadastrado", "Projeto cadastrado com sucesso"));
	}

}
