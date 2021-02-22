package org.ayty.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.ayty.model.AccessToken;
import org.ayty.model.User;
import org.ayty.service.UserService2;

import lombok.Getter;
import lombok.Setter;

/**
 * Classe e Bean de opera��es CRUD de Usuario.
 * 
 */

@Setter
@Getter
@ManagedBean
@SessionScoped
public class UserBean2 {

	private User user = new User();
	private String loginReturn;
	private String username;

	private Integer idUsuario;
	private AccessToken accessToken = new AccessToken();
	private String token;

	@Inject
	UserService2 service;

	/**
	 * M�todo para encerrar a sess�o de um usuario.
	 */
	public String encerrarLogin() {
		loginReturn = service.encerrarSessao();
		return loginReturn;
	}

	public void setAccessToken(AccessToken accessToken) {
		this.accessToken = accessToken;
	}

	/**
	 * M�todo para iniciar a sess�o de um usuario.
	 */
	public String postLogin() {
		token = service.postLogin(user.getLogin(), user.getPassword());
		loginReturn = service.loginSession(user.getLogin(), user.getPassword());
		this.setUsername(user.getLogin());

		return loginReturn;
	}

	/**
	 * M�todo para retornar uma lista de usuarios cadastrados.
	 * 
	 * @return List<User>
	 */
	public List<User> getUsers() {
		return service.getUsers();

	}

	/**
	 * M�todo para cadastrar um novo usuario.
	 * 
	 * @return String
	 */
	public String postUser() {
		service.postUser(token, user.getLogin(), user.getPassword(), user.getEmail(), user.getFullname(),
				user.getImage());
		return "indexLogout";
	}

	/**
	 * M�todo para cadastrar um novo usuario e iniciar sua sess�o ap�s o cadastro.
	 * 
	 * @return String
	 */
	public String postUserAndDoLogin() {
		service.postUser(token, user.getLogin(), user.getPassword(), user.getEmail(), user.getFullname(),

				user.getImage());
		token = service.postLogin(user.getLogin(), user.getPassword());
		loginReturn = service.loginSession(user.getLogin(), user.getPassword());
		this.setUsername(user.getLogin());
		return loginReturn;
	}

	/**
	 * M�todo para deletar um usuario.
	 * 
	 * @return String
	 */
	public String deleteUser() {
		int id = Integer.parseInt(
				FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idUsuario"));
		service.deleteUser(id, token);

		return "userpage";
	}

	/**
	 * M�todo para pegar um {@link id} do usuario e redirecionar para o update do
	 * respectivo usuario.
	 * 
	 * @return String
	 */
	public String getIdAndRedirectToUpdateUser() {
		idUsuario = Integer.parseInt(
				FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idUsuario"));
		setIdUsuario(idUsuario);
		return "updateUser";
	}

	/**
	 * M�todo para atualizar os dados de um usuario.
	 * 
	 * @return String
	 */
	public String updateUser() {
		service.updateUser(idUsuario, token, user.getLogin(), user.getPassword(), user.getEmail(), user.getFullname(),
				user.getImage());

		return "userpage";

	}

}
