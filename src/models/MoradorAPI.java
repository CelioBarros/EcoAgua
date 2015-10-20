package models;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

import controller.API;

public class MoradorAPI {
	
	private String nome, senha, login, apartamento;
	private int idPredio, id;

	public MoradorAPI(int id, int idPredio,String nome, String senha, String apartamento, String login ){
		setIdPredio(idPredio);
		setNome(nome);
		setSenha(senha);
		setApartamento(apartamento);
		setLogin(login);	
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public int getIdPredio() {
		return idPredio;
	}

	public void setIdPredio(int idPredio) {
		this.idPredio = idPredio;
	}

	public String getApartamento() {
		return apartamento;
	}

	public void setApartamento(String apartamento) {
		this.apartamento = apartamento;
	}
	
	public void atualiza() throws ClientProtocolException, IOException{
		
		API.atualizaMorador(idPredio, id, nome, senha, apartamento, login);
		
	}


}
