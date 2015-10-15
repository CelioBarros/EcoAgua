package com.example.ecoagua;

import android.annotation.SuppressLint;
import android.app.TabActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;

@SuppressLint("NewApi") @SuppressWarnings("deprecation")
public class MenuActivity extends TabActivity{
	
	private TabHost tabHost;
	
	private void setTabs() {
		tabHost = (TabHost) findViewById(android.R.id.tabhost);
		tabHost.setup();
		
		TabSpec home = tabHost.newTabSpec("Home");
		TabSpec ranking = tabHost.newTabSpec("Compor");
		TabSpec estatisticas = tabHost.newTabSpec("Sobre");
		TabSpec notificacoes = tabHost.newTabSpec("Home");
		
		home.setIndicator("", getDrawable(R.drawable.home));
		Intent homeIntent = new Intent(MenuActivity.this, PerfilActivity.class);
		home.setContent(homeIntent);
		 
		ranking.setIndicator("", getDrawable(R.drawable.ranking));
		Intent comporIntent = new Intent(MenuActivity.this, RankingAcitivity.class);
		ranking.setContent(comporIntent);

		estatisticas.setIndicator("", getDrawable(R.drawable.grafico));
		Intent sobreIntent = new Intent(MenuActivity.this, EstatisticasActivity.class);
		estatisticas.setContent(sobreIntent);

		notificacoes.setIndicator("", getDrawable(R.drawable.notificacoes));
		Intent buscaIntent = new Intent(MenuActivity.this, NotificacoesActivity.class);
		notificacoes.setContent(buscaIntent);
		
		tabHost.addTab(home);
		tabHost.addTab(ranking);
		tabHost.addTab(estatisticas);
		tabHost.addTab(notificacoes);
/*
		tabHost.setOnTabChangedListener(new OnTabChangeListener() {

			@Override
			public void onTabChanged(String arg0) {
				setTabColor(tabHost);
			}
		});
		setTabColor(tabHost);*/
	}
	
	private void setTabColor(TabHost tabhost) {
		for (int i = 0; i < tabhost.getTabWidget().getChildCount(); i++) {
			tabhost.getTabWidget().getChildAt(i).setBackgroundColor(Color.parseColor("#f8f5af")); //unselected
		}
		if(tabhost.getCurrentTab() == 0) {
			tabhost.getTabWidget().getChildAt(tabhost.getCurrentTab()).setBackgroundColor(Color.parseColor("#F5DA81")); //1st tab selected
		} else {
			tabhost.getTabWidget().getChildAt(tabhost.getCurrentTab()).setBackgroundColor(Color.parseColor("#F5DA81")); //2nd tab selected
		}
	}

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);
		
		setTabs();
	}

}
