package com.example.model;

public class Morador extends Usuario{
	private Predio predio;
	private String apartamento;

	/**
	 * Construtor de morador
	 * @param nome
	 * @param senha
	 * @param telefone
	 * @param email
	 * @param endereco
	 * @param predio
	 * @param apartamento
	 */
	public Morador(String nome, String senha, String telefone, String email, Endereco endereco, Predio predio, String apartamento) {
		super(nome, senha, telefone, email, endereco);
		this.predio = predio;
		this.apartamento = apartamento;
	}

	/**
	 * Get predio
	 * @return predio
	 */
	public Predio getPredio() {
		return predio;
	}

	/**
	 * Set predio
	 * @param predio
	 */
	public void setPredio(Predio predio) {
		this.predio = predio;
	}

	/**
	 * Get apartamento
	 * @return apartamento
	 */
	public String getApartamento() {
		return apartamento;
	}

	/**
	 * Set apartamento
	 * @param apartamento
	 */
	public void setApartamento(String apartamento) {
		this.apartamento = apartamento;
	}

}
