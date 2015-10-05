package com.example.ecoagua;

import com.example.model.Endereco;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class CadastroMoradorActivity extends Activity {
	private Button btnCadastrar;
	private EditText etNome;
	private EditText etSenha1;
	private EditText etPredio;
	private EditText etApartamento;
	
	private void cadastrarMorador() {
		btnCadastrar.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String nome = etNome.getText().toString();
				String senha = etSenha1.getText().toString();
				String predio = etPredio.getText().toString();
				String apartamento = etApartamento.getText().toString();
			}
		});
		
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cadastro_morador);

		btnCadastrar = (Button) findViewById(R.id.btn_cadastrar_morador);
		etNome = (EditText) findViewById(R.id.tf_morador_nome);
		etSenha1 = (EditText) findViewById(R.id.tf_morador_senha1);
		etPredio = (EditText) findViewById(R.id.tf_morador_predio);
		etApartamento = (EditText) findViewById(R.id.tf_morador_ap);
		
		cadastrarMorador();
	}

	

}
