package com.eliarlan.edevmoneyapi.dto;

public class Anexo {
	
	private String nome;
	
	private String url;

	public Anexo(String nome, String url) {
		super();
		this.nome = nome;
		this.url = url;
	}

	public String getNome() {
		return nome;
	}

	public String getUrl() {
		return url;
	}
	
	
}
