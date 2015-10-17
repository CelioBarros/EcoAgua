package com.example.model;

import java.util.ArrayList;
import java.util.List;

public class Predio  extends Usuario implements Comparable<Predio>{
	//gastos com agua do mes atual em L
	private float gastosMesAtual;
	//colocacao no ranking
	private int colocacao;
	//lista de moradores
	private List<Morador> moradores;
	//lista de notificacoes
	private List<Notificacao> notificacoes;

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
	 * Construtor com os dados do bd
	 * @param nome
	 * @param senha
	 * @param telefone
	 * @param email
	 * @param endereco
	 * @param moradores
	 * @param gastoMesAtual
	 * @param colocacao
	 */
	public Predio(String nome, String senha, String telefone, String email, Endereco endereco, List<Morador> moradores, float gastoMesAtual, int colocacao) {
		super(nome, senha, telefone, email, endereco);
		this.gastosMesAtual = gastoMesAtual;
		this.colocacao = colocacao;
		this.moradores = new ArrayList<Morador>();
		this.moradores = moradores;
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
		if (colocacao == 0) {
			throw new IllegalArgumentException("Coloca��o n�o pode ser 0.");
		}
		this.colocacao = colocacao;
	}

	public List<Morador> getMoradores() {
		return moradores;
	}

	public void setMoradores(List<Morador> moradores) {
		this.moradores = moradores;
	}

	public List<Notificacao> getNotificacoes() {
		return notificacoes;
	}

	public void setNotificacoes(List<Notificacao> notificacoes) {
		this.notificacoes = notificacoes;
	}

	@Override
	public int compareTo(Predio another) {
		if (this.getColocacao() < another.getColocacao()) {
            return -1;
        }
		else if (this.getColocacao() > another.getColocacao()) {
            return 1;
        }
		return 0;
	}
}
