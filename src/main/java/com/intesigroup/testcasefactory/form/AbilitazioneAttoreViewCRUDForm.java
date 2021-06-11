package com.intesigroup.testcasefactory.form;

import com.intesigroup.testcasefactory.domain.Attore;
import com.intesigroup.testcasefactory.domain.Funzionalita;

public class AbilitazioneAttoreViewCRUDForm {
	private Long idFunzionalita;
	private Long idAttore;
	private boolean abilitato;
	private String nomeAttore;
	public AbilitazioneAttoreViewCRUDForm() {
		super();

	}
	public AbilitazioneAttoreViewCRUDForm(Long idFunzione, Attore attore, boolean abilitato) {
		super();
		this.idFunzionalita = idFunzione;
		this.idAttore=attore.getId();
		this.abilitato = abilitato;
		nomeAttore=attore.getNome();
	}
	public String getNomeAttore() {
		return nomeAttore;
	}
	public void setNomeAttore(String nomeAttore) {
		this.nomeAttore = nomeAttore;
	}

	public Long getIdAttore() {
		return idAttore;
	}
	public void setIdAttore(Long idAttore) {
		this.idAttore = idAttore;
	}
	public boolean isAbilitato() {
		return abilitato;
	}
	public void setAbilitato(boolean abilitato) {
		this.abilitato = abilitato;
	}
	public Long getIdFunzionalita() {
		return idFunzionalita;
	}
	public void setIdFunzionalita(Long idFunzionalita) {
		this.idFunzionalita = idFunzionalita;
	}
}
