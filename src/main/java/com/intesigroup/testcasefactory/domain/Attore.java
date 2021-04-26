package com.intesigroup.testcasefactory.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name="attore")
@Data
public class Attore implements Serializable {

	private static final long serialVersionUID = -3058717740254774919L;
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Basic(optional=false)
	private String utente;
	
	@Basic(optional=true)
	private String caratteristiche_personali;
	
	@Basic(optional=true)
	private String esigenze_funzionali;
	
	@Basic(optional=true)
	private String esigenze_performance;
	
	@Basic(optional=true)
	private String esigenze_usabilita;
	
	@Basic(optional=false)
	private String codice;
	
	@Basic(optional=false)
	private String descrizione;
	
	@OneToMany(fetch=FetchType.EAGER, cascade = CascadeType.ALL, mappedBy="attore", orphanRemoval=true)
	private Set<InterfacciaAttore> interfacciaAttore=new HashSet<>();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUtente() {
		return utente;
	}

	public void setUtente(String utente) {
		this.utente = utente;
	}

	public String getCaratteristiche_personali() {
		return caratteristiche_personali;
	}

	public void setCaratteristiche_personali(String caratteristiche_personali) {
		this.caratteristiche_personali = caratteristiche_personali;
	}

	public String getEsigenze_funzionali() {
		return esigenze_funzionali;
	}

	public void setEsigenze_funzionali(String esigenze_funzionali) {
		this.esigenze_funzionali = esigenze_funzionali;
	}

	public String getEsigenze_performance() {
		return esigenze_performance;
	}

	public void setEsigenze_performance(String esigenze_performance) {
		this.esigenze_performance = esigenze_performance;
	}

	public String getEsigenze_usabilita() {
		return esigenze_usabilita;
	}

	public void setEsigenze_usabilita(String esigenze_usabilita) {
		this.esigenze_usabilita = esigenze_usabilita;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Set<InterfacciaAttore> getInterfacciaAttore() {
		return interfacciaAttore;
	}

	public void setInterfacciaAttore(Set<InterfacciaAttore> interfacciaAttore) {
		this.interfacciaAttore = interfacciaAttore;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

}