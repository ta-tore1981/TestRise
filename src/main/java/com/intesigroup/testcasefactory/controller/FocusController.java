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

import com.intesigroup.testcasefactory.domain.Focus;
import com.intesigroup.testcasefactory.domain.Funzionalita;
import com.intesigroup.testcasefactory.entityView.FocusViewCRUDForm;
import com.intesigroup.testcasefactory.service.FocusService;
import com.intesigroup.testcasefactory.service.FunzionalitaService;

@Controller
public class FocusController {
	@Autowired 
	FocusService focusService;
	@Autowired
	FunzionalitaService funzionalitaService;
	
	Logger logger = LoggerFactory.getLogger(FocusController.class);
	

	@PostMapping("/focus/formFocus")
	public String insFocus(@ModelAttribute FocusViewCRUDForm form,String action, Model model) {
		Focus focus= new Focus();
		if (action.equals("Inserisci")){
			focus.setId(0);
			focus.setFunzionalita(funzionalitaService.getFunzionalita(form.getIdFunzionalita()).orElse(null));
			focus.setDescrizione(form.getDescrizione());
			focus.setCodice(form.getCodice());		
			focus.setNome(form.getNome());
			focusService.save(focus);
			return("redirect:/focus/visualizza?idFunzionalita="+form.getIdFunzionalita());
		}
		if (action.equals("Modifica")){
			focus= focusService.getFocus(form.getId()).orElse(null);
			focus.setDescrizione(form.getDescrizione());
			focus.setCodice(form.getCodice());		
			focus.setNome(form.getNome());
			focusService.save(focus);
			return("redirect:/focus/visualizza?idSelected="+form.getId()+"&idFunzionalita="+form.getIdFunzionalita());
		}
		if (action.equals("Funzionalita")) {
			Long idInterfaccia=funzionalitaService.getFunzionalita(form.getIdFunzionalita()).get().getInterfaccia().getId();
			return("redirect:/funzionalita/visualizza?idInterfaccia="+idInterfaccia);
		}
		if (action.equals("Cancella")){
			focusService.deleteById(form.getId());
			return("redirect:/focus/visualizza?idFunzionalita="+form.getIdFunzionalita());
		}
		return("redirect:focus/visualizza?idFunzionalita="+form.getIdFunzionalita());
	}
	
	@GetMapping("/focus/visualizza")
	public String  projectView1(@RequestParam(required=false, name="idSelected") Long idSelected, @RequestParam(required=true, name="idFunzionalita") Long idFunzionalita, Model model) {
		FocusViewCRUDForm form= new FocusViewCRUDForm();
		Focus focus;
		List<Focus> focusList;
		Funzionalita funzionalita = funzionalitaService.getFunzionalita(idFunzionalita).orElse(null);
		if (funzionalita!=null) {
			form.setId(idFunzionalita);
			focusList = new ArrayList<Focus>(funzionalita.getFocus());
			focusList.sort(Comparator.comparing(Focus::getId));
			//valorizzo i valori di output con la lista delle interfacce
			if (idSelected!=null) {
				focus = focusList.stream().filter(u->(u.getId()==idSelected)).findAny().orElse(null);
				//nel caso la focus non esite imposta l'ID di output uguale a null per creare una nuova focus
				if (focus==null) {
					form.setId(Long.valueOf(0));
					form.setIdFunzionalita(idFunzionalita);
				}
				else {
					form.setCodice(focus.getCodice());
					form.setDescrizione(focus.getDescrizione());
					form.setNome(focus.getNome());
					form.setId(focus.getId());
					form.setIdFunzionalita(idFunzionalita);
					
	      		}
			}
			else {
				form.setId(Long.valueOf(0));
				form.setIdFunzionalita(idFunzionalita);
			}
		}
		else return "redirect:/erroPage?errorStr=l'focus selezionata non ha associato alcun funzionalita";
		model.addAttribute("form",form);
		model.addAttribute("focusList",focusList);
		return "inserimentoFocus";
	}

}
