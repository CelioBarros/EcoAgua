package com.example.controller;


import java.io.IOException;

import java.util.ArrayList;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import com.example.model.Morador;

@SuppressWarnings("deprecation")
public class API{

	private static final String DOMAIN  = "http://aguaeco-celiobarros.rhcloud.com";

	public static boolean login(String usuario, String senha) throws ClientProtocolException, IOException{
		CloseableHttpClient httpclient = HttpClients.createDefault();

		boolean login = false;
		
		try {

			String url = (DOMAIN + "/login/" + usuario + "/" + senha) ;
			System.out.println(url);
			HttpGet httpGet = new HttpGet(url);
			CloseableHttpResponse response1 = httpclient.execute(httpGet);
			
			try {
				System.out.println(response1.getStatusLine());
				HttpEntity entity1 = response1.getEntity();

				JSONArray array = new JSONArray(EntityUtils.toString(response1.getEntity()));
				JSONObject obj = array.getJSONObject(0);

				login = obj.getBoolean("login");
				System.out.println(login);
				
				entity1.consumeContent();
			} catch(Exception e){
				
			}finally {
				response1.close();
			}
		}finally{
			httpclient.close();

		}
		
		return login;
	}

	public static boolean cadastraMorador(int idPredio, String nome, String senha, int apartamento, String loginMorador  ) throws ClientProtocolException, IOException{
		CloseableHttpClient httpclient = HttpClients.createDefault();

		boolean cadastro = false;
		
		try {

			String url = (DOMAIN + "/cadastra_morador/" + idPredio + "/" + nome + "/" + senha
					+ "/" + apartamento + "/" + loginMorador) ;
			System.out.println(url);
			HttpPost httpPost = new HttpPost(url);
			CloseableHttpResponse response = httpclient.execute(httpPost);
		
			try {
				System.out.println(response.getStatusLine());
				HttpEntity entity1 = response.getEntity();

				JSONArray array = new JSONArray(EntityUtils.toString(response.getEntity()));
				JSONObject obj = array.getJSONObject(0);

				cadastro = obj.getBoolean("cadastro");
				System.out.println(cadastro);
				
				entity1.consumeContent();
			} catch(Exception e){
				
			}finally {
				response.close();
			}
		}finally{
			httpclient.close();

		}
		
		return cadastro;
	}

	public static boolean cadastraPredio(String nome, String senha, int telefone, String email, String estado, String cidade, String bairro, String rua, String numero, int cep, String login) throws ClientProtocolException, IOException{
		CloseableHttpClient httpclient = HttpClients.createDefault();

		boolean cadastro = false;
		try {

			String url = (DOMAIN + "/cadastra_predio/" + nome + "/"+ senha + "/"+ telefone + "/" + email + "/"+ estado + "/" + cidade + "/" + bairro + "/" + rua
					+ "/" + numero + "/" + cep + "/" + login ) ;
			System.out.println(url);
			HttpPost httpPost = new HttpPost(url);
			CloseableHttpResponse response = httpclient.execute(httpPost);
			
			try {
				System.out.println(response.getStatusLine());
				HttpEntity entity1 = response.getEntity();

				JSONArray array = new JSONArray(EntityUtils.toString(response.getEntity()));
				JSONObject obj = array.getJSONObject(0);

				cadastro = obj.getBoolean("cadastro");
				System.out.println(cadastro);
				
				entity1.consumeContent();
			} catch(Exception e){
				
			}finally {
				response.close();
			}
		}finally{
			httpclient.close();

		}
		
		return cadastro;
	}

	public static Morador infoMorador(int idMorador) throws ClientProtocolException, IOException{
		CloseableHttpClient httpclient = HttpClients.createDefault();

		Morador morador = null;
		try {

			String url = (DOMAIN + "/info_morador/" + idMorador) ;
			System.out.println(url);
			HttpGet httpGet = new HttpGet(url);
			CloseableHttpResponse response = httpclient.execute(httpGet);
			
			try {
				System.out.println(response.getStatusLine());
				HttpEntity entity1 = response.getEntity();

				JSONArray array = new JSONArray(EntityUtils.toString(response.getEntity()));
				JSONObject obj = array.getJSONObject(0);
				
				String login, senha, nome, apartamento;
				int idPredio;
				
				login = obj.getString("login_nome");
				senha = obj.getString("senha");
				idMorador = obj.getInt("id_morador");
				apartamento = obj.getString("apartamento");
				nome = obj.getString("nome");
				idPredio = obj.getInt("id_predio"); 
				
				morador = new Morador(idMorador,idPredio,nome, senha, apartamento,login);
				
				System.out.println(login);

				entity1.consumeContent();
			} catch(Exception e){
				
			}
			
			finally {
				response.close();
			}
		}finally{
			httpclient.close();

		}
		
		return morador;
	}

	public static void infoPredio(int idPredio) throws ClientProtocolException, IOException{
		CloseableHttpClient httpclient = HttpClients.createDefault();

		try {

			String url = (DOMAIN + "/info_predio/" + idPredio) ;
			System.out.println(url);
			HttpGet httpGet = new HttpGet(url);
			CloseableHttpResponse response = httpclient.execute(httpGet);
			
			try {
				System.out.println(response.getStatusLine());
				HttpEntity entity = response.getEntity();

				JSONArray array = new JSONArray(EntityUtils.toString(response.getEntity()));
				JSONObject obj = array.getJSONObject(0);

				String login = obj.getString("login");
				System.out.println(login);

				entity.consumeContent();
			} catch(Exception e){
				
			}finally {
				response.close();
			}
		}finally{
			httpclient.close();
		}
		
	}

	public static ArrayList<Morador> listaMoradores(int idPredio) throws ClientProtocolException, IOException{
		CloseableHttpClient httpclient = HttpClients.createDefault();

		ArrayList<Morador> moradores = new ArrayList();
		
		try {

			String url = (DOMAIN + "/lista_moradores/" + idPredio) ;
			System.out.println(url);
			HttpGet httpGet = new HttpGet(url);
			CloseableHttpResponse response = httpclient.execute(httpGet);
			
			try {
				System.out.println(response.getStatusLine());
				HttpEntity entity = response.getEntity();

				JSONArray array = new JSONArray(EntityUtils.toString(response.getEntity()));
				JSONObject obj = array.getJSONObject(0);

				JSONArray listaDeMoradores = obj.getJSONArray("lista_moradores");
				
				String login, senha, nome, apartamento;
				int idMorador;
				
				for (int i = 0; i < listaDeMoradores.length(); i++) {
					login = listaDeMoradores.getJSONObject(i).getString("login_nome");
					senha = listaDeMoradores.getJSONObject(i).getString("senha");
					idMorador = listaDeMoradores.getJSONObject(i).getInt("id_morador");
					apartamento = listaDeMoradores.getJSONObject(i).getString("apartamento");
					nome = listaDeMoradores.getJSONObject(i).getString("nome");
					
					moradores.add(new Morador(idMorador,idPredio,nome, senha, apartamento,login));
					
					System.out.println(apartamento);
				}
				
				entity.consumeContent();
			} catch(Exception e){
				
			}finally {
				response.close();
			}
		}finally{
			httpclient.close();
		}		
		
		return moradores;
	}
	
	public static boolean atualizaMorador(int idPredio,int idMorador, String nome, String senha, String apartamento, String login  ) throws ClientProtocolException, IOException{
		CloseableHttpClient httpclient = HttpClients.createDefault();

		boolean cadastro = false;
		try {

			String url = (DOMAIN + "/atualiza_morador/" + idMorador + "/" + idPredio + "/" + nome
					+ "/" + senha + "/" + apartamento + "/" + login) ;
			System.out.println(url);
			HttpPost httpPost = new HttpPost(url);
			CloseableHttpResponse response = httpclient.execute(httpPost);
	
			try {
				System.out.println(response.getStatusLine());
				HttpEntity entity = response.getEntity();

				JSONArray array = new JSONArray(EntityUtils.toString(response.getEntity()));
				JSONObject obj = array.getJSONObject(0);

				cadastro = obj.getBoolean("cadastro");
				System.out.println(cadastro);

				entity.consumeContent();
			} finally {
				response.close();
			}
		}catch(Exception e){
			
		}finally{
			httpclient.close();

		}
		
		return cadastro;

	}

}
