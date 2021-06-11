package com.intesigroup.testcasefactory.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.intesigroup.testcasefactory.domain.Approccio;
import com.intesigroup.testcasefactory.domain.Attore;
import com.intesigroup.testcasefactory.domain.Focus;
import com.intesigroup.testcasefactory.domain.Funzionalita;
import com.intesigroup.testcasefactory.domain.Interfaccia;
import com.intesigroup.testcasefactory.domain.Progetto;
import com.intesigroup.testcasefactory.domain.TestCase;
import com.intesigroup.testcasefactory.domain.TipoTest;
import com.intesigroup.testcasefactory.service.ApproccioService;
import com.intesigroup.testcasefactory.service.AttoreService;
import com.intesigroup.testcasefactory.service.FocusService;
import com.intesigroup.testcasefactory.service.FunzionalitaService;
import com.intesigroup.testcasefactory.service.InterfacciaService;
import com.intesigroup.testcasefactory.service.ProgettoService;
import com.intesigroup.testcasefactory.service.TestCaseService;
import com.intesigroup.testcasefactory.service.TipoTestService;


@Controller
public class TestCaseController {
	@Autowired
	TestCaseService testCaseService;
	@Autowired
	ProgettoService progettoService;
	@Autowired
	InterfacciaService interfacciaService;
	@Autowired
	FunzionalitaService funzionalitaService;
	@Autowired
	FocusService focusService;
	@Autowired
	AttoreService attoreService;
	@Autowired
	ApproccioService approccioService;
	@Autowired
	TipoTestService tipoTestService;
	
	@PostMapping ("/testCase/formAggiorna")
	public String formAggiorna(@RequestParam(required=false, name="idTestCase") Long idTestCase, 
			@RequestParam(required=false) Long idProgetto, @RequestParam(required=false) Long idInterfaccia, 
			@RequestParam(required=false) Long idFunzionalita,@RequestParam(required=false)  Long idFocus,
			@RequestParam(required=false) Long idAttore, @RequestParam(required=false) Long idApproccio, 
			@RequestParam(required=false) Long idTipoTest, @RequestParam(required=false) String useCase, 
			@RequestParam(required=false) String proceduraTest, @RequestParam(required=false) String controlloInterfaccia,
			@RequestParam(required=false) String controlloLog, @RequestParam(required=false) String controlloDatabase , 
			@RequestParam(required=false) String controlloPerformance, @RequestParam(required=false) String controlloStorage,
			@RequestParam(required=false) String controlloAltro, 
			@RequestParam(required=false) boolean installationLog, @RequestParam(required=false) boolean installationDatabase , 
			@RequestParam(required=false) boolean installationPerformance, @RequestParam(required=false) boolean installationStorage,
			@RequestParam(required=false) boolean installationAltro,@RequestParam(required=false) boolean installationInterfaccia,
			@RequestParam(required=false) String testId, String action, RedirectAttributes redirAttr, Model model) {
		TestCase testCase = new TestCase();
		this.validate(idProgetto,idFunzionalita,idInterfaccia,idFocus,idAttore, idApproccio,idTipoTest,redirAttr);
		if (redirAttr.getFlashAttributes().isEmpty()) {
			if (idTestCase!=null && idTestCase!=0 && !action.equals("crea")) {
				testCase= testCaseService.findById(idTestCase).orElse(null);
				if (testCase!=null) testCase.setId(idTestCase);
				else {
					redirAttr.addFlashAttribute("testCaseInesistente", "testCaseInesistente");
					return("redirect:/testCase/visualizza");
				}
			}
			boolean flag=true;
			Progetto progetto = progettoService.getProgetto(idProgetto).orElse(null);
			if (progetto==null) {
				redirAttr.addFlashAttribute("progettoInesistente", "progettoInesistente");
				flag=false;
			}
			else testCase.setProgetto(progetto);
			Interfaccia interfaccia = interfacciaService.getInterfaccia(idInterfaccia).orElse(null);
			if (interfaccia==null && flag) {
				redirAttr.addFlashAttribute("interfacciaInesistente", "interfacciaInesistente");
				flag=false;
			}
			else testCase.setInterfaccia(interfaccia);	
			Funzionalita funzionalita = funzionalitaService.getFunzionalita(idFunzionalita).orElse(null);
			if (funzionalita==null && flag) {
				redirAttr.addFlashAttribute("funzionalitaInesistente", "funzionalitaInesistente");
					flag=false;
				}
			else testCase.setFunzionalita(funzionalita);
			Focus focus = focusService.getFocus(idFocus).orElse(null);
			if (focus==null && flag) {
				redirAttr.addFlashAttribute("focusInesistente", "focusInesistente");
				flag=false;
			}
			else testCase.setFocus(focus);
			Attore attore = attoreService.getAttore(idAttore).orElse(null);
			if (attore==null && flag) {
				redirAttr.addFlashAttribute("attoreInesistente", "attoreInesistente");
				flag=false;
			}
			else testCase.setAttore(attore);
			Approccio approccio= approccioService.findById(idApproccio).orElse(null);
			if (approccio==null && flag) {
				redirAttr.addFlashAttribute("approccioInesistente", "approccioInesistente");
				flag=false;
			}
			else testCase.setApproccio(approccio);
			TipoTest tipoTest= tipoTestService.findById(idTipoTest).orElse(null);
			if (tipoTest==null && flag) {
				redirAttr.addFlashAttribute("tipoTestInesistente", "tipoTestInesistente");
				flag=false;
			}
			else testCase.setTipoTest(tipoTest);
			testCase.setUseCase(useCase);
			testCase.setProceduraTest(proceduraTest);
			testCase.setControlloInterfaccia(controlloInterfaccia);
			testCase.setControlloLog(controlloLog);
			testCase.setControlloDatabase(controlloDatabase);
			testCase.setControlloPerformance(controlloPerformance);
			testCase.setControlloStorage(controlloStorage);
			testCase.setControlloAltro(controlloAltro);
			testCase.setInstallationTestInterfaccia(installationInterfaccia);
			testCase.setInstallationTestDatabase(installationDatabase);
			testCase.setInstallationTestPerformance(installationPerformance);
			testCase.setInstallationTestStorage(installationStorage);
			testCase.setInstallationTestAltro(installationAltro);
			testCase.setInstallationTestLog(installationLog);
			if (redirAttr.getFlashAttributes().isEmpty()) {
				long numTest=0;
				if (action.equals("crea")) {
					numTest= testCaseService.countByProgettoId(idProgetto);
					numTest++;
				}
				else numTest = testCase.getNumeroTestCase();
				testCase.setNumeroTestCase(numTest);
				testId=progetto.getCodice()+"-"+attore.getCodice()+interfaccia.getCodice()+funzionalita.getCodice()+tipoTest.getCodice()+approccio.getCodice()+"-"+numTest;
				testCase.setTestId(testId);
				TestCase newTestCase = testCaseService.save(testCase);
				if (newTestCase!=null) {
					redirAttr.addAttribute("idTestCase", newTestCase.getId());
					return ("redirect:/testCase/preload");	
				}
				else {
					redirAttr.addFlashAttribute("ErroreGernerico","ErroreGernerico");
					return ("redirect:/testCase/visualizza");
				}
			}
		}
		return "redirect:/testCase/visualizza";
	}
	
	private void validate(Long idProgetto,Long idFunzionalita, Long idInterfaccia,Long idFocus, Long idAttore, Long idApproccio, Long idTipoTest, RedirectAttributes redirAttr) {
		if (idProgetto==null || idProgetto==0) redirAttr.addFlashAttribute("progettoVuoto","progettoVuoto)");
		if (idInterfaccia==null || idInterfaccia==0) redirAttr.addFlashAttribute("interfacciaVuoto","interfacciaVuoto)");
        if (idFunzionalita==null || idFunzionalita==0) redirAttr.addFlashAttribute("funzionalitaVuoto","funzionalitaVuoto)");
	    if (idFocus==null || idFocus==0) redirAttr.addFlashAttribute("focusVuoto","focusVuoto)");
	    if (idApproccio==null || idApproccio==0) redirAttr.addFlashAttribute("approccioVuoto","approccioVuoto)");
	    if (idTipoTest==null || idTipoTest==0) redirAttr.addFlashAttribute("tipoTestVuoto","tipoTestoVuoto)");
	    if (idAttore==null || idAttore==0) redirAttr.addFlashAttribute("attoreVuoto","attoreVuoto)");
	    redirAttr.getFlashAttributes().values().stream().forEach(e->System.out.println("-----------------------"+e.toString()));
	}

	
	@GetMapping("/testCase/preload")
	public String preoladTestCase(@RequestParam(required=true) Long idTestCase, RedirectAttributes redirAttr) {
		TestCase testCase= testCaseService.findById(idTestCase).orElse(null);
		if (testCase==null) {
			redirAttr.addFlashAttribute("testCaseInesistente", "testCaseInesistente");
			return "redirect:/testCase/visualizza";
		}
		redirAttr.addAttribute("idTestCase",testCase.getId());
		redirAttr.addAttribute("idProgetto", testCase.getProgetto().getId());
		redirAttr.addAttribute("idInterfaccia", testCase.getInterfaccia().getId());
		redirAttr.addAttribute("idFunzionalita", testCase.getFunzionalita().getId());
		redirAttr.addAttribute("idFocus",testCase.getFocus().getId());
		redirAttr.addAttribute("idAttore", testCase.getAttore().getId());
		redirAttr.addAttribute("idApproccio", testCase.getApproccio().getId());
		redirAttr.addAttribute("idTestCase", testCase.getId());
		redirAttr.addAttribute("idTipoTest", testCase.getTipoTest().getId());
		redirAttr.addAttribute("useCase", testCase.getUseCase());
		redirAttr.addAttribute("proceduraTest", testCase.getProceduraTest());
		redirAttr.addAttribute("controlloInterfaccia", testCase.getControlloInterfaccia());
		redirAttr.addAttribute("controlloLog", testCase.getControlloLog());
		redirAttr.addAttribute("controlloDatabase", testCase.getControlloDatabase());
		redirAttr.addAttribute("controlloPerformance", testCase.getControlloPerformance());
		redirAttr.addAttribute("controlloStorage", testCase.getControlloStorage());
		redirAttr.addAttribute("controlloAltro", testCase.getControlloAltro());
		redirAttr.addAttribute("installationDatabase", testCase.getInstallationTestDatabase());
		redirAttr.addAttribute("installationPerformance", testCase.getInstallationTestPerformance());
		redirAttr.addAttribute("installationStorage", testCase.getInstallationTestStorage());
		redirAttr.addAttribute("installationAltro", testCase.getInstallationTestAltro());
		redirAttr.addAttribute("installationInterfaccia", testCase.getInstallationTestInterfaccia());
		redirAttr.addAttribute("installationLog", testCase.getInstallationTestLog());
		redirAttr.addAttribute("testId", testCase.getTestId());
		return "redirect:/testCase/visualizza";
	}
	
	@GetMapping("/testCase/visualizza")
	public String testCaseVisualizza(@RequestParam(required=false, name="idTestCase") Long idTestCase, 
			@RequestParam(required=false) Long idProgetto, @RequestParam(required=false) Long idInterfaccia, 
			@RequestParam(required=false) Long idFunzionalita,@RequestParam(required=false)  Long idFocus,
			@RequestParam(required=false) Long idAttore, @RequestParam(required=false) Long idApproccio, 
			@RequestParam(required=false) Long idTipoTest, @RequestParam(required=false) String useCase, 
			@RequestParam(required=false) String proceduraTest, @RequestParam(required=false) String controlloInterfaccia,
			@RequestParam(required=false) String controlloLog, @RequestParam(required=false) String controlloDatabase , 
			@RequestParam(required=false) String controlloPerformance, @RequestParam(required=false) String controlloStorage,
			@RequestParam(required=false) String controlloAltro, 
			@RequestParam(required=false) Boolean installationLog, @RequestParam(required=false) boolean installationDatabase , 
			@RequestParam(required=false) Boolean installationPerformance, @RequestParam(required=false) boolean installationStorage,
			@RequestParam(required=false) Boolean installationAltro,@RequestParam(required=false) boolean installationInterfaccia,
			@RequestParam(required=false) String testId, Model model) {
		//definisco se è un nuovo test o devo aprire uno gia esistente
		// nel caso viene viene inserito in idTestCase
		if  (idTestCase!= null) {
			TestCase testCase = testCaseService.findById(idTestCase).orElse(null);
			if (testCase== null) {
				model.addAttribute("testCaseInesistente", "testCaseInesistente");
				testId=null;
			}
		}
		
		//definisco le liste dei progetti, interfacce, funzionalita e focus 
		List<Progetto> progettoList = progettoService.findAll();
		progettoList.sort(Comparator.comparing(Progetto::getId));
		model.addAttribute("progettoList", progettoList);
		List<Interfaccia> interfacciaList= new ArrayList<Interfaccia>();
		List<Funzionalita> funzionalitaList= new ArrayList<Funzionalita>();
		List<Focus> focusList = new ArrayList<Focus>();
		List<Attore> attoreList= new ArrayList<Attore>();
		boolean flag = true;
		//valorizzo la lista delle interfacce nel caso sia valorizzato il progetto del testCase
		//il flag risultera vero fono al momento che id idProgetto, idInterfaccia, idFunzioanlita sono diversi da 0
		if (idProgetto!= null && idProgetto!=0) { 
			interfacciaList = interfacciaService.findByProgettoId(idProgetto);
			interfacciaList.sort(Comparator.comparing(Interfaccia::getId));
			attoreList=attoreService.findByProgettoId(idProgetto);
			if (interfacciaList.isEmpty()) flag=false;
		}	
		else flag= false;
		model.addAttribute("interfacciaList", interfacciaList);
		model.addAttribute("attoreList", attoreList);
		//valorizzo la lista delle funzionalita nel caso sia valorizzata l'interfaccia del testCase
		if (idFunzionalita!= null && idInterfaccia!=0 && flag)  {
			funzionalitaList = funzionalitaService.findByProgettoId(idInterfaccia);
			funzionalitaList.sort(Comparator.comparing(Funzionalita::getId));
			if (funzionalitaList.isEmpty()) flag=false;
		}
		else flag= false;
		model.addAttribute("funzionalitaList",funzionalitaList);
		//valorizzo la lista delle dei focus nel caso sia valorizzato  l'idFunzionalita
		if (idFunzionalita!=null && idFunzionalita!=0 && flag) {
			 focusList = focusService.findByFunzionalitaId(idFunzionalita);
			 focusList.sort(Comparator.comparing(Focus::getId));
		}
		else flag=false;
		model.addAttribute("focusList", focusList);
		//valorizzo la lista degli approcci
		List<Approccio> approccioList= approccioService.findAll();
		approccioList.sort(Comparator.comparing(Approccio::getId));
		model.addAttribute("approccioList", approccioList);
		//valorizzo la lista tipoTest
		List<TipoTest> tipoTestList = tipoTestService.findAll();
		tipoTestList.sort(Comparator.comparing(TipoTest::getId));
		model.addAttribute("tipoTestList", tipoTestList);
		//inserisco nel model i valori che verranno selezionati sulla vista
		model.addAttribute("idProgetto",idProgetto);
		model.addAttribute("idInterfaccia",idInterfaccia);
		model.addAttribute("idFunzionalita", idFunzionalita);
		model.addAttribute("idFocus", idFocus);
		model.addAttribute("idAttore", idAttore);
		model.addAttribute("idTestCase",idTestCase);
		model.addAttribute("idApproccio",idApproccio);
		model.addAttribute("idTipoTest", idTipoTest);
		model.addAttribute("useCase",useCase);
		model.addAttribute("proceduraTest",proceduraTest);
		model.addAttribute("controlloInterfaccia",controlloInterfaccia);
		model.addAttribute("controlloLog",controlloLog);
		model.addAttribute("controlloDatabase",controlloDatabase);
		model.addAttribute("controlloPerformance",controlloPerformance);
		model.addAttribute("controlloStorage",controlloStorage);
		model.addAttribute("controlloAltro",controlloAltro);
		model.addAttribute("installationLog",installationLog);
		model.addAttribute("installationDatabase",installationDatabase);
		model.addAttribute("installationPerformance",installationPerformance);
		model.addAttribute("installationStorage",installationStorage);
		model.addAttribute("installationAltro",installationAltro);
		model.addAttribute("installationInterfaccia",installationInterfaccia);
		model.addAttribute("testId",testId);
		return "inserimentoTestCase";
		
	}
}
