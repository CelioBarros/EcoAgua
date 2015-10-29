package com.example.ecoagua;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.json.JSONException;

import com.example.controller.API;
import com.example.model.Notificacao;
import com.example.model.Predio;
import com.example.scrollable.NotificacoesList;
import com.example.scrollable.RankingList;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
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
		
		
		try {
			itens = API.getNotificacoesPorPredio(API.user.getId());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// so pra testar
		//criaItensTest();
		// necessario fazer o sorte antes
		Collections.sort(itens);
		notificacoesList = new NotificacoesList(this, itens, list);
		list.setAdapter(notificacoesList);
	}
/*
	private void criaItensTest() {
		// dados pra teste
		String texto1 = "notificacao teste 1";
		String texto2 = "notificacao teste 2";
		

		Notificacao n1 = new Notificacao(predio1, texto1);
		Notificacao n2 = new Notificacao(predio2, texto2);
		
		itens.add(n1);
		itens.add(n2);

	}
	*/
}
