package com.intesigroup.testcasefactory.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.intesigroup.testcasefactory.domain.Focus;
import com.intesigroup.testcasefactory.domain.Funzionalita;
import com.intesigroup.testcasefactory.domain.Interfaccia;
import com.intesigroup.testcasefactory.domain.Progetto;
import com.intesigroup.testcasefactory.domain.TestCase;
import com.intesigroup.testcasefactory.form.RicercaTestCaseView;
import com.intesigroup.testcasefactory.service.FocusService;
import com.intesigroup.testcasefactory.service.FunzionalitaService;
import com.intesigroup.testcasefactory.service.InterfacciaService;
import com.intesigroup.testcasefactory.service.ProgettoService;
import com.intesigroup.testcasefactory.service.TestCaseService;


@Controller
public class RicercaTestCaseController {
	@Autowired 
	ProgettoService progettoService;
	@Autowired
	InterfacciaService interfacciaService;
	@Autowired 
	FunzionalitaService funzionalitaService;
	@Autowired
	FocusService focusService;
	@Autowired
	TestCaseService testCaseService;
	
	@PostMapping("/testCase/cancella")
	public String cancellaTestCase(@RequestParam Long idTestCase,Long idProgetto,@RequestParam(required=false) Long idInterfaccia, @RequestParam(required=false) Long idFunzionalita, @RequestParam(required=false) Long idFocus, String tipoAzione,RedirectAttributes redirAtt) {
		if (idTestCase!=null){
			TestCase testCase= testCaseService.findById(idTestCase).orElse(null);
			if (testCase!=null) testCaseService.deleteById((long)idTestCase);
		}
		return("redirect:/testCase/ricerca?tipoAzione=cerca&idProgetto="+idProgetto+"&idInterfaccia="+idInterfaccia+"&idFunzionalita="+idFunzionalita+"&focus="+idFocus);
	}
	
	@GetMapping("/testCase/ricerca")
	public String ricercaTestCase(@RequestParam(required=false) Long idProgetto,@RequestParam(required=false) Long idInterfaccia, @RequestParam(required=false) Long idFunzionalita, @RequestParam(required=false) Long idFocus, String tipoAzione, @RequestParam(required=false) Integer page, Model model) {
		List<Interfaccia> interfacciaList = new ArrayList<Interfaccia>();
		List<Funzionalita> funzionalitaList = new ArrayList<Funzionalita>();
		List<Focus> focusList = new ArrayList<Focus>();
		List<TestCase> testCaseList = new ArrayList<TestCase>();
		boolean nextPage=false;
		if ((tipoAzione!=null && tipoAzione.equals("progetto"))) {
			if (idProgetto!=null){
					interfacciaList=new ArrayList<Interfaccia>(interfacciaService.findByProgettoId(idProgetto));
					idInterfaccia=(long) 0; idFunzionalita=(long) 0; idFunzionalita=(long) 0; idFocus=(long) 0;
					
			}
		}
		//quando viene cambiata la combo box interfaccia
		if (tipoAzione!=null && tipoAzione.equals("interfaccia")) {
			if (idInterfaccia!=null) {
				interfacciaList=new ArrayList<Interfaccia>(interfacciaService.findByProgettoId(idProgetto));
				if (idInterfaccia!=0) funzionalitaList= new ArrayList<Funzionalita>(funzionalitaService.findByInterfacciaId(idInterfaccia));
				idFunzionalita=(long) 0; idFocus=(long) 0;
				
			}
			
		}
		//quando viene cambiata la combo box funzionalita
		if (tipoAzione!=null && tipoAzione.equals("funzionalita")) {
			if (idFunzionalita!=null) {
				interfacciaList=new ArrayList<Interfaccia>(interfacciaService.findByProgettoId(idProgetto));
				funzionalitaList= new ArrayList<Funzionalita>(funzionalitaService.findByInterfacciaId(idInterfaccia));
				focusList = new ArrayList<Focus>(focusService.findByFunzionalitaId(idFunzionalita));
				idFocus=(long) 0;
			}
		}
		//quando viene premuto il bottone cerca
		if (tipoAzione!=null && tipoAzione.equals("cerca")) {
			Slice<TestCase> slice = testCaseService.getPagingTestCase(idProgetto, idInterfaccia, idFunzionalita, idFocus,0,20);
			testCaseList= slice.getContent();
			if (slice.hasNext()) nextPage=true;
			
			if (idProgetto!=null) interfacciaList=interfacciaService.findByProgettoId(idProgetto);
			if (!interfacciaList.isEmpty()) funzionalitaList=funzionalitaService.findByInterfacciaId(idInterfaccia);
			if (!funzionalitaList.isEmpty()) focusList=focusService.findByFunzionalitaId(idFunzionalita);
		}
		if (tipoAzione!=null && tipoAzione.equals("paginaSuccessiva")) {
			
		}
	/*	else {
			if (idProgetto!=null) interfacciaList=interfacciaService.findByProgettoId(idProgetto);
			if (!interfacciaList.isEmpty()) funzionalitaList=funzionalitaService.findByInterfacciaId(idInterfaccia);
			if (!funzionalitaList.isEmpty()) focusList=focusService.findByFunzionalitaId(idFunzionalita);
		}*/
		model.addAttribute("testCaseList", testCaseList);
		model.addAttribute("progettoList",getProgettoList());
		model.addAttribute("interfacciaList",interfacciaList);
		model.addAttribute("funzionalitaList", funzionalitaList);
		model.addAttribute("focusList",focusList);
		model.addAttribute("idProgetto", idProgetto);
		model.addAttribute("idInterfaccia", idInterfaccia);
		model.addAttribute("idFunzionalita", idFunzionalita);
		model.addAttribute("idFocus", idFocus);
		model.addAttribute("nextPage",nextPage);
		return "ricercaTestCase";
	}
	@ModelAttribute("progettoList")
	public List<Progetto> getProgettoList() {
		return progettoService.findAll();
	}
}
