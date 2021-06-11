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
	
	@Basic(optional=true)
	@Column(name="controllo_performance")
	private String controlloPerformance;
	
	@Basic(optional=true)
	@Column(name="controllo_storage")
	private String controlloStorage;
	
	@Basic(optional=true)
	@Column(name="controllo_database")
	private String controlloDatabase;
	
	@Basic(optional=true)
	@Column(name="controllo_log")
	private String controlloLog;
	
	@Basic(optional=true)
	@Column(name="controllo_interfaccia")
	private String controlloInterfaccia;
	
	@Basic(optional=true)
	@Column(name="controllo_altro")
	private String controlloAltro;
	
	@Basic(optional=true)
	@Column(name="installation_test_interfaccia")
	private Boolean installationTestInterfaccia;
	
	@Basic(optional=true)
	@Column(name="installation_test_log")
	private Boolean installationTestLog;
	
	@Basic(optional=true)
	@Column(name="installation_test_performance")
	private Boolean installationTestPerformance;
	
	@Basic(optional=true)
	@Column(name="installation_test_storage")
	private Boolean installationTestStorage;
	
	@Basic(optional=true)
	@Column(name="installation_test_database")
	private Boolean installationTestDatabase;
	
	@Basic(optional=true)
	@Column(name="installation_test_altro")
	private Boolean installationTestAltro;
	
	@Basic(optional=true)
	@Column(name="numero_testcase")
	private Long numeroTestCase;
	
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

	public TipoTest getTipoTest() {
		return tipoTest;
	}

	public void setTipoTest(TipoTest tipoTest) {
		this.tipoTest = tipoTest;
	}

	public Progetto getProgetto() {
		return progetto;
	}

	public void setProgetto(Progetto progetto) {
		this.progetto = progetto;
	}

	public Interfaccia getInterfaccia() {
		return interfaccia;
	}

	public void setInterfaccia(Interfaccia interfaccia) {
		this.interfaccia = interfaccia;
	}

	public Funzionalita getFunzionalita() {
		return funzionalita;
	}

	public void setFunzionalita(Funzionalita funzionalita) {
		this.funzionalita = funzionalita;
	}

	public Focus getFocus() {
		return focus;
	}

	public void setFocus(Focus focus) {
		this.focus = focus;
	}

	public Approccio getApproccio() {
		return approccio;
	}

	public void setApproccio(Approccio approccio) {
		this.approccio = approccio;
	}

	public Attore getAttore() {
		return attore;
	}

	public void setAttore(Attore attore) {
		this.attore = attore;
	}

	public String getControlloStorage() {
		return controlloStorage;
	}

	public void setControlloStorage(String controlloStorage) {
		this.controlloStorage = controlloStorage;
	}

	public String getControlloDatabase() {
		return controlloDatabase;
	}

	public void setControlloDatabase(String controlloDatabase) {
		this.controlloDatabase = controlloDatabase;
	}

	public String getControlloPerformance() {
		return controlloPerformance;
	}

	public void setControlloPerformance(String controlloPerformance) {
		this.controlloPerformance = controlloPerformance;
	}

	public String getControlloLog() {
		return controlloLog;
	}

	public void setControlloLog(String controlloLog) {
		this.controlloLog = controlloLog;
	}

	public String getControlloInterfaccia() {
		return controlloInterfaccia;
	}

	public void setControlloInterfaccia(String controlloInterfaccia) {
		this.controlloInterfaccia = controlloInterfaccia;
	}

	public String getControlloAltro() {
		return controlloAltro;
	}

	public void setControlloAltro(String controlloAltro) {
		this.controlloAltro = controlloAltro;
	}

	public Boolean getInstallationTestInterfaccia() {
		return installationTestInterfaccia;
	}

	public void setInstallationTestInterfaccia(Boolean installationTestInterfaccia) {
		this.installationTestInterfaccia = installationTestInterfaccia;
	}

	public Boolean getInstallationTestLog() {
		return installationTestLog;
	}

	public void setInstallationTestLog(Boolean installationTestLog) {
		this.installationTestLog = installationTestLog;
	}

	public Boolean getInstallationTestPerformance() {
		return installationTestPerformance;
	}

	public void setInstallationTestPerformance(Boolean installationTestPerformance) {
		this.installationTestPerformance = installationTestPerformance;
	}

	public Boolean getInstallationTestStorage() {
		return installationTestStorage;
	}

	public void setInstallationTestStorage(Boolean installationTestStorage) {
		this.installationTestStorage = installationTestStorage;
	}

	public Boolean getInstallationTestDatabase() {
		return installationTestDatabase;
	}

	public void setInstallationTestDatabase(Boolean installationTestDatabase) {
		this.installationTestDatabase = installationTestDatabase;
	}

	public Boolean getInstallationTestAltro() {
		return installationTestAltro;
	}

	public void setInstallationTestAltro(Boolean installationTestAltro) {
		this.installationTestAltro = installationTestAltro;
	}

	public Long getNumeroTestCase() {
		return numeroTestCase;
	}

	public void setNumeroTestCase(Long numeroTestCase) {
		this.numeroTestCase = numeroTestCase;
	}
	
}
