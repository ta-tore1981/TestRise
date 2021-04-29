package com.intesigroup.testcasefactory.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="interfaccia_attore")
public class InterfacciaAttore {
	@Id
	@Column(name="id")
	Long id;
	
	@ManyToOne
	@JoinColumn(name="id_interfaccia", referencedColumnName="id")
	private Interfaccia interfaccia= new Interfaccia();
	
	@ManyToOne
	@JoinColumn(name="id_attore",referencedColumnName="id")
	private Attore attore= new Attore();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Interfaccia getInterfaccia() {
		return interfaccia;
	}

	public void setInterfaccia(Interfaccia interfaccia) {
		this.interfaccia = interfaccia;
	}	

}
