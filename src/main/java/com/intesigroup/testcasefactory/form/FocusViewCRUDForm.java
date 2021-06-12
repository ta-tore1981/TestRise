package com.intesigroup.testcasefactory.form;

public class FocusViewCRUDForm {
	Long id;
	String nome;
	String descrizione;
	Long idFunzionalita;
	Long idInterfaccia;
	Long idProgetto;
	String codice;
	String nomeProgetto;
	String nomeInterfaccia;
	String nomeFunzionalita;
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


	public Long getIdInterfaccia() {
		return idInterfaccia;
	}


	public void setIdInterfaccia(Long idInterfaccia) {
		this.idInterfaccia = idInterfaccia;
	}


	public Long getIdProgetto() {
		return idProgetto;
	}


	public void setIdProgetto(Long idProgetto) {
		this.idProgetto = idProgetto;
	}


	public String getNomeProgetto() {
		return nomeProgetto;
	}


	public void setNomeProgetto(String nomeProgetto) {
		this.nomeProgetto = nomeProgetto;
	}


	public String getNomeInterfaccia() {
		return nomeInterfaccia;
	}


	public void setNomeInterfaccia(String nomeInterfaccia) {
		this.nomeInterfaccia = nomeInterfaccia;
	}


	public String getNomeFunzionalita() {
		return nomeFunzionalita;
	}


	public void setNomeFunzionalita(String nomeFunzionalita) {
		this.nomeFunzionalita = nomeFunzionalita;
	}
	
	
}
