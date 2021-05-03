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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.intesigroup.testcasefactory.domain.Progetto;
import com.intesigroup.testcasefactory.entityView.ProgettoViewCRUDForm;
import com.intesigroup.testcasefactory.service.ProgettoService;

@Controller
@RequestMapping("/")
public class ProgettoController {
	@Autowired
	ProgettoService progettoService;
	
	Logger logger = LoggerFactory.getLogger(ProgettoController.class);
	

	@PostMapping("/progetto/formProgetto")
	public String insProgetti(@ModelAttribute ProgettoViewCRUDForm form,String action, Model model) {
		Progetto progetto= new Progetto();
		if (action.equals("Inserisci")){
			progetto.setId(0);
			progetto.setDescrizione(form.getDescrizione());
			progetto.setCodice(form.getCodice());		
			progetto.setNome(form.getNome());
			progettoService.save(progetto);
			return("redirect:/progetto/visualizza");
		}
		else if (action.equals("Modifica")){
			progetto= progettoService.getProgetto(form.getId()).orElse(null);
			progetto.setDescrizione(form.getDescrizione());
			progetto.setCodice(form.getCodice());		
			progetto.setNome(form.getNome());
			progettoService.save(progetto);
			return("redirect:/progetto/visualizza?idSelected="+progetto.getId());
		}
		else if (action.equals("Cancella")){
			progettoService.delete(form.getId());
			return("redirect:/progetto/visualizza");
		}
		else if (action.equals("Interfaccia")) {
			return("redirect:/interfaccia/visualizza?idProgetto="+form.getId());
		}
		else return("redirect:/progetto/visualizza");
	}

		
	
	@GetMapping("/progetto/visualizza")
	public String  projectView1(@RequestParam(required=false, name="idSelected") Long idSelected,Model model) {
		ProgettoViewCRUDForm form= new ProgettoViewCRUDForm();
		List<Progetto> progettoList = progettoService.findAll();
		//valorizzo i valori di output con la lista dei progetti
		progettoList.sort(Comparator.comparing(Progetto::getId));
		if (idSelected!=null) {
			Progetto progetto=progettoList.stream().filter(u->u.getId()==idSelected).findAny().orElse(null);
			//nel caso il non esiste imposta l'ID di output uguale a null per creare una nuova funzionalita
			if (progetto==null) {
				form.setId(Long.valueOf(0));
			}
			else {
				form.setId(progetto.getId());
				form.setCodice(progetto.getCodice());
				form.setDescrizione(progetto.getDescrizione());
				form.setNome(progetto.getNome());
				form.setId(progetto.getId());
			}
		}
		else form.setId(Long.valueOf(0));
		model.addAttribute("form",form);
		model.addAttribute("progettoList",progettoList);
		return "inserimentoProgetto";
	}
}
