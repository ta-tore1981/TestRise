package com.intesigroup.testcasefactory.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.intesigroup.testcasefactory.domain.Attore;
import com.intesigroup.testcasefactory.repository.AttoreRepository;


@Service
public class AttoreServiceImpl implements AttoreService{
	@Autowired
	AttoreRepository attoreRepository;
	
	public Attore save(Attore attore) {
		 return attoreRepository.save(attore);
	}
	public void deleteById(long id) {
		attoreRepository.deleteById(id);
		
	}
	public List<Attore> findAll(){
		return attoreRepository.findAll();
	}
	public Optional<Attore>getAttore(long id) {
		return attoreRepository.findById(id);
	}
	public List<Attore> findByProgettoId(long id) {
		return attoreRepository.findByProgettoId(id);
	}
	
}
