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
import com.intesigroup.testcasefactory.domain.Interfaccia;
import com.intesigroup.testcasefactory.domain.Progetto;
import com.intesigroup.testcasefactory.entityView.InterfacciaViewCRUDForm;
import com.intesigroup.testcasefactory.service.InterfacciaService;
import com.intesigroup.testcasefactory.service.ProgettoService;

@Controller
public class InterfacciaController {
	@Autowired 
	InterfacciaService interfacciaService;
	@Autowired
	ProgettoService progettoService;
	
	Logger logger = LoggerFactory.getLogger(InterfacciaController.class);
	

	@PostMapping("/interfaccia/formInterfaccia")
	public String insInterfaccia(@ModelAttribute InterfacciaViewCRUDForm form,String action, Model model) {
		Interfaccia interfaccia= new Interfaccia();
		if (action.equals("Inserisci")){
			interfaccia.setId(0);
			interfaccia.setProgetto(progettoService.getProgetto(form.getIdProgetto()).orElse(null));
			interfaccia.setDescrizione(form.getDescrizione());
			interfaccia.setCodice(form.getCodice());		
			interfaccia.setNome(form.getNome());
			interfacciaService.save(interfaccia);
			return("redirect:/interfaccia/visualizza?idProgetto="+form.getIdProgetto());
		}
		if (action.equals("Modifica")){
			interfaccia= interfacciaService.getInterfaccia(form.getId()).orElse(null);
			interfaccia.setDescrizione(form.getDescrizione());
			interfaccia.setCodice(form.getCodice());		
			interfaccia.setNome(form.getNome());
			interfacciaService.save(interfaccia);
			return("redirect:/interfaccia/visualizza?idSelected="+form.getId()+"&idProgetto="+form.getIdProgetto());
		}
		if (action.equals("Cancella")){
			interfacciaService.deleteById(form.getId());
			return("redirect:/interfaccia/visualizza?idProgetto="+form.getIdProgetto());
		}
		if (action.equals("Funzionalita")) {
			return("redirect:/funzionalita/visualizza?idInterfaccia="+form.getId());
		}
		if (action.equals("Progetto")) {
			return("redirect:/progetto/visualizza?idProgetto="+form.getIdProgetto());
		}
		return("redirect:interfaccia/visualizza?idProgetto="+form.getIdProgetto());
	}
	
	@GetMapping("/interfaccia/visualizza")
	public String  projectView1(@RequestParam(required=false, name="idSelected") Long idSelected, @RequestParam(required=true, name="idProgetto") Long idProgetto, Model model) {
		InterfacciaViewCRUDForm form= new InterfacciaViewCRUDForm();
		Interfaccia interfaccia;
		List<Interfaccia> interfacciaList;
		Progetto progetto = progettoService.getProgetto(idProgetto).orElse(null);
		if (progetto!=null) {
			interfacciaList = new ArrayList<Interfaccia>(progetto.getInterfaccia());
			interfacciaList.sort(Comparator.comparing(Interfaccia::getId));
			//valorizzo i valori di output con la lista delle interfacce
			if (idSelected!=null) {
				interfaccia = interfacciaList.stream().filter(u->(u.getId()==idSelected)).findAny().orElse(null);
				//nel caso la funzionalita non esite imposta l'ID di output uguale a null per creare una nuova funzionalita
				if (interfaccia==null) {
					form.setId(Long.valueOf(0));
					form.setIdProgetto(idProgetto);
				}
				else {
					form.setCodice(interfaccia.getCodice());
					form.setDescrizione(interfaccia.getDescrizione());
					form.setNome(interfaccia.getNome());
					form.setId(interfaccia.getId());
					form.setIdProgetto(interfaccia.getProgetto().getId());
					
	      		}
			}
			else {
				form.setId(Long.valueOf(0));
				form.setIdProgetto(idProgetto);
			}
		}
		else return "redirect:/erroPage?errorStr=l'interfaccia selezionata non ha associato alcun progetto";
		model.addAttribute("form",form);
		model.addAttribute("interfacciaList",interfacciaList);
		return "inserimentoInterfaccia";
	}
}
