package com.intesigroup.testcasefactory.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

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

import com.intesigroup.testcasefactory.domain.Interfaccia;
import com.intesigroup.testcasefactory.domain.Progetto;
import com.intesigroup.testcasefactory.service.InterfacciaService;
import com.intesigroup.testcasefactory.service.ProgettoService;

@Controller
@RequestMapping("/")
public class ProgettoController {
	Logger logger = LoggerFactory.getLogger(ProgettoController.class);
	@Autowired
	ProgettoService progettoService;
	
	@Autowired
	InterfacciaService interfacciaService;

	@GetMapping("/progetto/formProgetto")
	public String formProgetto(Progetto progetto,Model model) {
		//da inserire eccezione nel caso non si trovino progetti legati all'progetto
		List<Progetto> progettoVis = progettoService.findAll();
		progettoVis.sort(Comparator.comparing(Progetto::getId));
		model.addAttribute("progettoVis",progettoVis);
		return "inserimentoProgetto";
	}
	@PostMapping("/progetto/inserisci")
	public String insProgetti(@ModelAttribute Progetto progetto,Model model) {
		if (progetto!=null) {	
			progettoService.save(progetto);
			model.addAttribute("progetto", progetto);
			return("redirect:/progetto/formProgetto");
		}
		return("redirect:/progetto/formProgetto");
	}
	@PostMapping ("/progetto/modifica")
	public String modificaProgetto(long idProgetto,Progetto progetto) {
		Progetto progettoOld = progettoService.getProgetto(idProgetto).get();
		progettoOld.setCodice(progetto.getCodice());
		progettoOld.setDescrizione(progetto.getDescrizione());
		return("redirect:/progetto/formProgetto");
	}
	@PostMapping ("/progetto/cancella")
	public String  cancellazioneProgetto(@RequestParam(name="idProgetto") long idProgetto) {
		if (progettoService.getProgetto(idProgetto).isPresent()) progettoService.delete(idProgetto);
		return("redirect:/progetto/formProgetto");
	}
	@GetMapping("/progetto/visualizzaById")
	public String cercaProgetto(@RequestParam(name="id")long id, Model model) {
		Progetto progetto=progettoService.getProgetto(id).get();
		model.addAttribute("progetto", progetto);
		return "visualizzaProgetti";
	}
	@GetMapping("/progetto/visualizzaInterfacce")
	public String visualizzaIntrefacce(@RequestParam(name="id") long id,Model model) {
		List<Interfaccia> interfaccia= new ArrayList<Interfaccia>();
		Progetto progetto = progettoService.getProgetto(id).get();
		if (progetto.getInterfaccia()!=null) {
			interfaccia = new ArrayList<>(progetto.getInterfaccia());
			logger.info("sono entrato");
			interfaccia.forEach((u)->logger.info(u.getCodice()));
			
		}
		model.addAttribute("interfaccia",interfaccia);
		return "visualizzaInterfacce";
	}
}
