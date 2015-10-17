package com.example.ecoagua;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.PointsGraphSeries;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

public class EstatisticasActivity extends Activity {
	// saber quantos dias tem o m�s
	private Calendar hoje;
	private GraphView graph;
	private LineGraphSeries<DataPoint> seriesMes;
	private LineGraphSeries<DataPoint> seriesSemana;
	private Spinner tempo;
	private EditText etData;
	private String selected;
	private LineGraphSeries<DataPoint> series;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_estatisticas);

		hoje = Calendar.getInstance();
		selected = "Semana";

		graph = (GraphView) findViewById(R.id.graph);
		tempo = (Spinner) findViewById(R.id.tempo);
		etData = (EditText) findViewById(R.id.ed_data);

		setSppinerTempo(); // coloca as opcoes semana e mes
		setData(hoje); // mostra a data pra selecionar
		setSelected(); // seta o grafico quando muda o sppiner
		setSeries(hoje); // seta series pra criar o grafico
	}

	private void setSeries(Calendar dia) {
		seriesSemana = new LineGraphSeries<DataPoint>(
				criaDataPoints(getQtdDiasSemana()));
		seriesMes = new LineGraphSeries<DataPoint>(
				criaDataPoints(getQtdDiasMes(dia)));
	}

	private void setSelected() {
		tempo.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				selected = parent.getItemAtPosition(position).toString();

				if (selected.equals("Semana")) {
					selected = "Semana";
					criaGrafico();
				} else {
					selected = "M�s";
					criaGrafico();
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				criaGrafico();
			}

		});

	}

	private void criaGrafico() {

		checkSelected();

		graph.removeAllSeries();
		graph.addSeries(series);

		// legenda
		series.setTitle("Consumo (L)");
		graph.getLegendRenderer().setVisible(true);
		graph.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);

		// setNumLabelsX(7);

		xSoInt();

		scroll();
	}

	private DataPoint[] criaDataPoints(int qtdDias) {
		int start = 0;
		DataPoint[] data = new DataPoint[qtdDias + 1];
		
		//mudar o valor de 1 para os dados de medicao
		while (start != qtdDias + 1) {
			// substituir o 1 pelo dado da lista de medicoes
			data[start] = new DataPoint(start, 1);
			start++;
		}
		return data;
	}

	private int getQtdDiasSemana() {
		return 7;
	}

	//
	private void setData(final Calendar dia) {

		final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

			@Override
			public void onDateSet(DatePicker view, int year, int monthOfYear,
					int dayOfMonth) {
				dia.set(Calendar.YEAR, year);
				dia.set(Calendar.MONTH, monthOfYear);
				dia.set(Calendar.DAY_OF_MONTH, dayOfMonth);
				updateLabel(dia);

			}

		};

		etData.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				new DatePickerDialog(EstatisticasActivity.this, date, hoje
						.get(Calendar.YEAR), hoje.get(Calendar.MONTH), hoje
						.get(Calendar.DAY_OF_MONTH)).show();
			}
		});

	}

	private void updateLabel(Calendar dia) {

		String myFormat = "dd/MM/yyyy"; // In which you need put here
		SimpleDateFormat sdf = new SimpleDateFormat(myFormat, new Locale("pt",
				"BR"));

		etData.setText(sdf.format(dia.getTime()));

		// teria um atualiza dados
		setSeries(dia); // atualiza dias
		criaGrafico(); // atualiza grafico
	}

	private void checkSelected() {
		if (selected.equals("Semana")) {
			series = seriesSemana;
		} else {
			series = seriesMes;
		}
	}

	private void setNumLabelsX(int num) {
		// set num horizontal labels
		graph.getGridLabelRenderer().setNumHorizontalLabels(num);

	}

	private void xSoInt() {
		// custom label
		graph.getGridLabelRenderer().setLabelFormatter(
				new DefaultLabelFormatter() {
					@Override
					public String formatLabel(double value, boolean isValueX) {
						if (isValueX) {
							// show normal x values
							return super.formatLabel((int) value, isValueX);
						} else {
							// show currency for y values
							return super.formatLabel((int) value, isValueX);
						}
					}
				});

	}

	private void scroll() {
		// scroll
		graph.getViewport().setScrollable(true);
		graph.getViewport().setScalable(true);

	}

	private int getQtdDiasMes(Calendar dia) {
		return dia.getActualMaximum(Calendar.DAY_OF_MONTH);
	}

	private void setSppinerTempo() {
		List<String> tempoCategorias = new ArrayList<String>();
		tempoCategorias.add("Semana");
		tempoCategorias.add("M�s");

		ArrayAdapter<String> tempoAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, tempoCategorias);

		tempoAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		tempo.setAdapter(tempoAdapter);

	}

}
