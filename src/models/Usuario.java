package models;

public class Usuario {
	private static final int TAMANHO_MAXIMO_NOME = 30;
	private static final int TAMANHO_MINIMO_SENHA = 6;
	private static final int TAMANHO_MAXIMO_SENHA = 20;
	private static final int TAMANHO_MAXIMO_EMAIL = 30;
	private static final int TAMANHO_MAXIMO_TELEFONE = 11;
	private static final String EMAIL_REGEX = "^[\\w-]+(\\.[\\w-]+)*@([\\w-]+\\.)+[a-zA-Z]{2,7}$";
	private static final String TELEFONE_REGEX= "^\\([1-9]{2}\\) [2-9][0-9]{3,4}\\-[0-9]{4}$";
	private String apartamento;
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
		setNome(nome);
		setSenha(senha);
		setTelefone(telefone);
		setEmail(email);
		setEndereco(endereco);
	}

	/**
	 * Get nome do usuario
	 * @return nome
	 */
	public String getNome() {
		return apartamento;
	}

	/**
	 * Set nome do usuario
	 * @param nome
	 */
	public void setNome(String nome) {
		if (nome != null) {
			if (nome.trim().isEmpty()) {
				throw new IllegalArgumentException("Nome � obrigat�rio.");
			} else if (nome.length() > TAMANHO_MAXIMO_NOME) {
				throw new IllegalArgumentException("Tamanho do nome excede o limite de " + TAMANHO_MAXIMO_NOME + " caracteres.");
			}
		}else{
			throw new IllegalArgumentException("Nome � obrigat�rio.");
		}
		this.apartamento = nome;
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
	public void setSenha(String password) {
		/*if (password != null) {
			if (password.trim().isEmpty()) {
				throw new IllegalArgumentException("Senha � obrigat�ria.");
			} else if (password.length() < TAMANHO_MINIMO_SENHA) {
				throw new IllegalArgumentException("Deve deve ter pelo menos " + TAMANHO_MINIMO_SENHA + " caracteres.");
			} else if (password.length() > TAMANHO_MAXIMO_SENHA) {
				throw new IllegalArgumentException("Tamanho da senha excede o limite de " + TAMANHO_MAXIMO_SENHA + " caracteres.");
			}
		}*/
		
		this.senha = password;
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
		/*if (telefone == null || telefone.trim().isEmpty()) {
			throw new IllegalArgumentException("Telefone � obrigat�rio.");
		} else if (telefone.length() > TAMANHO_MAXIMO_TELEFONE || !telefone.matches(TELEFONE_REGEX)) {
			throw new IllegalArgumentException("Telefone inv�lido.");
		}*/
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
		/*if (email == null || email.trim().isEmpty()) {
			throw new IllegalArgumentException("E-mail � obrigat�rio.");
		} else if (email.length() > TAMANHO_MAXIMO_EMAIL || !email.matches(EMAIL_REGEX)) {
			throw new IllegalArgumentException("E-mail inv�lido.");
		}*/
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
		/*if (endereco != null) {
			throw new IllegalArgumentException("Endereco � obrigat�rio.");
		}*/
		this.endereco = endereco;
	}
}
