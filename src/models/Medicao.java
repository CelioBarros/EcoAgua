package models;

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
