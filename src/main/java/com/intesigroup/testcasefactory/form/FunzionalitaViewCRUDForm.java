package com.intesigroup.testcasefactory.form;

public class FunzionalitaViewCRUDForm {
	Long id;
	String nome;
	String descrizione;
	Long idInterfaccia;
	Long idProgetto;
	String codice;
	String nomeInterfaccia;
	String nomeProgetto;
	
	public FunzionalitaViewCRUDForm() {
		id=null;
		idInterfaccia=null;
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

	public Long getIdInterfaccia() {
		return idInterfaccia;
	}

	public void setIdInterfaccia(Long idInterfaccia) {
		this.idInterfaccia = idInterfaccia;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public String getNomeInterfaccia() {
		return nomeInterfaccia;
	}

	public void setNomeInterfaccia(String nomeInterfaccia) {
		this.nomeInterfaccia = nomeInterfaccia;
	}

	public String getNomeProgetto() {
		return nomeProgetto;
	}

	public void setNomeProgetto(String nomeProgetto) {
		this.nomeProgetto = nomeProgetto;
	}

	public Long getIdProgetto() {
		return idProgetto;
	}

	public void setIdProgetto(Long idProgetto) {
		this.idProgetto = idProgetto;
	}
	
}
