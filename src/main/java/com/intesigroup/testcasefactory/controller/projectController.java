package com.intesigroup.testcasefactory.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.intesigroup.testcasefactory.domain.Progetto;
import com.intesigroup.testcasefactory.service.ProgettoService;

@Controller
@RequestMapping("/")
public class projectController {
	@Autowired
	ProgettoService progettoService;
	
	Logger logger = LoggerFactory.getLogger(projectController.class);
	@GetMapping("/projectNew/view")
	public String  projectView(@RequestParam(required=false, name="idProgetto") Long idProgetto, Progetto progetto, Model model) {
		List<Progetto> listaProgetto = progettoService.findAll();
		if (idProgetto!=null)  progetto = progettoService.getProgetto(idProgetto).orElse(new Progetto());
		else progetto= new Progetto();
		model.addAttribute("progetto",progetto);
		model.addAttribute("listaProgetto",listaProgetto);
		return "projectView";
	}
	@PostMapping("/projectNew/insert")
	public String insProgetti(@ModelAttribute Progetto progetto,String action, Model model) {
		if (action.equals("Inserisci_progetto")){
			if (progetto!=null) {
				progetto.setId(0);
				progettoService.save(progetto);
				model.addAttribute("progetto", progetto);
				return("redirect:/projectNew/view");
			}
			return("redirect:projectNew/view");
		}
		else if (action.equals("Modifica_progetto")) {
			Progetto progettoOld = progettoService.getProgetto(progetto.getId()).orElse(null);
			progettoOld.setCodice(progetto.getCodice());
			progettoOld.setNome(progetto.getNome());
			progettoOld.setDescrizione(progetto.getDescrizione());
			progettoService.save(progettoOld);
			return("redirect:/projectNew/view");
		}
		else if (action.equals("Cancella_progetto")) {
			progettoService.delete(progetto.getId());
			return("redirect:/projectNew/view");
		}
		return("redirect:projectNew/view");
	}

}
