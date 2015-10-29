package com.example.ecoagua.grafico;

import android.graphics.Color;

import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class Grafico {
	protected Grafico() {
		// TODO Auto-generated constructor stub
	}

	public GraphView criaGrafico(GraphView graph,
			LineGraphSeries<DataPoint> serie, boolean legenda,
			boolean comLabels, int numLabels, boolean soIntEmX, boolean scroll) {
		graph.removeAllSeries();
		graph.addSeries(serie);

		// parametros
		setScrollAndZoom(graph, scroll);
		setLegenda(graph, legenda);
		setNumLabelsX(graph, numLabels, comLabels);
		xSoInt(graph, soIntEmX);
		return graph;
	}

	public void setLegenda(GraphView graph, boolean check) {
		if (check) {

			graph.getLegendRenderer().setVisible(true);
			graph.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);
		}
	}

	public void setNumLabelsX(GraphView graph, int num, boolean check) {
		if (check) {
			graph.getGridLabelRenderer().setNumHorizontalLabels(num);
		}

	}

	public void xSoInt(GraphView graph, boolean check) {

		if (check) {

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

	}

	public void setScrollAndZoom(GraphView graph, boolean check) {
		if (check) {
			graph.getViewport().setScrollable(check);
			graph.getViewport().setScalable(check);
		}

	}
}
