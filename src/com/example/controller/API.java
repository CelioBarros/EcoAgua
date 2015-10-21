package com.example.controller;

import com.example.model.*;

import java.util.ArrayList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
 
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import org.json.*;

public class API{

	private static final String DOMAIN  = "http://aguaeco-celiobarros.rhcloud.com";

	public static Usuario login(String usuario, String senha) throws Exception{
		boolean login;
		
		Usuario user;
		String url, response;
		url= DOMAIN +"/login/" + usuario +"/" + senha;
		
		response = GET(url);
		
		JSONArray array = new JSONArray(response);
		JSONObject obj = array.getJSONObject(0);

		login = obj.getBoolean("login");
		System.out.println(login);

		if(login){
			if(obj.getString("tipo_usuario") == "Morador"){
				user = infoMorador(obj.getInt("id_morador"));
			}else{
				user = infoPredio(obj.getInt("id_predio"));
			}
		}else{
			user = null;
		}
		
		
		return user;		
	}
	
	public static Morador infoMorador(int idMorador) throws JSONException{
		
		Morador morador;
		String url, response;
		url = (DOMAIN + "/info_morador/" + idMorador) ;
		
		try {
			
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
				
		}finally{}
		
		return morador;
	}

	public static Predio infoPredio(int idPredio) throws JSONException{
		Predio predio;
		String url, response;
		url = (DOMAIN + "/info_predio/" + idPredio) ;
		
		try {
			
			response = GET(url);
			
			JSONArray array = new JSONArray(response);
			JSONObject obj = array.getJSONObject(0);
				
			String senha, nome, bairro, cidade,rua, numero, estado, telefone, email ;
			int cep;
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
			numero = "10";
			endereco = new Endereco(estado,cidade,bairro,rua,Integer.toString(cep),numero);
			predio = new Predio(nome,senha,telefone,email,endereco);
			
			System.out.println(estado);
				
		}finally{}
		
		return predio;
	}

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
	
	public static boolean cadastraPredio(String nome, String senha, int telefone, String email, String estado, String cidade, String bairro, String rua, String numero, int cep, String login) throws JSONException{
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
	
	public static ArrayList<Morador> listaMoradores(int idPredio) throws JSONException{
		ArrayList<Morador> moradores = new ArrayList();

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

	public static String GET(String url){
        InputStream inputStream = null;
        String result = "";
        try {
 
            // create HttpClient
            HttpClient httpclient = new DefaultHttpClient();
 
            // make GET request to the given URL
            HttpResponse httpResponse = httpclient.execute(new HttpGet(url));
 
            // receive response as inputStream
            inputStream = httpResponse.getEntity().getContent();
 
            // convert inputstream to string
            if(inputStream != null)
                result = convertInputStreamToString(inputStream);
            else
                result = "Did not work!";
 
        } catch (Exception e) {
            //Log.d("InputStream", e.getLocalizedMessage());
        }
 
        return result;
    }
 
    private static String convertInputStreamToString(InputStream inputStream) throws IOException{
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while((line = bufferedReader.readLine()) != null)
            result += line;
 
        inputStream.close();
        return result;
 
    }
}

