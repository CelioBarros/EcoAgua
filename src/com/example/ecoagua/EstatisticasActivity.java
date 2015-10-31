package com.example.ecoagua;

import com.example.ecoagua.R;
import com.example.ecoagua.grafico.AcudeVolumeActivity;
import com.example.ecoagua.grafico.MesActivity;
import com.example.ecoagua.grafico.SemanaActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
public class EstatisticasActivity extends Activity {
	private Button btnMensal;
	private Button btnSemanal;
	private Button btnVolume;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_estatisticas);
		btnMensal = (Button) findViewById(R.id.btn_grafico_mensal);
		btnSemanal = (Button) findViewById(R.id.btn_grafico_semanal);
		btnVolume = (Button) findViewById(R.id.btn_grafico_acude_volume);
		
		verGraficoConsumoMensal();
		verGraficoConsumoSemanal();
		verGraficoAcudeVolume();
	}

	private void verGraficoAcudeVolume() {
		btnVolume.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(EstatisticasActivity.this, AcudeVolumeActivity.class);
				startActivity(intent);
				
			}
		});
		
	}

	private void verGraficoConsumoSemanal() {
		btnSemanal.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(EstatisticasActivity.this, SemanaActivity.class);
				startActivity(intent);
				
			}
		});
		
	}

	private void verGraficoConsumoMensal() {
		btnMensal.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(EstatisticasActivity.this, MesActivity.class);
				startActivity(intent);
				
			}
		});
		
	}


}
