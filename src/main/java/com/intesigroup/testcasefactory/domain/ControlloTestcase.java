package com.intesigroup.testcasefactory.domain;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="controllo_testcase")

public class ControlloTestCase implements Serializable {
	private static final long serialVersionUID = -5031623264043634242L;
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Basic(optional=true)
	@Column(name="controllo")
	private String controllo;
	
	@Column(name="installation_test")
	private boolean IntellationTest;
	
	@ManyToOne
	@JoinColumn(name="id_tipo_controllo",referencedColumnName="id")
	TipoControllo tipoControllo= new TipoControllo();
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getControllo() {
		return controllo;
	}

	public void setControllo(String descrizione) {
		this.controllo = descrizione;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public boolean isIntellationTest() {
		return IntellationTest;
	}

	public void setIntellationTest(boolean intellationTest) {
		IntellationTest = intellationTest;
	}

	public TipoControllo getTipoControllo() {
		return tipoControllo;
	}

	public void setTipoControllo(TipoControllo tipoControllo) {
		this.tipoControllo = tipoControllo;
	}
	
	

}
