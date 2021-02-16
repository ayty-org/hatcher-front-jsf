package org.ayty.bean;

import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Named
@SessionScoped
public class LogoutUserBean {

	private final HttpServletRequest httpServletRequest;
	private final FacesContext facesContext;

	private String username;

	public LogoutUserBean() {
		this.facesContext = FacesContext.getCurrentInstance();
		this.httpServletRequest = (HttpServletRequest) this.facesContext.getExternalContext().getRequest();
		if (this.httpServletRequest.getSession().getAttribute("sessionUsername") != null) {
			this.username = this.httpServletRequest.getSession().getAttribute("sessionUsername").toString();
		}
	}

	public String encerrarSessao() {
		this.httpServletRequest.getSession().removeAttribute("sessionUsername");
		this.httpServletRequest.getSession().invalidate();
		return "#";
	}

}