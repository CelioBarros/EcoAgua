package com.example.controller;

import com.example.model.*;

import android.util.Log;
import com.example.model.*;


import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import org.json.*;

/**
 * API eh classe que permite a conexao do aplicativo Android com o servidor REST. Utilizando
 * HTTP Client como meio de comunicacao.
 * 
 * @author      Felipe Sales
 * 
 * @version     %I%, %G%
 * @since       1.0
 */
@SuppressWarnings("deprecation")
public class API{

	/** 
	 *O endereco do servidor
	 */
	private static final String DOMAIN  = "http://aguaeco-celiobarros.rhcloud.com";

	/** 
	 *
	 * Recebe o login e a senha do usuario, compara com as informacoes no servidor, se tudo der certo
	 * retorna um objeto Usuario contendo todas as informacoes relevantes a essa conta se nao retorna NULL.
	 *
	 * @param usuario       o login do usuario
	 * @param senha         a sua respectiva senha
	 *
	 * @return Usuario      retorna um objeto com todas as informacoes do usuario.
	 * 
	 * @exception JSONException Se houver algum erro na conexao com o servidor.
	 * @see JSONException
	 */
	public static Usuario login(String usuario, String senha) throws JSONException{
		boolean login;

		Usuario user;
		String url, response;
		url= DOMAIN +"/login/" + usuario +"/" + senha;
		Log.d("url", url);

		response = GET(url);
		Log.d("JSON",response);
		JSONArray array = new JSONArray(response);
		JSONObject obj = array.getJSONObject(0);

		login = obj.getBoolean("login");

		if(login){
			if(obj.getString("tipo_usuario") == "Morador"){
				user = infoMorador(obj.getInt("id_morador"));
			}else{
				user = infoPredio(obj.getInt("id_predio"));
			}
		}else{
			user = null;
		}

		Log.d("Login", login + "");



		return user;		
	}

	/** 
	 *
	 * Recebe o id do morador, compara com as informacoes no servidor, se tudo der certo
	 * retorna um objeto Morador contendo todas as informacoes se nao retorna NULL.
	 *
	 * @param idMorador     O id no morador no nosso banco de dados.
	 * 
	 * @return Morador      retorna um objeto com todas as informacoes do respectivo morador.
	 * 
	 * @exception JSONException Se houver algum erro na conexao com o servidor.
	 * 
	 * @see JSONException
	 * @see Morador
	 */
	public static Morador infoMorador(int idMorador) throws JSONException{

		Morador morador;
		String url, response;
		url = (DOMAIN + "/info_morador/" + idMorador) ;


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

		morador = new Morador(nome, senha, telefone,email,endereco,predio, apartamento);

		System.out.println(apartamento);


		return morador;
	}

	/** 
	 *
	 * Recebe o id do predio, compara com as informacoes no servidor, se tudo der certo
	 * retorna um objeto Predio contendo todas as informacoes se nao retorna NULL.
	 *
	 * @param idPredio     O id no predio no nosso banco de dados.
	 * 
	 * @return Predio      retorna um objeto com todas as informacoes do respectivo predio.
	 * 
	 * @exception JSONException Se houver algum erro na conexao com o servidor.
	 * 
	 * @see JSONException
	 * @see Predio
	 */
	public static Predio infoPredio(int idPredio) throws JSONException{
		Predio predio;
		String url, response;
		url = (DOMAIN + "/info_predio/" + idPredio) ;

		try {

			response = GET(url);

			JSONArray array = new JSONArray(response);
			JSONObject obj = array.getJSONObject(0);

			String senha, nome, bairro, cidade,rua, estado, telefone, email ;
			int cep, numero;
			Endereco endereco;

			//login = obj.getString("login_predio");
			senha = obj.getString("senha");
			nome = obj.getString("nome");
			email = obj.getString("email");
			telefone = obj.getString("telefone");
			bairro = obj.getString("bairro");
			cidade = obj.getString("cidade");
			//estado = obj.getString("estado");
			estado = "PB";
			rua = obj.getString("rua");
			//cep = obj.getInt("cep");
			cep = 11111111;
			//numero = obj.getString("numero");
			numero = 10;
			endereco = new Endereco(estado,cidade,bairro,rua,cep,numero);
			predio = new Predio(nome,senha,telefone,email,endereco);

			System.out.println(estado);

		}finally{}

		return predio;
	}

	/** 
	 *
	 * Recebe todas as informacoes do morador e as cadastra no servidor, se der certo retorna true se
	 * nao retorna false.
	 *
	 * @param idPredio     O id do predio no nosso banco de dados.
	 * @param nome         O nome do morador.
	 * @param senha        A senha do morador.
	 * @param apartamento  O apartamento do morador.
	 * 
	 * @return boolean      Retorna true se o cadastro deu certo e false se nao.
	 * 
	 * @exception JSONException Se houver algum erro na conexao com o servidor.
	 * 
	 * @see JSONException
	 */
	public static boolean cadastraMorador(int idPredio, String nome, String senha, String apartamento, String login ) throws JSONException{
		boolean cadastro;
		String url,response;

		url = (DOMAIN + "/cadastra_morador/" + idPredio + "/"+ nome + "/"+ senha + "/" + apartamento + "/"+ login) ;

		response = GET(url);

		JSONArray array = new JSONArray(response);
		JSONObject obj = array.getJSONObject(0);

		cadastro = obj.getBoolean("cadastro");
		System.out.println(cadastro);

		return cadastro;
	}

	/** 
	 *
	 * Recebe todas as informacoes do predio e as cadastra no servidor, se der certo retorna true se
	 * nao retorna false.
	 *
	 * @param nome         O nome do predio.
	 * @param senha        A senha do predio.
	 * @param telefone     O telefone do predio.
	 * @param email        O email do predio.
	 * @param estado       O estado em que o predio esta localizado.
	 * @param cidade       A cidade em que o predio esta localizado.
	 * @param bairro       O bairro em que o predio esta localizado.
	 * @param rua          A rua em que o predio esta localizado.
	 * @param numero       O numero do predio
	 * @param cep          O cep do predio
	 * @param login        O login do predio
	 * 
	 * @return boolean      Retorna true se o cadastro deu certo e false se nao.
	 * 
	 * @exception JSONException Se houver algum erro na conexao com o servidor.
	 * 
	 * @see JSONException
	 */
	public static boolean cadastraPredio(String nome, String senha, String telefone, String email, String estado, String cidade, String bairro, String rua, int numero, int cep, String login) throws JSONException{
		boolean cadastro;
		String url,response;

		url = (DOMAIN + "/cadastra_predio/" + nome + "/"+ senha + "/"+ telefone + "/" + email + "/"+ estado + "/" + cidade + "/" + bairro + "/" + rua
				+ "/" + numero + "/" + cep + "/" + login ) ;

		response = GET(url);

		JSONArray array;

		array = new JSONArray(response);

		JSONObject obj = array.getJSONObject(0);

		cadastro = obj.getBoolean("cadastro");
		System.out.println(cadastro);



		return cadastro;
	}

	/** 
	 *
	 * Receo id do predio e retorna todos os moradoes registrados a aquele predio.
	 *
	 * @param idPredio     O id do predio no nosso banco de dados.
	 * 
	 * @return ArrayList<Morador>      Retorna a lista de moradores do predio.
	 * 
	 * @exception JSONException Se houver algum erro na conexao com o servidor.
	 * 
	 * @see JSONException
	 * @see Morador
	 * @see ArrayList
	 */	
	public static ArrayList<Morador> listaMoradores(int idPredio) throws JSONException{
		ArrayList<Morador> moradores = new ArrayList<Morador>();

		String url,response;

		url = (DOMAIN + "/lista_moradores/" + idPredio) ;
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
			apartamento = listaDeMoradores.getJSONObject(i).getString("apartamento");
			nome = listaDeMoradores.getJSONObject(i).getString("nome");

			moradores.add(new Morador(nome, senha, telefone,email,endereco,predio, apartamento));

			System.out.println(nome);
		}


		return moradores;
	}

	/** 
	 *
	 * Recebe todas as informacoes do morador e as atualiza no servidor, se der certo retorna true se
	 * nao retorna false.
	 *
	 * @param idPredio     O id do predio no nosso banco de dados.
	 * @param idMorador    O id do morador no nosso banco de dados.
	 * @param nome         O nome do morador.
	 * @param senha        A senha do morador.
	 * @param apartamento  O apartamento do morador.
	 * @param login        O login do morador.
	 * 
	 * @return boolean      Retorna true se o cadastro deu certo e false se nao.
	 * 
	 * @exception JSONException Se houver algum erro na conexao com o servidor.
	 * 
	 * @see JSONException
	 */	
	public static boolean atualizaMorador(int idPredio,int idMorador, String nome, String senha, String apartamento, String login  ) throws JSONException{

		boolean cadastro;
		String url,response;

		url = (DOMAIN + "/atualiza_morador/" + idMorador + "/" + idPredio + "/" + nome
				+ "/" + senha + "/" + apartamento + "/" + login) ;

		response = GET(url);

		JSONArray array = new JSONArray(response);
		JSONObject obj = array.getJSONObject(0);

		cadastro = obj.getBoolean("cadastro");
		System.out.println(cadastro);

		return cadastro;
	}

	public static ArrayList<InfoAcude> infoAcudes() throws JSONException{
		ArrayList<InfoAcude> infoAcudes = new ArrayList<InfoAcude>();
		String url, response;

		url = DOMAIN + "/info_acudes";

		response = GET(url);
		//System.out.println(response);
		JSONArray array = new JSONArray(response);

		String nome, volume, data;
		int id;
		for (int i = 0; i < array.length(); i++) {
			nome = array.getJSONObject(i).getString("nome_acude");
			volume = array.getJSONObject(i).getString("volume");
			data = array.getJSONObject(i).getString("data");
			id = array.getJSONObject(i).getInt("id");

			infoAcudes.add(new InfoAcude(nome,volume,data,id));


			//System.out.println(i);
		}

		return infoAcudes;

	}

	public static boolean addInfoAcudes(String nome, String volume, String data) throws JSONException{
		String url, response;
		boolean result;

		url = DOMAIN + "/add_info_acudes/" + nome + "/" + volume + "/" + data;

		response = GET(url);

		JSONArray array = new JSONArray(response);
		JSONObject obj = array.getJSONObject(0);

		result = obj.getBoolean("insert_info");
		System.out.println(result);

		return result;

	}

	public static boolean cadastraMedicao(int idPredio, String quantidade, String unidade, String data) throws JSONException{
		boolean result;
		String url, response;

		url = DOMAIN + "/cadastra_medicao/" + idPredio + "/" + quantidade + "/" + unidade + "/" + data;

		response = GET(url);

		JSONArray array = new JSONArray(response);
		JSONObject obj = array.getJSONObject(0);

		result = obj.getBoolean("cadastra_medicao");

		//System.out.println("medicao " + result);


		return result;
	}

	public static ArrayList<Medicao> medicao(int idPredio) throws JSONException{
		ArrayList<Medicao> medicoes = new ArrayList<Medicao>();

		String url,response;

		url = DOMAIN + "/medicao/" + idPredio;

		response  = GET(url);

		JSONArray array = new JSONArray(response);

		String unidade, quantidade, data;
		for (int i = 0; i < array.length(); i++) {

			//unidade = array.getJSONObject(i).getString("unidade");

			quantidade = "" + array.getJSONObject(i).getDouble("quantidade");

			data = array.getJSONObject(i).getString("data");

			medicoes.add(new Medicao( Float.parseFloat(quantidade),data));

			//System.out.println("Medicoes: " + i);
		}



		return medicoes;
	}

	public static ArrayList<Medicao> medicao(int idPredio, String data_consulta) throws JSONException{
		ArrayList<Medicao> medicoes = new ArrayList<Medicao>();

		String url,response;

		url = DOMAIN + "/medicao/" + idPredio + "/" + data_consulta;

		response  = GET(url);

		JSONArray array = new JSONArray(response);

		String unidade, quantidade, data;
		for (int i = 0; i < array.length(); i++) {

			//unidade = array.getJSONObject(i).getString("unidade");

			quantidade = "" + array.getJSONObject(i).getDouble("quantidade");

			data = array.getJSONObject(i).getString("data");

			medicoes.add(new Medicao( Float.parseFloat(quantidade),data));

			//System.out.println("Medicoes: " + i);
		}


		return medicoes;
	}

	public static boolean cadastraNotificacoes(int idPredio, String texto, String data) throws JSONException{
		boolean result;
		String url, response;

		url = DOMAIN + "/cadastra_notificacoes/" + idPredio + "/" + texto + "/" +  data;

		response = GET(url);

		JSONArray array = new JSONArray(response);
		JSONObject obj = array.getJSONObject(0);

		result = obj.getBoolean("cadastra_notificacoes");

		//System.out.println("Cadastra notificacoes " + result);


		return result;
	}

	public static ArrayList<Notificacao> notificacoes(int idPredio) throws JSONException{
		ArrayList<Notificacao> notificacoes = new ArrayList<Notificacao>();
		String url, response;

		url = DOMAIN + "/notificacoes/" + idPredio;

		response = GET(url);

		JSONArray array = new JSONArray(response);

		String data,texto;
		for (int i = 0; i < array.length(); i++) {
			data = array.getJSONObject(i).getString("data");
			texto = array.getJSONObject(i).getString("texto");

			notificacoes.add(new Notificacao(texto, data));
			//System.out.println("Notificacoes" + i);
		}



		return notificacoes;
	}

	public static ArrayList<Notificacao> notificacoes(int idPredio, String data_consulta) throws JSONException{
		ArrayList<Notificacao> notificacoes = new ArrayList<Notificacao>();
		String url, response;

		url = DOMAIN + "/notificacoes/" + idPredio + "/" + data_consulta;

		response = GET(url);

		JSONArray array = new JSONArray(response);

		String data,texto;
		for (int i = 0; i < array.length(); i++) {
			data = array.getJSONObject(i).getString("data");
			texto = array.getJSONObject(i).getString("texto");

			notificacoes.add(new Notificacao(texto, data));
			//System.out.println("Notificacoes" + i);
		}



		return notificacoes;
	}




	/** 
	 *
	 * Recebe uma url e gera uma requiciao HTTP GET, retorna o resultado dessa requisicao se ela for bem sucessida se naa retorna: Did not Work!
	 * 
	 * @param url       A url a qual a requisicao sera feita.
	 * 
	 * @return String   A resposta obitida apos a requisicao
	 * 
	 */
	private static String GET(String url){
		Log.d("GET","DENTRO DO GET");

		String resultado= "nao funcionou";

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

