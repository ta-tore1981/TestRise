package com.intesigroup.testcasefactory.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.intesigroup.testcasefactory.domain.Funzionalita;
import com.intesigroup.testcasefactory.domain.Interfaccia;
import com.intesigroup.testcasefactory.entityView.FunzionalitaViewCRUDForm;
import com.intesigroup.testcasefactory.service.FunzionalitaService;
import com.intesigroup.testcasefactory.service.InterfacciaService;
import com.intesigroup.testcasefactory.service.ProgettoService;

@Controller
public class FunzionalitaController {
	@Autowired 
	FunzionalitaService funzionalitaService;
	@Autowired
	InterfacciaService interfacciaService;
	
	Logger logger = LoggerFactory.getLogger(FunzionalitaController.class);
	

	@PostMapping("/funzionalita/formFunzionalita")
	public String insFunzionalita(@ModelAttribute FunzionalitaViewCRUDForm form,String action, Model model) {
		Funzionalita funzionalita= new Funzionalita();
		if (action.equals("Inserisci")){
			funzionalita.setId(0);
			funzionalita.setInterfaccia(interfacciaService.getInterfaccia(form.getIdInterfaccia()).orElse(null));
			funzionalita.setDescrizione(form.getDescrizione());
			funzionalita.setCodice(form.getCodice());		
			funzionalita.setNome(form.getNome());
			funzionalitaService.save(funzionalita);
			return("redirect:/funzionalita/visualizza?idInterfaccia="+form.getIdInterfaccia());
		}
		if (action.equals("Modifica")){
			funzionalita= funzionalitaService.getFunzionalita(form.getId()).orElse(null);
			funzionalita.setDescrizione(form.getDescrizione());
			funzionalita.setCodice(form.getCodice());		
			funzionalita.setNome(form.getNome());
			funzionalitaService.save(funzionalita);
			return("redirect:/funzionalita/visualizza?idSelected="+form.getId()+"&idInterfaccia="+form.getIdInterfaccia());
		}
		if (action.equals("Cancella")){
			funzionalitaService.delete(form.getId());
			return("redirect:/funzionalita/visualizza?idInterfaccia="+form.getIdInterfaccia());
		}
		if (action.equals("Focus")) {
			return("redirect:/focus/visualizza?idFunzionalita="+form.getId());
		}
		if (action.equals("Interfaccia")) {
			Long idProgetto = interfacciaService.getInterfaccia(form.getIdInterfaccia()).get().getProgetto().getId(); 
			return("redirect:/interfaccia/visualizza?idProgetto="+idProgetto);
		}
		return("redirect:/funzionalita/visualizza?idInterfaccia="+form.getIdInterfaccia());
	}
	
	@GetMapping("/funzionalita/visualizza")
	public String  projectView1(@RequestParam(required=false, name="idSelected") Long idSelected, @RequestParam(required=true, name="idInterfaccia") Long idInterfaccia, Model model) {
		FunzionalitaViewCRUDForm form= new FunzionalitaViewCRUDForm();
		List<Funzionalita> funzionalitaList;
		Funzionalita funzionalita;
		Interfaccia interfaccia = interfacciaService.getInterfaccia(idInterfaccia).orElse(null);
		if (interfaccia!=null) {
			funzionalitaList = new ArrayList<Funzionalita>(interfaccia.getFunzionalita());
			funzionalitaList.sort(Comparator.comparing(Funzionalita::getId));
			//valorizzo i valori di output con la lista delle interfacce
			if (idSelected!=null) {
				funzionalita = funzionalitaList.stream().filter(u->(u.getId()==idSelected)).findAny().orElse(null);
				//nel caso la funzionalita non esite imposta l'ID di output uguale a null per creare una nuova funzionalita
				if (funzionalita==null) {
					form.setId(Long.valueOf(0));
					form.setIdInterfaccia(idInterfaccia);
				}
				else {
					form.setCodice(funzionalita.getCodice());
					form.setDescrizione(funzionalita.getDescrizione());
					form.setNome(funzionalita.getNome());
					form.setId(funzionalita.getId());
					form.setIdInterfaccia(idInterfaccia);
					
	      		}
			}
			else {
				form.setIdInterfaccia(idInterfaccia);
				form.setId(Long.valueOf(0));
			}
		}
		else return "redirect:/erroPage?errorStr=l'funzionalita selezionata non ha associato alcun interfaccia";
		model.addAttribute("form",form);
		model.addAttribute("funzionalitaList",funzionalitaList);
		return "inserimentoFunzionalita";
	}
}
