package com.intesigroup.testcasefactory.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.intesigroup.testcasefactory.domain.Funzionalita;
import com.intesigroup.testcasefactory.repository.FunzionalitaRepository;

@Service
@Transactional
public class FunzionalitaServiceImpl implements FunzionalitaService{
	@Autowired
	FunzionalitaRepository funzionalitaService;
	
	@Override
	public Funzionalita save(Funzionalita funzionalita) {
		return funzionalitaService.save(funzionalita);
		
	}

	@Override
	public List<Funzionalita> findAll() {
		return funzionalitaService.findAll();
	}

	@Override
	public Optional<Funzionalita> getFunzionalita(long id) {
		return funzionalitaService.findById(id);
	}
	@Override
	public void delete(long idFunzionalita) {
		funzionalitaService.deleteById(idFunzionalita);
	}
	public List<Funzionalita> findByInterfacciaId(long id){
		return funzionalitaService.findByInterfacciaId(id);
	}
	public List<Funzionalita> findByProgettoId(long id){
		return funzionalitaService.findByInterfacciaId(id);
	}

}
