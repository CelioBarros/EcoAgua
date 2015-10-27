package com.example.ecoagua;

import java.util.Calendar;

import org.json.JSONException;

import com.example.controller.API;
import com.example.model.Morador;
import com.example.model.Predio;
import com.example.model.Usuario;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends Activity{
	private TextView etCadastrar;
	private Button btnEntrar;
	private EditText etLogin;
	private EditText etSenha;
	
	private void logar() {
		btnEntrar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String login = etLogin.getText().toString();
				String senha = etSenha.getText().toString();
				
				try {
					if(API.login(login, senha)){
						setMedicoes();
						Intent intent = new Intent(LoginActivity.this, MainActivity.class);
						startActivity(intent);
					}else{
						String msg = "Usu�rio n�o existe.";
						String title = "Login";
						Dialogo.showDialogo(title, msg, LoginActivity.this);
					}
					
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}

			/**
			 * Assim que confirmado o login, seta as medicoes dependendo do tipo de usuario
			 */
			private void setMedicoes() {
				try {
					API.user.setMedicoes(API.getMedicoesPorPredio(API.user.getId()));
					System.out.println("oi");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		
	}

	private void cadastrar() {
		etCadastrar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(LoginActivity.this, CadastroPredioActivity.class);
				startActivity(intent);
			}
		});
		
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		etCadastrar = (TextView) findViewById(R.id.login_text_cadastrarse);
		btnEntrar = (Button) findViewById(R.id.btn_entrar);
		etLogin = (EditText) findViewById(R.id.et_login);
		etSenha = (EditText) findViewById(R.id.et_senha);
		
		cadastrar();

		logar();
	}
}
