package com.example.controller;

import com.example.ecoagua.LoginActivity;
import com.example.ecoagua.MainActivity;
import com.example.model.*;

import android.content.Intent;
import android.util.Log;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import org.json.*;

/**
 * API eh classe que permite a conexao do aplicativo Android com o servidor
 * REST. Utilizando HTTP Client como meio de comunicacao.
 * 
 * @author Felipe Sales
 * 
 * @version %I%, %G%
 * @since 1.0
 */
@SuppressWarnings("deprecation")
public class API {

	public static Usuario user;

	/**
	 * O endereco do servidor
	 */
	private static final String DOMAIN = "http://aguaeco-celiobarros.rhcloud.com";

	/**
	 * 
	 * Recebe o login e a senha do usuario, compara com as informacoes no
	 * servidor, se tudo der certo retorna um objeto Usuario contendo todas as
	 * informacoes relevantes a essa conta se nao retorna NULL.
	 * 
	 * @param usuario
	 *            o login do usuario
	 * @param senha
	 *            a sua respectiva senha
	 * @return
	 * 
	 * @return Usuario retorna um objeto com todas as informacoes do usuario.
	 * 
	 * @exception JSONException
	 *                Se houver algum erro na conexao com o servidor.
	 * @see JSONException
	 */
	public static boolean login(String usuario, String senha)
			throws JSONException {
		boolean login;

		String url, response;
		url = DOMAIN + "/login/" + usuario + "/" + senha;
		Log.d("url", url);

		response = GET(url);
		Log.d("JSON", response);
		JSONArray array = new JSONArray(response);
		JSONObject obj = array.getJSONObject(0);

		login = obj.getBoolean("login");

		if (login) {
			if (obj.getString("tipo_usuario").equals("Morador")) {
				user = infoMorador(obj.getInt("id_morador"));
			} else {
				user = infoPredio(obj.getInt("id_predio"));
			}
		} else {
			user = null;
		}

		return login;
	}

	/**
	 * 
	 * Recebe o id do morador, compara com as informacoes no servidor, se tudo
	 * der certo retorna um objeto Morador contendo todas as informacoes se nao
	 * retorna NULL.
	 * 
	 * @param idMorador
	 *            O id no morador no nosso banco de dados.
	 * 
	 * @return Morador retorna um objeto com todas as informacoes do respectivo
	 *         morador.
	 * 
	 * @exception JSONException
	 *                Se houver algum erro na conexao com o servidor.
	 * 
	 * @see JSONException
	 * @see Morador
	 */
	public static Morador infoMorador(int idMorador) throws JSONException {

		Morador morador;
		String url, response;
		url = (DOMAIN + "/info_morador/" + idMorador);

		response = GET(url);

		JSONArray array = new JSONArray(response);
		JSONObject obj = array.getJSONObject(0);

		String senha, nome, apartamento, telefone, email;
		int idPredio;
		Endereco endereco;
		Predio predio;

		telefone = "";
		email = "";

		senha = obj.getString("senha");
		apartamento = obj.getString("apartamento");
		nome = obj.getString("nome");
		idPredio = obj.getInt("id_predio");

		predio = infoPredio(idPredio);
		endereco = predio.getEndereco();

		morador = new Morador(nome, senha, telefone, email, endereco, predio,
				apartamento, idMorador);

		System.out.println(apartamento);

		return morador;
	}

	/**
	 * 
	 * Recebe o id do predio, compara com as informacoes no servidor, se tudo
	 * der certo retorna um objeto Predio contendo todas as informacoes se nao
	 * retorna NULL.
	 * 
	 * @param idPredio
	 *            O id no predio no nosso banco de dados.
	 * 
	 * @return Predio retorna um objeto com todas as informacoes do respectivo
	 *         predio.
	 * 
	 * @exception JSONException
	 *                Se houver algum erro na conexao com o servidor.
	 * 
	 * @see JSONException
	 * @see Predio
	 */
	public static Predio infoPredio(int idPredio) throws JSONException {
		Predio predio;
		String url, response;
		url = (DOMAIN + "/info_predio/" + idPredio);

		try {

			response = GET(url);

			JSONArray array = new JSONArray(response);
			JSONObject obj = array.getJSONObject(0);

			String senha, nome, bairro, cidade, rua, estado, telefone, email;
			int cep, numero;
			Endereco endereco;

			// login = obj.getString("login_predio");
			senha = obj.getString("senha");
			nome = obj.getString("nome");
			email = obj.getString("email");
			telefone = obj.getString("telefone");
			bairro = obj.getString("bairro");
			cidade = obj.getString("cidade");
			// estado = obj.getString("estado");
			estado = "PB";
			rua = obj.getString("rua");
			cep = obj.getInt("cep");
			// numero = Integer.parseInt(obj.getString("numero"));

			// numero de login_predio é 'numero'
			numero = 1;

			endereco = new Endereco(estado, cidade, bairro, rua, cep, numero);
			predio = new Predio(nome, senha, telefone, email, endereco,
					idPredio);

			System.out.println(estado);

		} finally {
		}

		return predio;
	}

	/**
	 * 
	 * Recebe todas as informacoes do morador e as cadastra no servidor, se der
	 * certo retorna true se nao retorna false.
	 * 
	 * @param idPredio
	 *            O id do predio no nosso banco de dados.
	 * @param nome
	 *            O nome do morador.
	 * @param senha
	 *            A senha do morador.
	 * @param apartamento
	 *            O apartamento do morador.
	 * 
	 * @return boolean Retorna true se o cadastro deu certo e false se nao.
	 * 
	 * @exception JSONException
	 *                Se houver algum erro na conexao com o servidor.
	 * 
	 * @see JSONException
	 */
	public static boolean cadastraMorador(int idPredio, String nome,
			String senha, String apartamento, String login)
			throws JSONException {

		boolean cadastro;

		String url, response;

		url = (DOMAIN + "/cadastra_morador/" + idPredio + "/" + nome + "/"
				+ senha + "/" + apartamento + "/" + login);

		response = GET(url);

		JSONArray array = new JSONArray(response);
		JSONObject obj = array.getJSONObject(0);

		cadastro = obj.getBoolean("cadastro");

		return cadastro;
	}

	private static boolean checkId(int id, String tipo) {
		boolean idExiste = false;
		if (tipo.equals("Predio")) {
			try {
				user = infoPredio(id);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (tipo.equals("Morador")) {
			try {
				user = infoMorador(id);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (user != null) {
			idExiste = true;
		}

		return idExiste;

	}

	/**
	 * 
	 * Recebe todas as informacoes do predio e as cadastra no servidor, se der
	 * certo retorna true se nao retorna false.
	 * 
	 * @param nome
	 *            O nome do predio.
	 * @param senha
	 *            A senha do predio.
	 * @param telefone
	 *            O telefone do predio.
	 * @param email
	 *            O email do predio.
	 * @param estado
	 *            O estado em que o predio esta localizado.
	 * @param cidade
	 *            A cidade em que o predio esta localizado.
	 * @param bairro
	 *            O bairro em que o predio esta localizado.
	 * @param rua
	 *            A rua em que o predio esta localizado.
	 * @param numero
	 *            O numero do predio
	 * @param cep
	 *            O cep do predio
	 * @param login
	 *            O login do predio
	 * 
	 * @return boolean Retorna true se o cadastro deu certo e false se nao.
	 * 
	 * @exception JSONException
	 *                Se houver algum erro na conexao com o servidor.
	 * 
	 * @see JSONException
	 */
	public static boolean cadastraPredio(String nome, String senha,
			String telefone, String email, String estado, String cidade,
			String bairro, String rua, int numero, int cep, String login)
			throws JSONException {
		boolean cadastro = false;

		String url, response;

		url = (DOMAIN + "/cadastra_predio/" + nome + "/" + senha + "/"
				+ telefone + "/" + email + "/" + estado + "/" + cidade + "/"
				+ bairro + "/" + rua + "/" + numero + "/" + cep + "/" + login);

		response = GET(url);

		JSONArray array;

		array = new JSONArray(response);

		JSONObject obj = array.getJSONObject(0);

		cadastro = obj.getBoolean("cadastro");
		if (cadastro) {
			if (obj.getString("tipo_usuario") == "Morador") {
				user = infoMorador(obj.getInt("id_morador"));
			} else {
				user = infoPredio(obj.getInt("id_predio"));
			}
		} else {
			user = null;
		}

		return cadastro;
	}

	/**
	 * 
	 * Receo id do predio e retorna todos os moradoes registrados a aquele
	 * predio.
	 * 
	 * @param idPredio
	 *            O id do predio no nosso banco de dados.
	 * 
	 * @return ArrayList<Morador> Retorna a lista de moradores do predio.
	 * 
	 * @exception JSONException
	 *                Se houver algum erro na conexao com o servidor.
	 * 
	 * @see JSONException
	 * @see Morador
	 * @see ArrayList
	 */
	public static ArrayList<Morador> listaMoradores(int idPredio)
			throws JSONException {
		ArrayList<Morador> moradores = new ArrayList<Morador>();

		String url, response;

		url = (DOMAIN + "/lista_moradores/" + idPredio);
		response = GET(url);

		JSONArray array = new JSONArray(response);
		JSONObject obj = array.getJSONObject(0);

		JSONArray listaDeMoradores = obj.getJSONArray("lista_moradores");

		String senha, nome, apartamento, telefone, email;

		Endereco endereco;
		Predio predio;

		telefone = "";
		email = "";

		predio = infoPredio(idPredio);
		endereco = predio.getEndereco();

		for (int i = 0; i < listaDeMoradores.length(); i++) {
			senha = listaDeMoradores.getJSONObject(i).getString("senha");
			apartamento = listaDeMoradores.getJSONObject(i).getString(
					"apartamento");
			nome = listaDeMoradores.getJSONObject(i).getString("nome");

			moradores.add(new Morador(nome, senha, telefone, email, endereco,
					predio, apartamento, idPredio));

			System.out.println(nome);
		}

		return moradores;
	}

	/**
	 * 
	 * Recebe todas as informacoes do morador e as atualiza no servidor, se der
	 * certo retorna true se nao retorna false.
	 * 
	 * @param idPredio
	 *            O id do predio no nosso banco de dados.
	 * @param idMorador
	 *            O id do morador no nosso banco de dados.
	 * @param nome
	 *            O nome do morador.
	 * @param senha
	 *            A senha do morador.
	 * @param apartamento
	 *            O apartamento do morador.
	 * @param login
	 *            O login do morador.
	 * 
	 * @return boolean Retorna true se o cadastro deu certo e false se nao.
	 * 
	 * @exception JSONException
	 *                Se houver algum erro na conexao com o servidor.
	 * 
	 * @see JSONException
	 */
	public static boolean atualizaMorador(int idPredio, int idMorador,
			String nome, String senha, String apartamento, String login)
			throws JSONException {

		boolean cadastro;
		String url, response;

		url = (DOMAIN + "/atualiza_morador/" + idMorador + "/" + idPredio + "/"
				+ nome + "/" + senha + "/" + apartamento + "/" + login);

		response = GET(url);

		JSONArray array = new JSONArray(response);
		JSONObject obj = array.getJSONObject(0);

		cadastro = obj.getBoolean("cadastro");
		System.out.println(cadastro);

		return cadastro;
	}

	/**
	 * 
	 * Recebe uma url e gera uma requiciao HTTP GET, retorna o resultado dessa
	 * requisicao se ela for bem sucessida se naa retorna: Did not Work!
	 * 
	 * @param url
	 *            A url a qual a requisicao sera feita.
	 * 
	 * @return String A resposta obitida apos a requisicao
	 * 
	 */
	private static String GET(String url) {
		Log.d("GET", "DENTRO DO GET");

		String resultado = "nao funcionou";

		try {
			resultado = new Consulta().execute(url).get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Log.d("GET resultado", resultado);

		return resultado;
	}

}