package com.intesigroup.testcasefactory.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="approccio")

public class Approccio implements Serializable{
	private static final long serialVersionUID = -1671435270164696898L;
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Basic(optional=false)
	@Column(name="nome")
	private String nome;
	
	@Column(name="codice")
	@Basic(optional=false)
	private String codice;
	
	@Column(name="descrizione")
	@Basic(optional=true)
	private String descrizione;
	
	@OneToMany(fetch=FetchType.EAGER, mappedBy="approccio")
	Set<TestCase> testCase=new HashSet<>();
	
	
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Set<TestCase> getTestCase() {
		return testCase;
	}

	public void setTestCase(Set<TestCase> testCase) {
		this.testCase = testCase;
	}
	
	
}
