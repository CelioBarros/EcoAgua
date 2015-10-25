package com.example.ecoagua;

import com.example.controller.API;
import com.example.model.Morador;
import com.example.model.Predio;

import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
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

		btnSair = (Button) findViewById(R.id.btn_sair);
		btnCadastrarMedicao = (Button) findViewById(R.id.btn_cadastrar_medicao);
		tvColocacao = (TextView) findViewById(R.id.tv_perfil_colocacao);
		tvEmail = (TextView) findViewById(R.id.tv_perfil_email);
		tvEndereco = (TextView) findViewById(R.id.tv_perfil_endereco);
		tvNome = (TextView) findViewById(R.id.tv_perfil_nome);
		tvTelefone = (TextView) findViewById(R.id.tv_perfil_telefone);
		tvUserName = (TextView) findViewById(R.id.tv_perfil_predio_username);
		etMedicao = (EditText) findViewById(R.id.et_medicao);

		btnCadastrarMoradorPerfil = (Button) findViewById(R.id.btn_cadastrar_morador_perfil);
		
		checkUser();
		sair();

	}

	private void checkUser() {
		// desaparece se o usuario logado e do tipo morador
		LinearLayout llMedicao = (LinearLayout) findViewById(R.id.ll_medicao);
		if (API.user.getClass() == Morador.class) {
			llMedicao.setVisibility(View.INVISIBLE);
			btnCadastrarMoradorPerfil.setVisibility(View.INVISIBLE);
			
			Morador morador = (Morador) API.user;
			Predio predio = morador.getPredio();
			
			setDados(predio.getColocacao(), predio.getEmail(), predio
					.getEndereco().toString(), morador.getNome(),
					predio.getTelefone());
		} else {
			cadastrarMedicao();
			cadastrarMorador();
			Predio predio = (Predio) API.user;
			setDados(predio.getColocacao(), predio.getEmail(), predio
					.getEndereco().toString(), predio.getNome(),
					predio.getTelefone());
		}

	}

	private void setDados(int colocacao, String email, String endereco,
			String nome, String telefone) {
		// setar os dados da tela de perfil, obs: pegar os dados do usuario
		// logado
		tvColocacao.setText(Integer.toString(colocacao));
		tvEmail.setText(email);
		tvEndereco.setText(endereco);
		tvNome.setText(nome);
		tvTelefone.setText(telefone);
		tvUserName.setText(nome);

	}

	private void sair() {
		btnSair.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// fazer alguma coisa pra avisar ao controller que o usuario
				// deslogou

				API.user = null;

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
				// Float medicaoF = Float.parseFloat(medicao);
				// Medicao medicao = new Medicao(medicaoF, predio);
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
