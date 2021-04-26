package com.intesigroup.testcasefactory.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.intesigroup.testcasefactory.domain.Focus;
import com.intesigroup.testcasefactory.domain.Funzionalita;
import com.intesigroup.testcasefactory.domain.Interfaccia;
import com.intesigroup.testcasefactory.service.FocusService;
import com.intesigroup.testcasefactory.service.FunzionalitaService;

@Controller
public class FocusController {
	Logger logger = LoggerFactory.getLogger(FunzionalitaController.class);
	@Autowired
	FocusService focusService;
	@Autowired
	FunzionalitaService funzionalitaService; 
	
	//Interfaccia per inserimento focus
	@GetMapping("/focus/formFocus")
	public String formFocus(@RequestParam(name="idFunzionalita") long idFunzionalita, Focus focus,Model model) {
		//da inserire eccezione nel caso non si trovino progetti legati all'interfaccia
		List<Focus> focusVis = new ArrayList<Focus>(funzionalitaService.getFunzionalita(idFunzionalita).get().getFocus());
		focusVis.sort(Comparator.comparing(Focus::getId));
		model.addAttribute("focusVis",focusVis);
		model.addAttribute("idFunzionalita", idFunzionalita);
		return "inserimentoFocus";
	}
	//EndPoint salvataggio focus
	@PostMapping("/focus/inserisci")
	public String insFocus(@ModelAttribute Focus focus,long idFunzionalita, Model model) {
		if (focus!=null) {
			Funzionalita funzionalita = funzionalitaService.getFunzionalita(idFunzionalita).get();
			funzionalita.getFocus().add(focus);
			focus.setFunzionalita(funzionalita);
			focusService.save(focus);
			List<Focus> focusVis = new ArrayList<Focus>(funzionalita.getFocus());
			model.addAttribute("focusVis",focusVis);
			model.addAttribute("idFunzionalita",idFunzionalita);
			return "redirect:/focus/formFocus?idFunzionalita="+idFunzionalita;
		}
		return "redirect:/focus/formFocus?idFunzionalita="+idFunzionalita;
	}
	// endpoint modifica focus
	@PostMapping("/focus/modifica")
	public String modificaFocus(long idFocus, Focus focus) {
		Focus focusOld = focusService.getFocus(idFocus).get();
		focusOld.setCodice(focus.getCodice());
		focusOld.setDescrizione(focus.getDescrizione());
		focusOld.setNome(focus.getNome());
		focusService.save(focusOld);
		return "redirect:/focus/formFocus?idFunzionalita="+focusOld.getFunzionalita().getId(); 	
	}
	//endpoint cancellazione focus
	@PostMapping ("/focus/cancella")
	public String cancellazioneFocus(long idFocus,long idFunzionalita) {
		  Focus focus= focusService.getFocus(idFocus).orElse(null);
	        if (focus!=null) {
	        	focusService.deleteById(idFocus);
	        }
	        // qui ci deve essere l'errore nel caso si cerca di cancellare la funzionalita gia cancellata
	        return "redirect:/focus/formFocus?idFunzionalita="+ idFunzionalita;
	}
	
	
	@GetMapping("/focus/visualizza")
	public String visualizzaFocus(Model model) {
		List<Focus> focus = focusService.findAll();
		model.addAttribute("focus",focus);
		return "visualizzaFocus";
	}
	@PostMapping("/focus/inserimentoFocus")
	public String aggiungiFocus(Focus focus) {
		focusService.save(focus);
		return "inserimentoFocus";
	}
	@GetMapping("/focus/visualizzaById")
	public String cercaFocus(@RequestParam(name="id") long id,Model model) {
		Focus focus=focusService.getFocus(id).get();
		model.addAttribute("focus", focus);
		return "visualizzaFocus";
	}
}
