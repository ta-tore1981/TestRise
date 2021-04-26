package com.intesigroup.testcasefactory.controller;

import java.util.ArrayList;
import java.util.Collections;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.intesigroup.testcasefactory.domain.Funzionalita;
import com.intesigroup.testcasefactory.domain.Interfaccia;
import com.intesigroup.testcasefactory.domain.Progetto;
import com.intesigroup.testcasefactory.service.FunzionalitaService;
import com.intesigroup.testcasefactory.service.InterfacciaService;
import com.intesigroup.testcasefactory.service.InterfacciaServiceImpl;
import com.intesigroup.testcasefactory.service.ProgettoService;

@Controller
@RequestMapping("/")
public class InterfacciaController {
	Logger logger = LoggerFactory.getLogger(InterfacciaController.class);
	@Autowired
	InterfacciaService interfacciaService;
	
	@Autowired
	FunzionalitaService funzionalitaService;
	
	@Autowired 
	ProgettoService progettoService;
	
	//Interfaccia per inserimento interfaccia
	@GetMapping("/interfaccia/formInterfaccia")
	public String formInterfaccia(@RequestParam(name="idProgetto") long idProgetto, Interfaccia interfaccia,Model model) {
		//da inserire eccezione nel caso non si trovino progetti legati all'interfaccia
		List<Interfaccia> interfacciaVis = new ArrayList<Interfaccia>(progettoService.getProgetto(idProgetto).get().getInterfaccia());
		interfacciaVis.sort(Comparator.comparing(Interfaccia::getId));
		model.addAttribute("interfacciaVis",interfacciaVis);
		model.addAttribute("idProgetto", idProgetto);
		return "inserimentoInterfaccia";
	}
	
	//EndPoint salvataggio interfaccia
	@PostMapping("/interfaccia/inserisci")
	public String insInterfaccia(@ModelAttribute Interfaccia interfaccia,long idProgetto, Model model) {
		if (interfaccia!=null) {
			Progetto progetto = progettoService.getProgetto(idProgetto).get();
			progetto.getInterfaccia().add(interfaccia);
			interfaccia.setProgetto(progetto);
			interfacciaService.save(interfaccia);
			logger.info("il codice del progetto padre è + "+ interfaccia.getProgetto().getCodice());
			List<Interfaccia> interfacciaVis = new ArrayList<Interfaccia>(progetto.getInterfaccia());
			model.addAttribute("interfacciaVis",interfacciaVis);
			model.addAttribute("idProgetto",idProgetto);
			return "redirect:/interfaccia/formInterfaccia?idProgetto="+idProgetto;
		}
		return "redirect:/interfaccia/formInterfaccia?idProgetto="+idProgetto;
	}
	// endpoint modifica interfaccia
	@PostMapping("/interfaccia/modifica")
	public String modificaInterfaccia(long idInterfaccia, Interfaccia interfaccia) {
		Interfaccia interfacciaOld = interfacciaService.getInterfaccia(idInterfaccia).get();
		interfacciaOld.setCodice(interfaccia.getCodice());
		interfacciaOld.setDescrizione(interfaccia.getDescrizione());
		interfacciaOld.setNome(interfaccia.getNome());
		interfacciaService.save(interfacciaOld);
		return "redirect:/interfaccia/formInterfaccia?idProgetto="+interfacciaOld.getProgetto().getId(); 
		
	}
	//endpoint cancellazione interfaccia
	@PostMapping ("/interfaccia/cancella")
	public String cancellazioneInterfaccia(long idInterfaccia,long idProgetto) {
        Interfaccia interfaccia= interfacciaService.getInterfaccia(idInterfaccia).orElse(null);
        if (interfaccia!=null) {
        	interfacciaService.deleteById(idInterfaccia);
        }
        // qui ci deve essere l'errore nel caso si cerca di cancellare un'interfaccia gia cancellata
        return "redirect:/interfaccia/formInterfaccia?idProgetto="+ idProgetto;
        
     }
	@GetMapping("/interfaccia/visualizza")
	public String visualizzaInterfacce(Model model) {
		List<Interfaccia> interfaccia = interfacciaService.findAll();
		model.addAttribute("interfaccia",interfaccia);
		return "visualizzaInterfacce";
	}
	@GetMapping("/interfaccia/visualizzaById")
	public String cercaInterfaccia(@RequestParam(name="id") long id,Model model) {
		logger.info("Entrato in visualizza");
		Interfaccia interfaccia=interfacciaService.getInterfaccia(id).get();
		logger.info(interfaccia.getNome());
		model.addAttribute("interfaccia", interfaccia);
		return "visualizzaInterfacce";
	}
	@GetMapping("/interfaccia/visualizzaFunzionalita")
	public String visualizzaFunzionalita(@RequestParam(name="id") long id,Model model) {
		logger.info("Entrato in visualizzaFunzionalita");
		List<Funzionalita> funzionalita= new ArrayList<Funzionalita>();
		Interfaccia interfaccia = interfacciaService.getInterfaccia(id).get();
		if (interfaccia.getFunzionalita()!=null) {
			funzionalita = new ArrayList<>(interfaccia.getFunzionalita());
			
		}
		model.addAttribute("funzionalita",funzionalita);
		return "visualizzaFunzionalita";
	}
	

}
