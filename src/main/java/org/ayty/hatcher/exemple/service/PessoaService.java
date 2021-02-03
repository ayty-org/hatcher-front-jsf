package org.ayty.hatcher.exemple.service;

import java.io.Serializable;
import java.util.List;

import org.ayty.hatcher.exemple.model.Pessoa;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;

public class PessoaService implements Serializable{
	
	public List<Pessoa> getPessoas() {
		Client client = ClientBuilder.newClient();
		
		//url
		WebTarget target = client.target("http://run.mocky.io/v3/a5a7d9ef-3093-4da9-a4b9-41054d4ce459");
	    
	    //pegando o json da requisicao
		String json = target.request().get(String.class); 
		
		//transformando o json em uma lista de Post
		List<Pessoa> list = new Gson().fromJson(json, new TypeToken<List<Pessoa>>(){}.getType());
	    return list;
	}
}