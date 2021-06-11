package com.intesigroup.testcasefactory.form;

public class AttoreViewCRUDForm {
	Long id;
	String nome;
	String descrizione;
	Long idProgetto;
	String codice;

	public AttoreViewCRUDForm() {
		id=null;
		nome="";
		descrizione="";
		codice="";
		idProgetto=null;
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
	public void setNome(String attore) {
		this.nome = attore;
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

}
