package com.intesigroup.testcasefactory.form;

public class FocusViewCRUDForm {
	Long id;
	String nome;
	String descrizione;
	Long idFunzionalita;
	String codice;
	
	
	public FocusViewCRUDForm() {
		id=null;
		idFunzionalita=null;
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


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getDescrizione() {
		return descrizione;
	}


	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}


	public Long getIdFunzionalita() {
		return idFunzionalita;
	}


	public void setIdFunzionalita(Long idFunzionalita) {
		this.idFunzionalita = idFunzionalita;
	}


	public String getCodice() {
		return codice;
	}


	public void setCodice(String codice) {
		this.codice = codice;
	}
	
}
