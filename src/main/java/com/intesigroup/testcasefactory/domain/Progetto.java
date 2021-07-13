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
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="progetto")
public class Progetto implements Serializable{
	private static final long serialVersionUID = 5235070022808386732L;
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotBlank
	@Column(name="nome")
	@Basic(optional=false)
	private String nome;
	
	@NotBlank
	@Column(name="codice")
	@Basic(optional=false)
	private String codice;
	
	@Column(name="descrizione")
	@Basic(optional=true)
	private String descrizione;
	
	@OneToMany(fetch=FetchType.EAGER,cascade=CascadeType.ALL, mappedBy="progetto", orphanRemoval=true)
	private Set<Interfaccia> Interfaccia=new HashSet<>();
	
	@OneToMany(fetch=FetchType.EAGER,cascade=CascadeType.ALL, mappedBy="progetto", orphanRemoval=true)
	private Set<Attore> Attore=new HashSet<>();
	
	@OneToMany(fetch=FetchType.LAZY,cascade=CascadeType.ALL, mappedBy="progetto", orphanRemoval=true)
	private Set<TestCase> testCase=new HashSet<>();
	
	
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

	public Set<Interfaccia> getInterfaccia() {
		return Interfaccia;
	}

	public void setInterfaccia(Set<Interfaccia> interfaccia) {
		Interfaccia = interfaccia;
	}
	public Set<Attore> getAttore() {
		return Attore;
	}
	public void setAttore(Set<Attore> attore) {
		Attore = attore;
	}
	public Set<TestCase> getTestCase() {
		return testCase;
	}
	public void setTestCase(Set<TestCase> testCase) {
		this.testCase = testCase;
	}
	
}
