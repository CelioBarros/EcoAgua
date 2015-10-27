package com.example.ecoagua;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import com.example.model.CalendarUtils;
import com.example.model.Medicao;
import com.example.model.Usuario;
import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.DataPointInterface;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.OnDataPointTapListener;
import com.jjoe64.graphview.series.Series;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class EstatisticasActivity extends Activity {
	// saber quantos dias tem o mês
	private Calendar dia;
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

		dia = Calendar.getInstance();
		selected = "Semana";

		graph = (GraphView) findViewById(R.id.graph);
		tempo = (Spinner) findViewById(R.id.tempo);
		etData = (EditText) findViewById(R.id.ed_data);

		setSppinerTempo(); // coloca as opcoes semana e mes
		setData(); // mostra a data pra selecionar
		setSelected(); // seta o grafico quando muda o sppiner
		setSeries(); // seta series pra criar o grafico
	}

	private void setSeries() {
		seriesSemana = new LineGraphSeries<DataPoint>(
				criaDataPoints(getQtdDiasSemana()));
		seriesMes = new LineGraphSeries<DataPoint>(
				criaDataPoints(CalendarUtils.getQtdDiasMes(dia)));
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
					selected = "Mês";
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
		scroll(false);
		graph.removeAllSeries();
		graph.addSeries(series);
		
		setPropriedadesSerie();
		
		// legenda
		graph.getLegendRenderer().setVisible(true);
		graph.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);	

		// setNumLabelsX(7);

		//xSoInt();

		scroll(true);
	}

	private void setPropriedadesSerie() {
		// legenda
		series.setTitle("Consumo (L)");
		// styling series
		series.setColor(Color.GREEN);
		series.setDrawDataPoints(true);
		series.setDataPointsRadius(10);
		series.setThickness(8);
		
		series.setOnDataPointTapListener(new OnDataPointTapListener() {
			@Override
			public void onTap(Series series, DataPointInterface dataPoint) {
				Calendar temp = dia;
				temp.set(Calendar.DAY_OF_MONTH, (int)dataPoint.getX());
				
				Toast.makeText(
						EstatisticasActivity.this,
						"Consumo: " + dataPoint.getY() + " L"+ "\nData: " + CalendarUtils.getDataFormatadaSemHoras(temp)
								,
						Toast.LENGTH_SHORT).show();
			}
		});
	}

	private DataPoint[] criaDataPoints(int qtdDias) {
		int start = 1;
		DataPoint[] data = new DataPoint[qtdDias];
		
		List<Medicao> medicoes;
		if(qtdDias == 7){ //semana
			medicoes = Usuario.getMedicoesSemana(dia);
		}else{
			medicoes = Usuario.getMedicoesMes(dia);
		}
		
		

		// mudar o valor de 1 para os dados de medicao
		while ((start < qtdDias+1) && (start < medicoes.size()+1)) {
			// substituir o 1 pelo dado da lista de medicoes
			data[start-1] = new DataPoint(start, medicoes.get(start-1).getValor());
			start++;
		}
		
		return data;
	}

	private int getQtdDiasSemana() {
		return 7;
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
				new DatePickerDialog(EstatisticasActivity.this, date, dia
						.get(Calendar.YEAR), dia.get(Calendar.MONTH), dia
						.get(Calendar.DAY_OF_MONTH)).show();
			}
		});

	}

	private void updateLabel() {

		String myFormat = "dd/MM/yyyy"; // In which you need put here
		SimpleDateFormat sdf = new SimpleDateFormat(myFormat, new Locale("pt",
				"BR"));

		etData.setText(sdf.format(dia.getTime()));

		// teria um atualiza dados
		setSeries(); // atualiza dias
		selected = "Semana";
		setSppinerDefault();
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

	private void scroll(boolean scroll) {
		// scroll
		graph.getViewport().setScrollable(scroll);
		graph.getViewport().setScalable(scroll);

	}

	private void setSppinerTempo() {
		List<String> tempoCategorias = new ArrayList<String>();
		tempoCategorias.add("Semana");
		tempoCategorias.add("Mês");

		ArrayAdapter<String> tempoAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, tempoCategorias);

		tempoAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		tempo.setAdapter(tempoAdapter);

	}
	
	private void setSppinerDefault(){
		String defaultSp = "Semana"; //the value you want the position for

		ArrayAdapter myAdap = (ArrayAdapter) tempo.getAdapter(); //cast to an ArrayAdapter

		int spinnerPosition = myAdap.getPosition(defaultSp);

		//set the default according to value
		tempo.setSelection(spinnerPosition);
	}



}
