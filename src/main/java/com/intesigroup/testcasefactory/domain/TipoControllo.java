package com.intesigroup.testcasefactory.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="tipo_controllo")


public class TipoControllo implements Serializable{

	private static final long serialVersionUID = 436015862077044826L;
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@OneToMany(fetch=FetchType.EAGER, mappedBy="tipoControllo")
	Set<ControlloTestCase>  controlloTestCase = new HashSet<ControlloTestCase>();
	
	@Basic(optional=false)
	private String nome;
	
	@Basic(optional=false)
	private String codice;

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

	public Set<ControlloTestCase> getControlloTestCase() {
		return controlloTestCase;
	}

	public void setControlloTestCase(Set<ControlloTestCase> controlloTestCase) {
		this.controlloTestCase = controlloTestCase;
	}
	
	
}
