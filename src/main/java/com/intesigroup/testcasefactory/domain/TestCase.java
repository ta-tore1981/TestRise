package com.intesigroup.testcasefactory.domain;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="testcase")

public class TestCase implements Serializable{
	private static final long serialVersionUID = 1034800209864503975L;
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Basic(optional=false)
	private String codice;
	
	@Basic(optional=true)
	private String comportamento;
	
	@Basic(optional=true)
	private String procedura_test;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public String getComportamento() {
		return comportamento;
	}

	public void setComportamento(String comportamento) {
		this.comportamento = comportamento;
	}

	public String getProcedura_test() {
		return procedura_test;
	}

	public void setProcedura_test(String procedura_test) {
		this.procedura_test = procedura_test;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
