package com.intesigroup.testcasefactory.domain;

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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;


@Entity
@Table(name="funzionalita")

public class Funzionalita {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotBlank
	@Basic(optional=false)
	private String nome;
	
	@NotBlank
	@Basic(optional=false)
	private String codice;
	
	@Basic(optional=true)
	private String descrizione;
	
	@ManyToMany(mappedBy = "funzionalita")
	Set<Attore> attore;
	
	@ManyToOne()
	@JoinColumn(name="id_interfaccia", referencedColumnName="id")
	private Interfaccia interfaccia= new Interfaccia();
	
	@OneToMany(fetch=FetchType.EAGER,cascade=CascadeType.ALL, mappedBy="funzionalita", orphanRemoval=true)
	private Set<Focus> focus=new HashSet<>();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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

	public Interfaccia getInterfaccia() {
		return interfaccia;
	}

	public void setInterfaccia(Interfaccia interfaccia) {
		this.interfaccia = interfaccia;
	}

	public Set<Focus> getFocus() {
		return focus;
	}

	public void setFocus(Set<Focus> focus) {
		this.focus = focus;
	}
}
