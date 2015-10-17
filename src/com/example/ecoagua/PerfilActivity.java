package com.example.ecoagua;

import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class PerfilActivity extends Activity {
	private Button btnCadastrarMoradorPerfil;
	private Button btnSair;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_perfil);
		
		btnCadastrarMoradorPerfil = (Button) findViewById(R.id.btn_cadastrar_morador_perfil);
		btnSair = (Button) findViewById(R.id.btn_sair);
		
		cadastrarMorador();
	}

	private void cadastrarMorador() {
		btnCadastrarMoradorPerfil.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(PerfilActivity.this, CadastroMoradorActivity.class);
				startActivity(intent);
			}
		});
	}
}
