package com.example.model;

import java.util.ArrayList;
import java.util.List;

public class Predio extends Usuario implements Comparable<Predio> {
	// gastos com agua do mes atual em L
	private float gastosMesAtual;
	// colocacao no ranking
	private int colocacao;
	// lista de moradores
	private List<Morador> moradores;
	// lista de notificacoes
	private List<Notificacao> notificacoes;
	// lista de medicoes
	private List<Medicao> medicoes;

	/**
	 * Construtor de predio
	 * 
	 * @param nome
	 * @param senha
	 * @param telefone
	 * @param email
	 * @param endereco
	 */
	public Predio(String nome, String senha, String telefone, String email,
			Endereco endereco, int id) {
		super(nome, senha, telefone, email, endereco, id);

	}

	/**
	 * Construtor com os dados do bd
	 * 
	 * @param nome
	 * @param senha
	 * @param telefone
	 * @param email
	 * @param endereco
	 * @param moradores
	 * @param gastoMesAtual
	 * @param colocacao
	 */
	public Predio(String nome, String senha, String telefone, String email,
			Endereco endereco, List<Morador> moradores,
			List<Notificacao> notificacaoes, List<Medicao> medicoes,
			float gastoMesAtual, int colocacao, int id) {
		super(nome, senha, telefone, email, endereco, id);
	}

	/**
	 * Get gastos
	 * 
	 * @return gastos mensais de agua em litros
	 */
	public float getGastosMesAtual() {
		return gastosMesAtual;
	}

	/**
	 * Set gastos
	 * 
	 * @param gastosMesAtual
	 */
	public void setGastosMesAtual(float gastosMesAtual) {
		this.gastosMesAtual = gastosMesAtual;
	}

	/**
	 * Get colocacao
	 * 
	 * @return colocacao no ranking
	 */
	public int getColocacao() {
		return colocacao;
	}

	/**
	 * Set colocacao
	 * 
	 * @param colocacao
	 */
	public void setColocacao(int colocacao) {
		if (colocacao == 0) {
			throw new IllegalArgumentException("Colocação não pode ser 0.");
		}
		this.colocacao = colocacao;
	}

	public List<Morador> getMoradores() {
		return moradores;
	}

	public void setMoradores(List<Morador> moradores) {
		if(moradores == null){
			throw new IllegalArgumentException("Moradores não podem ser null.");
		}
		this.moradores = moradores;
	}

	public List<Notificacao> getNotificacoes() {
		return notificacoes;
	}

	public void setNotificacoes(List<Notificacao> notificacoes) {
		if(notificacoes == null){
			throw new IllegalArgumentException("Notificações não podem ser null.");
		}
		this.notificacoes = notificacoes;
	}

	public List<Medicao> getMedicoes() {
		return medicoes;
	}

	public void setMedicoes(List<Medicao> medicoes) {
		if(medicoes == null){
			throw new IllegalArgumentException("Medições não podem ser null.");
		}
		this.medicoes = medicoes;
	}

	@Override
	public int compareTo(Predio another) {
		if (this.getColocacao() < another.getColocacao()) {
			return -1;
		} else if (this.getColocacao() > another.getColocacao()) {
			return 1;
		}
		return 0;
	}
}
