package com.example.model;

public class InfoAcude {

	
	private String  nome, volume, data;
	private int id;
	
	public InfoAcude(String nome, String volume, String data, int id){
		setNome(nome);
		setVolume(volume);
		setData(data);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getVolume() {
		return volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
}
