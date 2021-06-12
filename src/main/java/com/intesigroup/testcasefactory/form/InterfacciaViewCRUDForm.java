package com.intesigroup.testcasefactory.form;

public class InterfacciaViewCRUDForm {
	Long id;
	String nome;
	String descrizione;
	Long idProgetto;
	String codice;
	String nomeProgetto;

	
	public InterfacciaViewCRUDForm() {
		id=null;
		idProgetto=null;
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


	public Long getIdProgetto() {
		return idProgetto;
	}


	public void setIdProgetto(Long idProgetto) {
		this.idProgetto = idProgetto;
	}


	public String getCodice() {
		return codice;
	}


	public void setCodice(String codice) {
		this.codice = codice;
	}


	public String getNomeProgetto() {
		return nomeProgetto;
	}


	public void setNomeProgetto(String nomeProgetto) {
		this.nomeProgetto = nomeProgetto;
	}
	
	
}
