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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;


@Entity
@Table(name="focus")

public class Focus implements Serializable{
	
	private static final long serialVersionUID = 3771609294847163718L;
	
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
	
	@ManyToOne()
	@JoinColumn(name="id_funzionalita", referencedColumnName="id")
	private Funzionalita funzionalita= new Funzionalita();
	
	@OneToMany(fetch=FetchType.EAGER,mappedBy="focus")
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

	public Funzionalita getFunzionalita() {
		return funzionalita;
	}

	public void setFunzionalita(Funzionalita funzionalita) {
		this.funzionalita = funzionalita;
	}

	public Set<TestCase> getTestCase() {
		return testCase;
	}

	public void setTestCase(Set<TestCase> testCase) {
		this.testCase = testCase;
	}
	
	
	
}
