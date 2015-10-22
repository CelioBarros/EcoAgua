package com.example.ecoagua;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.example.model.Predio;
import com.example.scrollable.RankingList;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class RankingActivity extends Activity {
	private ListView list;
	private RankingList rankingList;
	private List<Predio> itens;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ranking);

		list = (ListView) findViewById(R.id.lvExpRanking);
		itens = new ArrayList<Predio>();

		// so pra testar
		criaItensTest();
		// necessario fazer o sorte antes
		Collections.sort(itens);
		rankingList = new RankingList(this, itens, list);
		list.setAdapter(rankingList);
	}

	private void criaItensTest() {
		// dados pra teste
		String posicao1 = "1";
		String posicao2 = "2";

		String nome1 = "nome1";
		String nome2 = "nome2";

		String gasto1 = "20";
		String gasto2 = "30";

		Predio predio1 = new Predio(nome1, nome1, nome1, nome1, null);
		Predio predio2 = new Predio(nome2, nome1, nome1, nome1, null);

		predio1.setColocacao(Integer.parseInt(posicao1));
		predio2.setColocacao(Integer.parseInt(posicao2));

		predio1.setGastosMesAtual(Float.parseFloat(gasto1));
		predio2.setGastosMesAtual(Float.parseFloat(gasto2));

		itens.add(predio1);
		itens.add(predio2);
	}
}
