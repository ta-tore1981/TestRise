package com.intesigroup.testcasefactory.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.intesigroup.testcasefactory.domain.Progetto;

@Service
public interface ProgettoService {
	public List<Progetto> findAll();
	public Progetto save(Progetto progetto);
	public Optional<Progetto> getProgetto(long id);
	public void delete(long id);
}
