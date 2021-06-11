package com.intesigroup.testcasefactory.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.intesigroup.testcasefactory.domain.TipoTest;
import com.intesigroup.testcasefactory.repository.TipoTestRepository;

@Service
public class TipoTestServiceImpl implements TipoTestService{
	
	@Autowired
	TipoTestRepository tipoTestRepository;
	
	@Override
	public List<TipoTest> findAll() {
		return tipoTestRepository.findAll();
	}

	@Override
	public Optional<TipoTest> findById(long id) {
		return tipoTestRepository.findById(id);
	}
	

}
