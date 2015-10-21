package com.example.model;

public class Morador extends Usuario {
	private static final int TAMANHO_MAXIMO_APARTAMENTO = 10;
	private static final int TAMANHO_MAXIMO_USUARIO = 30;
	private Predio predio;
	private String apartamento;
	private String nomeUsuario; // nomeDoPredio_Apartamento

	/**
	 * Construtor de morador
	 * 
	 * @param nome
	 * @param senha
	 * @param telefone
	 * @param email
	 * @param endereco
	 * @param predio
	 * @param apartamento
	 */
	public Morador(String nome, String senha, String telefone, String email,
			Endereco endereco, Predio predio, String apartamento) {
		super(nome, senha, telefone, email, endereco);
		setPredio(predio);
		setApartamento(apartamento);
		setNomeUsuario(predio.getNome() + "_" + apartamento);
	}
	

	/**
	 * Get predio
	 * 
	 * @return predio
	 */
	public Predio getPredio() {
		return predio;
	}

	/**
	 * Set predio
	 * 
	 * @param predio
	 */
	public void setPredio(Predio predio) {
		if (predio == null) {
			throw new IllegalArgumentException("Pr�dio � obrigat�rio.");
		}
		this.predio = predio;
	}

	/**
	 * Get apartamento
	 * 
	 * @return apartamento
	 */
	public String getApartamento() {
		return apartamento;
	}

	/**
	 * Set apartamento
	 * 
	 * @param apartamento
	 */
	public void setApartamento(String apartamento) {
		if (apartamento != null) {
			if (apartamento.trim().isEmpty()) {
				throw new IllegalArgumentException("Apartamento � obrigat�rio.");
			} else if (apartamento.length() > TAMANHO_MAXIMO_APARTAMENTO) {
				throw new IllegalArgumentException("Tamanho do nome do apartamento excede o limite de " + TAMANHO_MAXIMO_APARTAMENTO + " caracteres.");
			}
		}
		this.apartamento = apartamento;
	}

	/**
	 * get nome do usuario
	 * 
	 * @return nome do usuario
	 */
	public String getNomeUsuario() {
		return nomeUsuario;
	}

	/**
	 * Set nome do usuario
	 * 
	 * @param nomeUsuario
	 */
	public void setNomeUsuario(String nomeUsuario) {
		if (nomeUsuario != null) {
			if (nomeUsuario.trim().isEmpty()) {
				throw new IllegalArgumentException("Apartamento � obrigat�rio.");
			} else if (nomeUsuario.length() > TAMANHO_MAXIMO_USUARIO) {
				throw new IllegalArgumentException("Tamanho do usu�rio excede o limite de " + TAMANHO_MAXIMO_USUARIO + " caracteres.");
			}
		}
		this.nomeUsuario = nomeUsuario;
	}
}
