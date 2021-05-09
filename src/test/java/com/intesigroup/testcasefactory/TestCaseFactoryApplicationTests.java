package com.intesigroup.testcasefactory;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.intesigroup.testcasefactory.domain.Attore;
import com.intesigroup.testcasefactory.domain.Funzionalita;
import com.intesigroup.testcasefactory.domain.Interfaccia;
import com.intesigroup.testcasefactory.domain.Progetto;
import com.intesigroup.testcasefactory.service.AttoreService;
import com.intesigroup.testcasefactory.service.FunzionalitaService;
import com.intesigroup.testcasefactory.service.InterfacciaService;
import com.intesigroup.testcasefactory.service.ProgettoService;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)

class TestCaseFactoryApplicationTests {
	@LocalServerPort
	private int port;
	
	/*@Autowired
	private RestTemplate restTemplate;*/
	
	@Autowired
	ProgettoService progettoService; 
	@Autowired
	InterfacciaService interfacciaService;
	
	@Autowired
	FunzionalitaService funzionalitaService;
	@Autowired
	AttoreService attoreService;
	
	
	
	

/*	@Test
	public void contextLoads() throws Exception{
		assertThat(this.restTemplate.getForObject("http://localhost:"+port+"/",String.class)).contains("html");
		
	}*/
	@Test
	public void saveProgect() throws Exception{	
		// Creazione progetto
		Progetto p = new Progetto();
		p.setCodice("cod1");
		p.setDescrizione("Descrizione");
		p.setNome("progetto5");
		progettoService.save(p);
		
		// Creazione interfaccia
		Interfaccia i = new Interfaccia();
        i.setCodice("cod1");
        i.setDescrizione("descrizione1");
        i.setNome("Nome1");
        i.setProgetto(p);
        interfacciaService.save(i);
        p=progettoService.getProgetto(p.getId()).get();
        p.getInterfaccia().forEach((u)->System.out.println("Il nome dell' interfaccia collegata al progetto è"+ u.getNome()));
	}
	@Test 
	void visualizzaProgetti() throws Exception{
		List<Progetto> progetto = progettoService.findAll();
		for (Progetto p : progetto) {
			System.out.println(p.getNome());
		}
	}
	@Test
	void visualizzaInterfacce() throws Exception{
		Progetto p = progettoService.getProgetto(1).get();
		List<Interfaccia> interfaccia= new ArrayList<Interfaccia>(p.getInterfaccia());
		for (Interfaccia f : interfaccia) {
			System.out.println("L'interfaccia collegata al progetto 1 è "+ f.toString());
		}
	}

	@Test
	void deleteInterfaccia() {
			long idInterfaccia=40;
			interfacciaService.deleteById(idInterfaccia);
			assertThat(interfacciaService.getInterfaccia(idInterfaccia).isPresent());
	}
	@Test
	void deleteFunzionalita() throws Exception{
		long idFunzionalita=6;
		funzionalitaService.getFunzionalita(idFunzionalita);
		assertThat(funzionalitaService.getFunzionalita(idFunzionalita).isPresent());
		System.out.println("la descrizione della funzionalita cancellata è " + funzionalitaService.getFunzionalita(idFunzionalita).get().getDescrizione());
	}
	
	@Test
	void deleteFunzionalita1() throws Exception{
		long idFunzionalita=6;
		Funzionalita f=funzionalitaService.getFunzionalita(idFunzionalita).get();
		assertThat(funzionalitaService.getFunzionalita(idFunzionalita).isPresent());
		System.out.println("la descrizione della funzionalita cancellata è " + funzionalitaService.getFunzionalita(idFunzionalita).get().getDescrizione());
	}
	@Test
	void visualizzaAttori() throws Exception {
		List<Progetto> listProgetto = progettoService.findAll();
		for (Progetto p :  listProgetto){
			for (Attore a : p.getAttore()) {
				System.out.println("Il progetto "+p.getNome()+"ha gli attori: ");
				System.out.println("L'id dell'attore è "+a.getCodice());
				System.out.println("il nome dell'attore è "+ a.getNome());
				System.out.println("il codice dell'attore è "+ a.getCodice());
			}
			
		}
	}
}