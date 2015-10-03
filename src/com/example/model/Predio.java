package com.example.model;

public class Predio extends Usuario{
	//gastos com agua do mes atual em L
	private float gastosMesAtual;
	//colocacao no ranking
	private int colocacao;

	/**
	 * Construtor de predio
	 * @param nome
	 * @param senha
	 * @param telefone
	 * @param email
	 * @param endereco
	 */
	public Predio(String nome, String senha, String telefone, String email, Endereco endereco) {
		super(nome, senha, telefone, email, endereco);
		
	}

	/**
	 * Get gastos
	 * @return gastos mensais de agua em litros
	 */
	public float getGastosMesAtual() {
		return gastosMesAtual;
	}

	/**
	 * Set gastos
	 * @param gastosMesAtual
	 */
	public void setGastosMesAtual(float gastosMesAtual) {
		this.gastosMesAtual = gastosMesAtual;
	}

	/**
	 * Get colocacao
	 * @return colocacao no ranking
	 */
	public int getColocacao() {
		return colocacao;
	}

	/**
	 * Set colocacao
	 * @param colocacao
	 */
	public void setColocacao(int colocacao) {
		this.colocacao = colocacao;
	}
}
