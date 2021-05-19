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

@Entity
@Table(name="testcase")

public class TestCase implements Serializable{
	private static final long serialVersionUID = 1034800209864503975L;
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Basic(optional=true)
	@Column(name="testid")
	private String testId;
	
	@Basic(optional=true)
	@Column(name="usecase")
	private String useCase;
	
	@Basic(optional=true)
	@Column(name="procedura_test")
	private String proceduraTest;
	
	@ManyToOne
	@JoinColumn(name="id_tipo_test",referencedColumnName="id")
	TipoTest  tipoTest= new TipoTest();
	
	@ManyToOne
	@JoinColumn(name="id_progetto",referencedColumnName="id")
	Progetto progetto= new Progetto();
	
	@ManyToOne
	@JoinColumn(name="id_interfaccia",referencedColumnName="id")
	Interfaccia interfaccia=new Interfaccia();
	
	@ManyToOne
	@JoinColumn(name="id_funzionalita",referencedColumnName="id")
	Funzionalita funzionalita= new Funzionalita();
	
	@ManyToOne
	@JoinColumn(name="id_focus",referencedColumnName="id")
	Focus focus;
	
	@ManyToOne
	@JoinColumn(name="id_approccio", referencedColumnName = "id")
	Approccio approccio=new Approccio();
	
	@ManyToOne
	@JoinColumn(name="id_attore", referencedColumnName = "id")
	Attore attore= new Attore();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTestId() {
		return testId;
	}

	public void setTestId(String testId) {
		this.testId = testId;
	}

	public String getUseCase() {
		return useCase;
	}

	public void setUseCase(String comportamento) {
		this.useCase = comportamento;
	}

	public String getProceduraTest() {
		return proceduraTest;
	}

	public void setProceduraTest(String procedura_test) {
		this.proceduraTest = procedura_test;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
