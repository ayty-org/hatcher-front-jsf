package org.ayty.hatcher.example.service;

import java.io.Serializable;
import java.util.List;

import org.ayty.hatcher.example.model.Pessoa;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Response;

public class PessoaService implements Serializable {
	private static final long serialVersionUID = 1L;

	public List<Pessoa> getPessoas() {
		
		Client client = ClientBuilder.newClient();

		// Url de uma API que fornece um JSON
		String URL = "http://run.mocky.io/v3/a5a7d9ef-3093-4da9-a4b9-41054d4ce459";

		// O Client vai alvejar um alvo que será a URL
		WebTarget target = client.target(URL);
		
		Response response = target.request().get();

		// pegando o json da requisicao
		String json = response.readEntity(String.class);
		response.close();

		/* Transformando o Json em uma lista de pessoas. 
		 * O TypeToken converte o Json_-> Objeto.
		 * O Gson faz a associação do Json com o Objeto Pessoa
		 */
		List<Pessoa> list = new Gson().fromJson(json, new TypeToken<List<Pessoa>>() {
		}.getType());
		return list;
	}
}