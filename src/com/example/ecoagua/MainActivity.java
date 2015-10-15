package com.example.ecoagua;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
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
			}
		});
		
	}

	private void cadastrar() {
		etCadastrar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(MainActivity.this, CadastroPredioActivity.class);
				startActivity(intent);
			}
		});
		
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
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
