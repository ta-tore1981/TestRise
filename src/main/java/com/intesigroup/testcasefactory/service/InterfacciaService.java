package com.intesigroup.testcasefactory.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;
import com.intesigroup.testcasefactory.domain.Interfaccia;
import com.intesigroup.testcasefactory.domain.Progetto;

@Service
public interface InterfacciaService {
	public void save(Interfaccia interfaccia);
	public List<Interfaccia> findAll();
	public Optional<Interfaccia> getInterfaccia(long id);
	public void deleteById(long id);
	public void deleteByInterfaccia(Interfaccia interfaccia);
	public List<Interfaccia> findByProgettoId(long id);
}
