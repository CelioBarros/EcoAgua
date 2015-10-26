package com.example.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Medicao {
	private float valor;
	private final String unidadeMedida = "L";
	private Calendar data;
	private Predio predio;

	public Medicao(float valor, Predio predio) {
		setValor(valor);
		setData(Calendar.getInstance());
	}
	
	public Medicao(float valor, String data) {
		setValor(valor);
		
		Calendar cal = Calendar.getInstance();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		try {
			cal.setTime(sdf.parse(data));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		setData(cal);
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}

	public String getUnidadeMedida() {
		return unidadeMedida;
	}

	public Calendar getData() {
		return data;
	}

	public void setData(Calendar data) {
		this.data = data;
	}

	public Predio getPredio() {
		return predio;
	}

	public void setPredio(Predio predio) {
		if (predio == null) {
			throw new IllegalArgumentException("Predio � obrigat�rio.");

		}
		this.predio = predio;
	}
}
