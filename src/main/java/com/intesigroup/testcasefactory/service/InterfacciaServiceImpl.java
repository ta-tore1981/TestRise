package com.intesigroup.testcasefactory.service;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import com.intesigroup.testcasefactory.domain.Interfaccia;
import com.intesigroup.testcasefactory.repository.InterfacciaRepository;

@Service
@Transactional
public class InterfacciaServiceImpl implements InterfacciaService{
	
	@Autowired
	InterfacciaRepository interfacciaRepository;

	@Override
	public void save(Interfaccia interfaccia) {
		interfacciaRepository.saveAndFlush(interfaccia);
		
	}
	@Override
	public List<Interfaccia> findAll() {
		return interfacciaRepository.findAll();
		
	}
	@Override
	public Optional<Interfaccia> getInterfaccia(long id) {
		return interfacciaRepository.findById(id);
	}
	/*public void deleteById(long id) {
		interfacciaRepository.deleteById(id);
	}*/
	@Override
	public void deleteByInterfaccia(Interfaccia interfaccia) {
		interfacciaRepository.delete(interfaccia);
		interfacciaRepository.flush();
	}
	public void deleteById(long id) {
		interfacciaRepository.deleteById(id);
	}
	
}
