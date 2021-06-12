package com.intesigroup.testcasefactory.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.intesigroup.testcasefactory.domain.Interfaccia;
import com.intesigroup.testcasefactory.domain.Progetto;
import com.intesigroup.testcasefactory.repository.ProgettoRepository;

@Service
@Transactional
public class ProgettoServiceImpl implements ProgettoService{	
	
	@Autowired
	ProgettoRepository progettoRepository;
	
	public List<Progetto> findAll() {
		return progettoRepository.findAll();
	}
	public Progetto save(Progetto progetto) {
		return progettoRepository.saveAndFlush(progetto);
	}
	public Optional<Progetto> getProgetto(long id) {
		return progettoRepository.findById(id);
	}
	public void delete(long id) {
		progettoRepository.deleteById(id);
	}
}
