package com.example.model;

public class Usuario {
	private String nome;
	private String senha;
	private String telefone;
	private String email;
	private Endereco endereco;
	
	/**
	 * Construtor de usuario
	 * @param nome
	 * @param senha
	 * @param telefone
	 * @param email
	 * @param endereco
	 */
	public Usuario(String nome, String senha, String telefone, String email, Endereco endereco){
		this.nome = nome;
		this.senha = senha;
		this.telefone = telefone;
		this.email = email;
		this.endereco = endereco;
	}

	/**
	 * Get nome do usuario
	 * @return nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Set nome do usuario
	 * @param nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Get senha
	 * @return senha
	 */
	public String getSenha() {
		return senha;
	}

	/**
	 * Set senha
	 * @param senha
	 */
	public void setSenha(String senha) {
		this.senha = senha;
	}

	/**
	 * Get telefone
	 * @return telefone
	 */
	public String getTelefone() {
		return telefone;
	}

	/**
	 * Set telefone
	 * @param telefone
	 */
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	/**
	 * Get email
	 * @return email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Set email
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Get endereco
	 * @return endereco
	 */
	public Endereco getEndereco() {
		return endereco;
	}

	/**
	 * Set endereco
	 * @param endereco
	 */
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
}
