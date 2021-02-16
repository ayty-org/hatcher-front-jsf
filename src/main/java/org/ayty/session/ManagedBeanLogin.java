package org.ayty.session;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ManagedBean
@SessionScoped
public class ManagedBeanLogin {

	private String username;
	private String senha;

	private final HttpServletRequest httpServletRequest;
	private final FacesContext facesContext;
	private FacesMessage facesMessage;

	/**
	 * Cria uma nova instancia de ManagedBeanLogin
	 */
	public ManagedBeanLogin() {
		this.facesContext = FacesContext.getCurrentInstance();
		this.httpServletRequest = (HttpServletRequest) this.facesContext.getExternalContext().getRequest();
	}

	public String login() {
		if (this.username.isEmpty() && this.senha.isEmpty()) {
			return "indexLogin";
		} else {
			this.httpServletRequest.getSession().setAttribute("sessionUsername", this.username);

			return "sucessoLogin";
		}

	}

}