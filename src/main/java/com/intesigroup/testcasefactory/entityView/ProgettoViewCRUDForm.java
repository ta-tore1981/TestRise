package com.intesigroup.testcasefactory.entityView;

public class ProgettoViewCRUDForm {
	Long id;
	String nome;
	String descrizione;
	String codice;
	
	public ProgettoViewCRUDForm() {
		id=null;
		nome="";
		descrizione="";
		codice="";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String name) {
		this.nome = name;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}
	
}
