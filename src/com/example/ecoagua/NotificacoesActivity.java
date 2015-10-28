package com.example.ecoagua;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.example.ecoagua.R;
import com.example.model.Notificacao;
import com.example.model.Predio;
import com.example.scrollable.NotificacoesList;
import com.example.scrollable.RankingList;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class NotificacoesActivity extends Activity {
	private ListView list;
	private NotificacoesList notificacoesList;
	private List<Notificacao> itens;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_notificacoes);

		list = (ListView) findViewById(R.id.lvExpNotificacao);
		itens = new ArrayList<Notificacao>();

		// so pra testar
		criaItensTest();
		// necessario fazer o sorte antes
		Collections.sort(itens);
		notificacoesList = new NotificacoesList(this, itens, list);
		list.setAdapter(notificacoesList);
	}

	private void criaItensTest() {
		// dados pra teste
		String texto1 = "notificacao teste 1";
		String texto2 = "notificacao teste 2";

		String nome1 = "nome1";
		String nome2 = "nome2";

		Predio predio1 = new Predio(nome1, nome1, nome1, nome1, null, 1);
		Predio predio2 = new Predio(nome2, nome1, nome1, nome1, null, 2);

		Notificacao n1 = new Notificacao(predio1, texto1);
		Notificacao n2 = new Notificacao(predio2, texto2);
		
		itens.add(n1);
		itens.add(n2);

	}
}
