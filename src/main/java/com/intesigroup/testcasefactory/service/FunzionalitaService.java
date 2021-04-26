package com.intesigroup.testcasefactory.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.intesigroup.testcasefactory.domain.Funzionalita;

@Service
public interface FunzionalitaService {
	public void save(Funzionalita interfaccia);
	public List<Funzionalita> findAll();
	public Optional<Funzionalita> getFunzionalita(long id);
	public void deleteById(long idFunzionalita);

}
