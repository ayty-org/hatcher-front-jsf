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
public class ManagedBeanSession {

	private String usuario;

	private final HttpServletRequest httpServletRequest;
	private final FacesContext facesContext;
	private FacesMessage facesMessage;

	public ManagedBeanSession() {
		this.facesContext = FacesContext.getCurrentInstance();
		this.httpServletRequest = (HttpServletRequest) this.facesContext.getExternalContext().getRequest();
		if (this.httpServletRequest.getSession().getAttribute("sessionUsername") != null) {
			this.usuario = this.httpServletRequest.getSession().getAttribute("sessionUsername").toString();
		}
	}

	public String encerrarSessao() {
		this.httpServletRequest.getSession().removeAttribute("sessionUsername");
		this.httpServletRequest.getSession().invalidate();
		return "index";
	}

}