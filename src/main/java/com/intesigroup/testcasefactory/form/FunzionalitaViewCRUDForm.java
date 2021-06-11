package com.intesigroup.testcasefactory.form;

public class FunzionalitaViewCRUDForm {
	Long id;
	String nome;
	String descrizione;
	Long idInterfaccia;
	String codice;
	
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
	
}
