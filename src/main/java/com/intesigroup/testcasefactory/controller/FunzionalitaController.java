package com.intesigroup.testcasefactory.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.intesigroup.testcasefactory.domain.Attore;
import com.intesigroup.testcasefactory.domain.Funzionalita;
import com.intesigroup.testcasefactory.domain.Interfaccia;
import com.intesigroup.testcasefactory.entityView.AbilitazioneAttoreViewCRUDForm;
import com.intesigroup.testcasefactory.entityView.AttoreViewCRUDForm;
import com.intesigroup.testcasefactory.entityView.FunzionalitaViewCRUDForm;
import com.intesigroup.testcasefactory.service.AttoreService;
import com.intesigroup.testcasefactory.service.FunzionalitaService;
import com.intesigroup.testcasefactory.service.InterfacciaService;

@Controller
public class FunzionalitaController {
	@Autowired 
	FunzionalitaService funzionalitaService;
	@Autowired
	InterfacciaService interfacciaService;
	@Autowired
	AttoreService attoreService;
	Logger logger = LoggerFactory.getLogger(FunzionalitaController.class);
	

	@PostMapping("/funzionalita/formFunzionalita")
	public String insFunzionalita(@ModelAttribute FunzionalitaViewCRUDForm form,String action,RedirectAttributes redirAttrs, Model model) {
		Funzionalita funzionalita= new Funzionalita();
		this.validationFormInterfaccia(redirAttrs,form);
		if (action.equals("Inserisci")){
			if (!redirAttrs.getFlashAttributes().containsKey("error")) {
				funzionalita.setId(0);
				funzionalita.setInterfaccia(interfacciaService.getInterfaccia(form.getIdInterfaccia()).orElse(null));
				funzionalita.setDescrizione(form.getDescrizione());
				funzionalita.setCodice(form.getCodice());		
				funzionalita.setNome(form.getNome());
				funzionalitaService.save(funzionalita);
				
			}
			return("redirect:/funzionalita/visualizza?idInterfaccia="+form.getIdInterfaccia());
		}
		if (action.equals("Modifica")){
			if (!redirAttrs.getFlashAttributes().containsKey("nomeFunzionalitaVuoto") && !redirAttrs.getFlashAttributes().containsKey("codiceFunzionalitaVuoto"))  {
				redirAttrs.getFlashAttributes().remove("nomeCodiceFunzionalitaDuplicato");
				funzionalita= funzionalitaService.getFunzionalita(form.getId()).orElse(null);
				funzionalita.setDescrizione(form.getDescrizione());
				funzionalita.setCodice(form.getCodice());		
				funzionalita.setNome(form.getNome());
				funzionalitaService.save(funzionalita);
			}
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
	private void  validationFormInterfaccia(RedirectAttributes redirAttrs, FunzionalitaViewCRUDForm form) {
		if (form.getNome().trim().equals("")) {
			redirAttrs.addFlashAttribute("nomeFunzionalitaVuoto", "nomeFunzionalitaVuoto");
			redirAttrs.addFlashAttribute("error","true");
		}
		if (funzionalitaService.findByInterfacciaId(form.getIdInterfaccia()).stream().filter(p->p.getNome().trim().toUpperCase().equals(form.getNome().trim().toUpperCase()) || p.getCodice().trim().equals(form.getCodice().trim())).findAny().orElse(null)!=null) {
			redirAttrs.addFlashAttribute("nomeCodiceFunzionalitaDuplicato", "nomeCodiceFunzionalitaDuplicato");
			redirAttrs.addFlashAttribute("error","true");
		}
		if (form.getCodice().trim().equals("")) {
			redirAttrs.addFlashAttribute("codiceFunzionalitaVuoto", "codiceFunzionalitaVuoto");
			redirAttrs.addFlashAttribute("error","true");
		}
	}
	
	@PostMapping("/funzionalita/formAbilitazioni")
	public String formAbilitazioni(@ModelAttribute AbilitazioneAttoreViewCRUDForm formAbilitazione, Long idInterfaccia, RedirectAttributes redirAttrs) {
		Attore attore = attoreService.getAttore(formAbilitazione.getIdAttore()).orElse(null);
        Funzionalita funzionalita= funzionalitaService.getFunzionalita(formAbilitazione.getIdFunzionalita()).orElse(null);
        if (attore==null || funzionalita==null ) {
			redirAttrs.addFlashAttribute("erroreAttoreFunzionalitaInesistente","erroreAttoreFunzionalitaInesistente");
			return("redirect:/funzionalita/visualizza?idInterfaccia="+idInterfaccia+"&idSelected="+formAbilitazione.getIdFunzionalita());
		}
		if (formAbilitazione.isAbilitato()) {
			attore.getFunzionalita().add(funzionalita);
			redirAttrs.addFlashAttribute("confermaFunzionalitaAbilitazione","confermaFunzionalitaAbilitazione");
		}
		else {
			attore.getFunzionalita().remove(funzionalita);
			redirAttrs.addFlashAttribute("confermaFunzionalitaDisabilitazione","confermaFunzionalitaDisabilitazione");
		}
		attoreService.save(attore);
		return("redirect:/funzionalita/visualizza?idInterfaccia="+idInterfaccia+"&idSelected="+formAbilitazione.getIdFunzionalita());
	}
	
	@GetMapping("/funzionalita/visualizza")
	public String  projectView1(@RequestParam(required=false, name="idSelected") Long idSelected, @RequestParam(required=true, name="idInterfaccia") Long idInterfaccia, Model model) {
		FunzionalitaViewCRUDForm form= new FunzionalitaViewCRUDForm();
		AbilitazioneAttoreViewCRUDForm formAbilitazione = new AbilitazioneAttoreViewCRUDForm();
		List<Funzionalita> funzionalitaList;
		Funzionalita funzionalita;
		Interfaccia interfaccia = interfacciaService.getInterfaccia(idInterfaccia).orElse(null);
		//dichiaro il form delle abilitazioni delle funzionalita per ogni attore
		List<AbilitazioneAttoreViewCRUDForm> abilitazioneList= new ArrayList<AbilitazioneAttoreViewCRUDForm>();
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
					// seleziono tutti gli attori del progetto
					List<Attore> attore = attoreService.findByProgettoId(interfaccia.getProgetto().getId());
					// inizializzo la lista degli attori anilitati alla funzionalita selezionata
					for (Attore a : attore) {
						boolean trovato=false;
						// ciclo su tutti le funzioni degli attori e valorizzo temp solo se una funzione corrisponde alla funzione selezionata
						for (Funzionalita f: a.getFunzionalita()) 
							if (f.getId()==funzionalita.getId()) trovato= true;
						abilitazioneList.add(new AbilitazioneAttoreViewCRUDForm(Long.valueOf(funzionalita.getId()),a,trovato));
					}
					//valorizzazione del form
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
		model.addAttribute("formAbilitazione",formAbilitazione);
		model.addAttribute("abilitazioneList",abilitazioneList);
		model.addAttribute("funzionalitaList",funzionalitaList);
		return "inserimentoFunzionalita";
	}
}
