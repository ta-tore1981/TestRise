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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.intesigroup.testcasefactory.domain.Attore;
import com.intesigroup.testcasefactory.domain.Progetto;
import com.intesigroup.testcasefactory.form.AttoreViewCRUDForm;
import com.intesigroup.testcasefactory.form.InterfacciaViewCRUDForm;
import com.intesigroup.testcasefactory.form.ProgettoViewCRUDForm;
import com.intesigroup.testcasefactory.service.AttoreService;
import com.intesigroup.testcasefactory.service.ProgettoService;

@Controller
@RequestMapping("/")
public class ProgettoController {
	@Autowired
	ProgettoService progettoService;
	@Autowired
	AttoreService attoreService;
	
	Logger logger = LoggerFactory.getLogger(ProgettoController.class);
	
	@PostMapping("/progetto/formProgetto")
	public String insProgetti(@ModelAttribute ProgettoViewCRUDForm form,String action,RedirectAttributes redirAttrs,  Model model) {
		Progetto progetto= new Progetto();
		if (action.equals("Inserisci")){
			//verifica dei dati per la creazione del progetto
			this.validationFormProgetto(redirAttrs, form);
			// creazione progetto
			if (!redirAttrs.getFlashAttributes().containsKey("error")) {
				progetto.setId(0);
				progetto.setDescrizione(form.getDescrizione().trim());
				progetto.setCodice(form.getCodice().trim());		
				progetto.setNome(form.getNome().trim());
				progetto=progettoService.save(progetto);	
				redirAttrs.addAttribute("idSelected",progetto.getId());
				return("redirect:/progetto/visualizza");	
			}
		}
		if (action.equals("Modifica")){
			if (!redirAttrs.getFlashAttributes().containsKey("nomeProgettoVuoto") && !redirAttrs.getFlashAttributes().containsKey("codiceProgettoVuoto")) {
				redirAttrs.getFlashAttributes().remove("nomeCodiceProgettoDuplicato");
				progetto= progettoService.getProgetto(form.getId()).orElse(null);
				progetto.setDescrizione(form.getDescrizione());
				progetto.setCodice(form.getCodice());		
				progetto.setNome(form.getNome());
				progettoService.save(progetto);
				return("redirect:/progetto/visualizza?idSelected="+progetto.getId());
			}
		}
		if (action.equals("Cancella")){
			progettoService.delete(form.getId());
			return("redirect:/progetto/visualizza");
		}
		if (action.equals("Interfaccia")) {
			return("redirect:/interfaccia/visualizza?idProgetto="+form.getId());
		}
		return("redirect:/progetto/visualizza");
	}
	
	private void  validationFormProgetto(RedirectAttributes redirAttrs, ProgettoViewCRUDForm form) {
		if (form.getNome().trim().equals("")) {
			redirAttrs.addFlashAttribute("nomeProgettoVuoto", "nomeProgettoVuoto");
			redirAttrs.addFlashAttribute("error","true");
		}
		if (progettoService.findAll().stream().filter(p->p.getNome().trim().toUpperCase().equals(form.getNome().trim().toUpperCase()) || p.getCodice().trim().equals(form.getCodice().trim())).findAny().orElse(null)!=null) {
			redirAttrs.addFlashAttribute("nomeCodiceProgettoDuplicato", "nomeCodiceProgettoDuplicato");
			redirAttrs.addFlashAttribute("error","true");
		}
		if (form.getCodice().trim().equals("")) {
			redirAttrs.addFlashAttribute("codiceProgettoVuoto", "codiceProgettoVuoto");
			redirAttrs.addFlashAttribute("error","true");
		}
	}

	@PostMapping("/attore/formAttore")
	public String formAttore(@ModelAttribute AttoreViewCRUDForm form, String action, RedirectAttributes redirAttrs ) {
		Attore attore = new Attore();
		if (action.equals("Inserisci")) {
			this.validationFormAttore(redirAttrs, form);
			if (!redirAttrs.getFlashAttributes().containsKey("error")) {
				attore.setId(0);
				attore.setDescrizione(form.getDescrizione());
				attore.setCodice(form.getCodice());		
				attore.setNome(form.getNome());
				attore.setProgetto(progettoService.getProgetto(form.getIdProgetto()).get());
				attoreService.save(attore);
			}
			return("redirect:/progetto/visualizza?idSelected="+form.getIdProgetto());
		}
		else if (action.equals("Modifica")){
			this.validationFormAttore(redirAttrs, form);
			if (!redirAttrs.getFlashAttributes().containsKey("nomeAttoreVuoto") && !redirAttrs.getFlashAttributes().containsKey("codiceAttoreVuoto")) {
				redirAttrs.getFlashAttributes().remove("nomeCodiceAttoreDuplicato");
				attore = attoreService.getAttore(form.getId()).orElse(null);
				attore.setDescrizione(form.getDescrizione());
				attore.setCodice(form.getCodice());		
				attore.setNome(form.getNome());
		        attoreService.save(attore);
			}
			return("redirect:/progetto/visualizza?idSelected="+form.getIdProgetto()+"&attoreSelected="+attore.getId());		
		}
		else if (action.equals("Cancella")){
			attoreService.deleteById(form.getId());
			return("redirect:/progetto/visualizza?idSelected="+form.getIdProgetto());
		}
		else return("redirect:/progetto/visualizza?idSelected"+form.getIdProgetto());
	}
	private void  validationFormAttore(RedirectAttributes redirAttrs, AttoreViewCRUDForm form) {
		if (form.getNome().trim().equals("")) {
			redirAttrs.addFlashAttribute("nomeAttoreVuoto", "nomeAttoreVuoto");
			redirAttrs.addFlashAttribute("error","true");
		}
		if (attoreService.findByProgettoId(form.getIdProgetto()).stream().filter(p->p.getNome().trim().toUpperCase().equals(form.getNome().trim().toUpperCase()) || p.getCodice().trim().equals(form.getCodice().trim())).findAny().orElse(null)!=null) {
			redirAttrs.addFlashAttribute("nomeCodiceAttoreDuplicato", "nomeCodiceAttoreDuplicato");
			redirAttrs.addFlashAttribute("error","true");
		}
		if (form.getCodice().trim().equals("")) {
			redirAttrs.addFlashAttribute("codiceAttoreVuoto", "codiceAttoreVuoto");
			redirAttrs.addFlashAttribute("error","true");
		}
	}
	
	
	@GetMapping("/progetto/visualizza")
	public String  projectView1(@RequestParam(required=false, name="idSelected") Long idSelected,@RequestParam(required=false) Long attoreSelected, Model model) {
		ProgettoViewCRUDForm form= new ProgettoViewCRUDForm();
		List<Progetto> progettoList = progettoService.findAll();
		//valorizzo i valori di output con la lista dei progetti
		AttoreViewCRUDForm attoreForm = new AttoreViewCRUDForm();
		progettoList.sort(Comparator.comparing(Progetto::getId));
		ArrayList<Attore> attoreList= new ArrayList<Attore>();
		if (idSelected!=null) {
			Progetto progetto=progettoList.stream().filter(u->u.getId()==idSelected).findAny().orElse(null);
			//nel caso il non esiste imposta l'ID di output uguale a null per creare una nuova funzionalita
			if (progetto==null) {
				form.setId(Long.valueOf(0));
				attoreForm.setId(Long.valueOf(-1));
			}
			else {
				form.setId(progetto.getId());
				form.setCodice(progetto.getCodice());
				form.setDescrizione(progetto.getDescrizione());
				form.setNome(progetto.getNome());
				attoreForm.setIdProgetto(progetto.getId());
				attoreList = new ArrayList<Attore>(progetto.getAttore());
				attoreList.sort(Comparator.comparing(Attore::getId));
				if (attoreSelected!=null) {
					Attore attore = attoreList.stream().filter(u->u.getId()==attoreSelected).findAny().orElse(null);
					if (attore==null) attoreForm.setId(Long.valueOf(0));
					else {
						attoreForm.setId(attore.getId());
						attoreForm.setNome(attore.getNome());
						attoreForm.setCodice(attore.getCodice());
						attoreForm.setDescrizione(attore.getDescrizione());
					}
				}
			}
		}
		else {
			form.setId(Long.valueOf(0));
			attoreForm.setId(Long.valueOf(-1));
		}
		model.addAttribute("form",form);
		model.addAttribute("attoreForm",attoreForm);
		model.addAttribute("attoreList",attoreList);
		model.addAttribute("progettoList",progettoList);
		return "inserimentoProgetto";
	}
}
