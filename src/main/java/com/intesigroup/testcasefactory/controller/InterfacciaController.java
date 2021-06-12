package com.intesigroup.testcasefactory.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.intesigroup.testcasefactory.domain.Attore;
import com.intesigroup.testcasefactory.domain.Funzionalita;
import com.intesigroup.testcasefactory.domain.Interfaccia;
import com.intesigroup.testcasefactory.domain.Progetto;
import com.intesigroup.testcasefactory.form.AttoreViewCRUDForm;
import com.intesigroup.testcasefactory.form.FunzionalitaViewCRUDForm;
import com.intesigroup.testcasefactory.form.InterfacciaViewCRUDForm;
import com.intesigroup.testcasefactory.service.AttoreService;
import com.intesigroup.testcasefactory.service.InterfacciaService;
import com.intesigroup.testcasefactory.service.ProgettoService;

@Controller
public class InterfacciaController {
	@Autowired 
	InterfacciaService interfacciaService;
	@Autowired
	ProgettoService progettoService;
	@Autowired
	AttoreService attoreService;
	
	Logger logger = LoggerFactory.getLogger(InterfacciaController.class);
	

	@PostMapping("/interfaccia/formInterfaccia")
	public String insInterfaccia(@ModelAttribute InterfacciaViewCRUDForm form,String action, RedirectAttributes redirAttrs, Model model) {
		Interfaccia interfaccia= new Interfaccia();
		if (action.equals("Inserisci")){
			this.validationParam(redirAttrs,form);
			if (!redirAttrs.getFlashAttributes().containsKey("error")) {
				interfaccia.setId(0);
				interfaccia.setProgetto(progettoService.getProgetto(form.getIdProgetto()).orElse(null));
				interfaccia.setDescrizione(form.getDescrizione());
				interfaccia.setCodice(form.getCodice());		
				interfaccia.setNome(form.getNome());
				interfaccia=interfacciaService.save(interfaccia);	
				redirAttrs.addAttribute("idSelected",interfaccia.getId()); 
				redirAttrs.addAttribute("idProgetto",interfaccia.getProgetto().getId());
				return("redirect:/interfaccia/visualizza");
			}
			return("redirect:/interfaccia/visualizza?idProgetto="+form.getIdProgetto());
		}
		if (action.equals("Modifica")){
			this.validationParam(redirAttrs,form);
			if (!redirAttrs.getFlashAttributes().containsKey("nomeInterfacciaVuoto") && !redirAttrs.getFlashAttributes().containsKey("codiceInterfacciaVuoto")) {
				redirAttrs.getFlashAttributes().remove("nomeCodiceInterfacciaDuplicato");
				interfaccia= interfacciaService.getInterfaccia(form.getId()).orElse(null);
				interfaccia.setDescrizione(form.getDescrizione());
				interfaccia.setCodice(form.getCodice());		
				interfaccia.setNome(form.getNome());
				interfacciaService.save(interfaccia);
			}
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
	
	private void  validationParam(RedirectAttributes redirAttrs, InterfacciaViewCRUDForm form) {
		if (form.getNome().trim().equals("")) {
			redirAttrs.addFlashAttribute("nomeInterfacciaVuoto", "nomeInterfacciaVuoto");
			redirAttrs.addFlashAttribute("error","true");
		}
		if (interfacciaService.findByProgettoId(form.getIdProgetto()).stream().filter(p->p.getNome().trim().toUpperCase().equals(form.getNome().trim().toUpperCase()) || p.getCodice().trim().equals(form.getCodice().trim())).findAny().orElse(null)!=null) {
			redirAttrs.addFlashAttribute("nomeCodiceInterfacciaDuplicato", "nomeCodiceInterfacciaDuplicato");
			redirAttrs.addFlashAttribute("error","true");
		}
		if (form.getCodice().trim().equals("")) {
			redirAttrs.addFlashAttribute("codiceInterfacciaVuoto", "codiceInterfacciaVuoto");
			redirAttrs.addFlashAttribute("error","true");
		}
	}
	@PostMapping("/interfaccia/abilitazioneUtente")
	public String abilitazioneUtente(@ModelAttribute AttoreViewCRUDForm form, Long idInterfaccia,String action, RedirectAttributes redirAttrs) {
        Attore attore=attoreService.getAttore(form.getId()).orElse(null);
		Interfaccia interfaccia = interfacciaService.getInterfaccia(idInterfaccia).orElse(null);
		if (interfaccia==null) {
			return("redirect:/interfaccia/visualizza?idProgetto="+attore.getProgetto().getId());
		}
		if (action.equals("Abilita")){
			for (Funzionalita funzionalita : interfaccia.getFunzionalita()) {
				attore.getFunzionalita().add(funzionalita);
				attoreService.save(attore);
			}
			redirAttrs.addFlashAttribute("confermaInterfacciaAbilitazione","confermaInterfacciaAbilitazione");
		}
		if (action.equals("Disabilita")){
			for (Funzionalita funzionalita : interfaccia.getFunzionalita()) {
				attore.getFunzionalita().remove(funzionalita);
				attoreService.save(attore);
			}
			redirAttrs.addFlashAttribute("confermaInterfacciaDisabilitazione","confermaInterfacciaDisabilitazionee");
		}
		return("redirect:/interfaccia/visualizza?idProgetto="+attore.getProgetto().getId()+"&idSelected="+idInterfaccia);
	}
	@GetMapping("/interfaccia/visualizza")
	public String  projectView1(@RequestParam(required=false, name="idSelected") Long idSelected, @RequestParam(required=true, name="idProgetto") Long idProgetto, Model model) {
		InterfacciaViewCRUDForm form= new InterfacciaViewCRUDForm();
		Interfaccia interfaccia;
		List<Interfaccia> interfacciaList;
		List<Attore> attoreList= new ArrayList<Attore>();
		AttoreViewCRUDForm attoreForm = new AttoreViewCRUDForm();
		Progetto progetto = progettoService.getProgetto(idProgetto).orElse(null);
		if (progetto!=null) {
			form.setNomeProgetto(progetto.getNome());
			interfacciaList = new ArrayList<Interfaccia>(progetto.getInterfaccia());
			interfacciaList.sort(Comparator.comparing(Interfaccia::getId));
			attoreList= attoreService.findByProgettoId(idProgetto);
			attoreList.sort(Comparator.comparing(Attore::getId));
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
		model.addAttribute("attoreList",attoreList);
		model.addAttribute("attoreForm",attoreForm);
		return "inserimentoInterfaccia";
		
	}
}
