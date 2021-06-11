package com.intesigroup.testcasefactory.form;

public class RicercaTestCaseView {
	Long idProgetto;
	String nomeProgetto;
	Long idInterfaccia;
	String nomeInterfaccia;
	Long idFunzionalita;
	String nomeFunzionalita;
	Long idFocus;
	String nomeFocus;
	
	public RicercaTestCaseView() {		
		nomeProgetto= new String();
		nomeInterfaccia= new String();
		nomeFunzionalita= new String();
		nomeFocus=new String(); 
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
	public Long getIdInterfaccia() {
		return idInterfaccia;
	}
	public void setIdInterfaccia(Long idInterfaccia) {
		this.idInterfaccia = idInterfaccia;
	}
	public String getNomeInterfaccia() {
		return nomeInterfaccia;
	}
	public void setNomeInterfaccia(String nomeInterfaccia) {
		this.nomeInterfaccia = nomeInterfaccia;
	}
	public Long getIdFunzionalita() {
		return idFunzionalita;
	}
	public void setIdFunzionalita(Long idFunzionalita) {
		this.idFunzionalita = idFunzionalita;
	}
	public String getNomeFunzionalita() {
		return nomeFunzionalita;
	}
	public void setNomeFunzionalita(String nomeFunzionalita) {
		this.nomeFunzionalita = nomeFunzionalita;
	}
	public Long getIdFocus() {
		return idFocus;
	}
	public void setIdFocus(Long idFocus) {
		this.idFocus = idFocus;
	}
	public String getNomeFocus() {
		return nomeFocus;
	}
	public void setNomeFocus(String nomeFocus) {
		this.nomeFocus = nomeFocus;
	}
	
	
}
