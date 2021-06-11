package com.intesigroup.testcasefactory.form;

import com.intesigroup.testcasefactory.domain.TestCase;

public class TestCaseCRUDViewForm {
	private Long idTestCase;
	private Long idProgetto;
	private Long idInterfaccia;
	private Long idFunzionalita;
	private Long idFocus;
	private Long idAttore; 
	private Long idApproccio; 
	private Long idTipoTest;
	private String useCase; 
	private String proceduraTest;
	private String controlloInterfaccia;
	private String controlloLog;
	private String controlloDatabase;
	private String controlloPerformance;
	private String controlloStorage;
	private String controlloAltro;
	private Boolean installationLog; 
	private Boolean installationDatabase; 
	private Boolean installationPerformance;
	private Boolean installationStorage;
	private Boolean installationAltro;
	private Boolean installationInterfaccia;
	private String testId;
	
	public void set(TestCase testCase) {
		idTestCase= testCase.getId();
		idProgetto= testCase.getProgetto().getId();
		idInterfaccia = testCase.getInterfaccia().getId();
		idFunzionalita = testCase.getFunzionalita().getId();
		idFocus = testCase.getFocus().getId();
		idAttore = testCase.getFocus().getId();
		idApproccio = testCase.getFocus().getId();
		idTipoTest = testCase.getFocus().getId();
		useCase = testCase.getUseCase();
		proceduraTest = testCase.getProceduraTest();
		controlloInterfaccia = testCase.getControlloInterfaccia();
		controlloLog = testCase.getControlloLog();
		controlloDatabase = testCase.getControlloDatabase();
		controlloPerformance = testCase.getControlloPerformance();
		controlloStorage= testCase.getControlloStorage();
		controlloAltro=testCase.getControlloAltro();
		installationStorage = testCase.getInstallationTestStorage();
		installationAltro = testCase.getInstallationTestAltro();
		installationInterfaccia = testCase.getInstallationTestInterfaccia();
		testId = testCase.getTestId();
	}
	public Long getIdTestCase() {
		return idTestCase;
	}
	public void setIdTestCase(Long idTestCase) {
		this.idTestCase = idTestCase;
	}
	public Long getIdProgetto() {
		return idProgetto;
	}
	public void setIdProgetto(Long idProgetto) {
		this.idProgetto = idProgetto;
	}
	public Long getIdInterfaccia() {
		return idInterfaccia;
	}
	public void setIdInterfaccia(Long idInterfaccia) {
		this.idInterfaccia = idInterfaccia;
	}
	public Long getIdFunzionalita() {
		return idFunzionalita;
	}
	public void setIdFunzionalita(Long idFunzionalita) {
		this.idFunzionalita = idFunzionalita;
	}
	public Long getIdFocus() {
		return idFocus;
	}
	public void setIdFocus(Long idFocus) {
		this.idFocus = idFocus;
	}
	public Long getIdAttore() {
		return idAttore;
	}
	public void setIdAttore(Long idAttore) {
		this.idAttore = idAttore;
	}
	public Long getIdApproccio() {
		return idApproccio;
	}
	public void setIdApproccio(Long idApproccio) {
		this.idApproccio = idApproccio;
	}
	public Long getIdTipoTest() {
		return idTipoTest;
	}
	public void setIdTipoTest(Long idTipoTest) {
		this.idTipoTest = idTipoTest;
	}
	public String getUseCase() {
		return useCase;
	}
	public void setUseCase(String useCase) {
		this.useCase = useCase;
	}
	public String getProceduraTest() {
		return proceduraTest;
	}
	public void setProceduraTest(String proceduraTest) {
		this.proceduraTest = proceduraTest;
	}
	public String getControlloInterfaccia() {
		return controlloInterfaccia;
	}
	public void setControlloInterfaccia(String controlloInterfaccia) {
		this.controlloInterfaccia = controlloInterfaccia;
	}
	public String getControlloLog() {
		return controlloLog;
	}
	public void setControlloLog(String controlloLog) {
		this.controlloLog = controlloLog;
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
	public String getControlloStorage() {
		return controlloStorage;
	}
	public void setControlloStorage(String controlloStorage) {
		this.controlloStorage = controlloStorage;
	}
	public String getControlloAltro() {
		return controlloAltro;
	}
	public void setControlloAltro(String controlloAltro) {
		this.controlloAltro = controlloAltro;
	}
	
	public Boolean isInstallationLog() {
		return installationLog;
	}
	public void setInstallationLog(Boolean installationLog) {
		this.installationLog = installationLog;
	}
	public Boolean isInstallationDatabase() {
		return installationDatabase;
	}
	public void setInstallationDatabase(Boolean installationDatabase) {
		this.installationDatabase = installationDatabase;
	}
	public Boolean isInstallationPerformance() {
		return installationPerformance;
	}
	public void setInstallationPerformance(Boolean installationPerformance) {
		this.installationPerformance = installationPerformance;
	}
	public Boolean isInstallationStorage() {
		return installationStorage;
	}
	public void setInstallationStorage(Boolean installationStorage) {
		this.installationStorage = installationStorage;
	}
	public Boolean isInstallationAltro() {
		return installationAltro;
	}
	public void setInstallationAltro(Boolean installationAltro) {
		this.installationAltro = installationAltro;
	}
	public Boolean isInstallationInterfaccia() {
		return installationInterfaccia;
	}
	public void setInstallationInterfaccia(Boolean installationInterfaccia) {
		this.installationInterfaccia = installationInterfaccia;
	}
	public void setTestId(String testId) {
		this.testId = testId;
	}
	public String getTestId() {
		return testId;
	}
	
}
