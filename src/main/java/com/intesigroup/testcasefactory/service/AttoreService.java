package com.intesigroup.testcasefactory.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.intesigroup.testcasefactory.domain.Attore;

@Service
public interface AttoreService {
	public List<Attore> findAll();
	public void save(Attore attore);
	public void deleteById(long id);
	public Optional<Attore> getAttore(long id);
	public List<Attore> findByProgettoId(long id);
	
}
