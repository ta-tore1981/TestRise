package com.intesigroup.testcasefactory.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.intesigroup.testcasefactory.domain.Funzionalita;

@Service
public interface FunzionalitaService {
	public void save(Funzionalita funzionalita);
	public List<Funzionalita> findAll();
	public Optional<Funzionalita> getFunzionalita(long id);
	public void delete(long idFunzionalita);
	public List<Funzionalita> findByInterfacciaId(long id);
	public List<Funzionalita> findByProgettoId(long id);
}
