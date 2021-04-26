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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.intesigroup.testcasefactory.domain.Focus;
import com.intesigroup.testcasefactory.domain.Funzionalita;
import com.intesigroup.testcasefactory.domain.Interfaccia;
import com.intesigroup.testcasefactory.domain.Progetto;
import com.intesigroup.testcasefactory.service.FocusService;
import com.intesigroup.testcasefactory.service.FunzionalitaService;
import com.intesigroup.testcasefactory.service.InterfacciaService;

@Controller
public class FunzionalitaController {
	Logger logger = LoggerFactory.getLogger(FunzionalitaController.class);
	@Autowired
	FunzionalitaService funzionalitaService;
	@Autowired
	FocusService focusService;
	@Autowired
	InterfacciaService interfacciaService;
	
	//Interfaccia per inserimento funzionalita
	@GetMapping("/funzionalita/formFunzionalita")
	public String formInterfaccia(@RequestParam(name="idInterfaccia") long idInterfaccia, Funzionalita funzionalita,Model model) {
		//da inserire eccezione nel caso non si trovino progetti legati all'interfaccia
		List<Funzionalita> funzionalitaVis = new ArrayList<Funzionalita>(interfacciaService.getInterfaccia(idInterfaccia).get().getFunzionalita());
		funzionalitaVis.sort(Comparator.comparing(Funzionalita::getId));
		model.addAttribute("funzionalitaVis",funzionalitaVis);
		model.addAttribute("idInterfaccia", idInterfaccia);
		return "inserimentoFunzionalita";
	}
	//EndPoint salvataggio funzionalita
	@PostMapping("/funzionalita/inserisci")
	public String insFunzionalita(@ModelAttribute Funzionalita funzionalita,long idInterfaccia, Model model) {
		if (funzionalita!=null) {
			Interfaccia interfaccia = interfacciaService.getInterfaccia(idInterfaccia).get();
			interfaccia.getFunzionalita().add(funzionalita);
			funzionalita.setInterfaccia(interfaccia);
			funzionalitaService.save(funzionalita);
			List<Funzionalita> funzionalitaVis = new ArrayList<Funzionalita>(interfaccia.getFunzionalita());
			model.addAttribute("interfacciaVis",funzionalitaVis);
			model.addAttribute("idProgetto",idInterfaccia);
			return "redirect:/funzionalita/formFunzionalita?idInterfaccia="+idInterfaccia;
		}
		return "redirect:/funzionalita/formFunzionalita?idInterfaccia="+idInterfaccia;
	}
	
	// endpoint modifica funzionalita
		@PostMapping("/funzionalita/modifica")
		public String modificaFunzionalita(long idFunzionalita, Funzionalita funzionalita) {
			Funzionalita funzionalitaOld = funzionalitaService.getFunzionalita(idFunzionalita).get();
			funzionalitaOld.setCodice(funzionalita.getCodice());
			funzionalitaOld.setDescrizione(funzionalita.getDescrizione());
			funzionalitaOld.setNome(funzionalita.getNome());
			funzionalitaService.save(funzionalitaOld);
			return "redirect:/funzionalita/formFunzionalita?idProgetto="+funzionalitaOld.getInterfaccia().getId(); 
			
		}
	
	//endpoint cancellazione funzionalita
	@PostMapping ("/funzionalita/cancella")
	public String cancellazioneFunzionalita(long idFunzionalita,long idInterfaccia) {
	       Funzionalita funzionalita= funzionalitaService.getFunzionalita(idFunzionalita).orElse(null);
	        if (funzionalita!=null) {
	        	funzionalitaService.deleteById(idFunzionalita);
	        }
	        // qui ci deve essere l'errore nel caso si cerca di cancellare la funzionalita gia cancellata
	        return "redirect:/funzionalita/formFunzionalita?idInterfaccia="+ idInterfaccia;
	}
	
	
	@GetMapping("/funzionalita/visualizza")
	public String visualizzaFunzionalita(Model model) {
		List<Funzionalita> funzionalita = funzionalitaService.findAll();
		model.addAttribute("funzionalita",funzionalita);
		return "visualizzaFunzionalita";
	}
	@PostMapping("/funzionalita/inserimentoFunzionalita")
	public String aggiungiFunzionalita(Funzionalita funzionalita) {
		funzionalitaService.save(funzionalita);
		return "inserimentoFunzionalita";
	}
	@GetMapping("/funzionalita/visualizzaById")
	public String cercaFunzionalita(@RequestParam(name="id") long id,Model model) {
		Funzionalita funzionalita=funzionalitaService.getFunzionalita(id).get();
		model.addAttribute("funzionalita", funzionalita);
		return "visualizzaFunzionalita";
	}
	@GetMapping("/funzionalita/visualizzaFocus")
	public String visualizzaFocus(@RequestParam(name="id") long id,Model model) {
		List<Focus> focus= new ArrayList<Focus>();
		Funzionalita funzionalita = funzionalitaService.getFunzionalita(id).get();
		if (funzionalita.getFocus()!=null) {
			focus = new ArrayList<>(funzionalita.getFocus());
			logger.info("sono entrato");
		}
		model.addAttribute("funzionalita",focus);
		return "visualizzaFocus";
	}


}
