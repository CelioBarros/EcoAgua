package com.example.ecoagua.grafico;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import com.example.controller.API;
import com.example.ecoagua.R;
import com.example.model.CalendarUtils;
import com.example.model.Medicao;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.DatePicker;
import android.widget.EditText;

public class MesActivity extends Activity{
	private Calendar dia;
	private GraphView graph;
	private EditText etData;
	private LineGraphSeries<DataPoint> series;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mes);

		dia = Calendar.getInstance();

		graph = (GraphView) findViewById(R.id.graph_mes);
		etData = (EditText) findViewById(R.id.ed_data_mes);

		criaGrafico();
		setData();
	}

	private void criaGrafico(){
		Serie serie = new Serie();
		
		String titulo = "Consumo (L)";
		int cor = Color.BLUE;
		boolean drawDataPoints = true;
		int raio = 8;
		List<Medicao> medicoes = API.user.getMedicoesMes(dia);
		int numX = CalendarUtils.getQtdDiasMes(dia);
		Context context = MesActivity.this;
		boolean showTextOnPointClick = true;
				 
		series = serie.criaSerie(titulo, cor, drawDataPoints, raio, dia, medicoes, numX, context, showTextOnPointClick);
		
		
		Grafico grafico = new Grafico();
		
		boolean legenda = true;
		boolean scroll = true;
		
		boolean comLabels = false;
		int numLabels = 7;
		boolean soIntEmX = false;
		
		
		grafico.criaGrafico(graph, series, legenda, comLabels, numLabels, soIntEmX, scroll);
	}

	//
	private void setData() {

		final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

			@Override
			public void onDateSet(DatePicker view, int year, int monthOfYear,
					int dayOfMonth) {
				dia.set(Calendar.YEAR, year);
				dia.set(Calendar.MONTH, monthOfYear);
				dia.set(Calendar.DAY_OF_MONTH, dayOfMonth);
				updateLabel();

			}

		};

		etData.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				new DatePickerDialog(MesActivity.this, date, dia.get(Calendar.YEAR),
						dia.get(Calendar.MONTH), dia.get(Calendar.DAY_OF_MONTH))
						.show();
			}
		});

	}

	private void updateLabel() {

		String myFormat = "dd/MM/yyyy"; // In which you need put here
		SimpleDateFormat sdf = new SimpleDateFormat(myFormat, new Locale("pt",
				"BR"));

		etData.setText(sdf.format(dia.getTime()));

		criaGrafico();
	}
}
