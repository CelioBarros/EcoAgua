package com.example.ecoagua;

import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

public class PerfilActivity extends Activity {
	private Button btnCadastrarMoradorPerfil;
	private Button btnSair;
	private Button btnCadastrarMedicao;
	private TextView tvUserName;
	private TextView tvNome;
	private TextView tvTelefone;
	private TextView tvEmail;
	private TextView tvEndereco;
	private TextView tvColocacao;
	private EditText etMedicao;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_perfil);

		btnCadastrarMoradorPerfil = (Button) findViewById(R.id.btn_cadastrar_morador_perfil);
		btnSair = (Button) findViewById(R.id.btn_sair);
		btnCadastrarMedicao = (Button) findViewById(R.id.btn_cadastrar_medicao);
		tvColocacao = (TextView) findViewById(R.id.tv_perfil_colocacao);
		tvEmail = (TextView) findViewById(R.id.tv_perfil_email);
		tvEndereco = (TextView) findViewById(R.id.tv_perfil_endereco);
		tvNome = (TextView) findViewById(R.id.tv_perfil_nome);
		tvTelefone = (TextView) findViewById(R.id.tv_perfil_telefone);
		tvUserName = (TextView) findViewById(R.id.tv_perfil_predio_username);
		etMedicao = (EditText) findViewById(R.id.et_medicao);
		
		setDados();
		sair();
		cadastrarMedicao();
		cadastrarMorador();
	}

	private void setDados() {
		// setar os dados da tela de perfil, obs: pegar os dados do usuario logado
		tvColocacao.setText("a");
		tvEmail.setText("b");
		tvEndereco.setText("b");
		tvNome.setText("b");
		tvTelefone.setText("b");
		tvUserName.setText("b");
		
	}

	private void sair() {
		btnSair.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// fazer alguma coisa pra avisar ao controller que o usuario
				// deslogou

				Intent intent = new Intent(PerfilActivity.this,
						LoginActivity.class);
				startActivity(intent);
			}
		});

	}

	private void cadastrarMedicao() {
		btnCadastrarMedicao.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// chama controller pra cadastrar a medicao
				String medicao = etMedicao.getText().toString();
				//Float medicaoF = Float.parseFloat(medicao);
				//Medicao medicao = new Medicao(medicaoF, predio);
			}
		});

	}

	private void cadastrarMorador() {
		btnCadastrarMoradorPerfil.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(PerfilActivity.this,
						CadastroMoradorActivity.class);
				startActivity(intent);
			}
		});
	}
}
