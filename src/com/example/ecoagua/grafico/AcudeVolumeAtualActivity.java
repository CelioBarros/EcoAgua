package com.example.ecoagua.grafico;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.controller.API;
import com.example.ecoagua.LoginActivity;
import com.example.ecoagua.R;
import com.example.model.CalendarUtils;
import com.example.model.Medicao;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class AcudeVolumeAtualActivity extends Activity {
	private Calendar dia;
	private GraphView graph;
	private EditText etData;
	private LineGraphSeries<DataPoint> series;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_acude_volume_atual);

		dia = Calendar.getInstance();

		graph = (GraphView) findViewById(R.id.graph_acude_atual);
		etData = (EditText) findViewById(R.id.ed_data_acude);

		criaGrafico();
	}

	private void criaGrafico() {
		Serie serie = new Serie();

		String titulo = "Volume (m�)";
		int cor = Color.BLUE;
		boolean drawDataPoints = true;
		int raio = 8;
		List<Medicao> medicoes = LoginActivity.medicoesAcude;
		int numX = medicoes.size();
		Context context = AcudeVolumeAtualActivity.this;
		boolean showTextOnPointClick = true;
		String legendaT = "Volume: ";
		String unidade = "m�";
		series = serie.criaSerie(titulo, cor, drawDataPoints, raio, dia,
				medicoes, numX, context, showTextOnPointClick, legendaT,
				unidade);

		Grafico grafico = new Grafico();

		boolean legenda = true;
		boolean scroll = true;

		boolean comLabels = false;
		int numLabels = 7;
		boolean soIntEmX = false;

		String tituloX = "Dia";

		grafico.criaGrafico(graph, series, legenda, comLabels, numLabels,
				soIntEmX, scroll, tituloX, titulo);
	}
}