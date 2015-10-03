package com.example.model;

public class Endereco {
	private String estado;
	private String cidade;
	private String bairro;
	private String rua;
	private String cep;
	private String numero;
	
	/**
	 * Construtor de endereco
	 * @param estado
	 * @param cidade
	 * @param bairro
	 * @param rua
	 * @param cep
	 * @param numero
	 */
	public Endereco(String estado, String cidade, String bairro, String rua,
			String cep, String numero) {
		super();
		this.estado = estado;
		this.cidade = cidade;
		this.bairro = bairro;
		this.rua = rua;
		this.cep = cep;
		this.numero = numero;
	}
	
	/**
	 * Get estado
	 * @return estado
	 */
	public String getEstado() {
		return estado;
	}
	
	/**
	 * Set estado
	 * @param estado
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	/**
	 * Get cidade
	 * @return cidade
	 */
	public String getCidade() {
		return cidade;
	}
	
	/**
	 * Set cidade
	 * @param cidade
	 */
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	
	/**
	 * Get bairro
	 * @return bairro
	 */
	public String getBairro() {
		return bairro;
	}
	
	/**
	 * Set bairro
	 * @param bairro
	 */
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	
	/**
	 * Get rua
	 * @return rua
	 */
	public String getRua() {
		return rua;
	}
	
	/**
	 * Set rua
	 * @param rua
	 */
	public void setRua(String rua) {
		this.rua = rua;
	}
	
	/**
	 * Get cep
	 * @return cep
	 */
	public String getCep() {
		return cep;
	}
	
	/**
	 * Set cep
	 * @param cep
	 */
	public void setCep(String cep) {
		this.cep = cep;
	}
	
	/**
	 * Get numero
	 * @return
	 */
	public String getNumero() {
		return numero;
	}
	
	/**
	 * Set numero
	 * @param numero
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}
}
