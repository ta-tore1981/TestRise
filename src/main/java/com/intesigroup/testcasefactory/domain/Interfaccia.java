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
@Table(name="interfaccia")
public class Interfaccia implements Serializable{
	private static final long serialVersionUID = 5685338486330698814L;
	
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
	
	@ManyToOne
	@JoinColumn(name="id_progetto",referencedColumnName="id")
	private Progetto progetto= new Progetto();
	
	@OneToMany(fetch=FetchType.EAGER, cascade = CascadeType.ALL, mappedBy="interfaccia", orphanRemoval=true)
	private Set<Funzionalita> funzionalita=new HashSet<>();
	
	

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

	public Progetto getProgetto() {
		return progetto;
	}

	public Set<Funzionalita> getFunzionalita() {
		return funzionalita;
	}

	public void setFunzionalita(Set<Funzionalita> funzionalita) {
		this.funzionalita = funzionalita;
	}

	public void setProgetto(Progetto progetto) {
		this.progetto = progetto;
	}
	
}
