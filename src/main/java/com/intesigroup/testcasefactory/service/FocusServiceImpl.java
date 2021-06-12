package com.intesigroup.testcasefactory.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.intesigroup.testcasefactory.domain.Focus;
import com.intesigroup.testcasefactory.repository.FocusRepository;

@Service
@Transactional
public class FocusServiceImpl implements FocusService{
	
	@Autowired
	FocusRepository focusRepository;

	@Override
	public Focus save(Focus focus) {
		return focusRepository.save(focus);
		
	}

	@Override
	public List<Focus> findAll() {
		return focusRepository.findAll();
	}

	@Override
	public Optional<Focus> getFocus(long id) {
		return focusRepository.findById(id);
	}
	@Override
	public void deleteById(long id) {
		focusRepository.deleteById(id);
	}
	public List<Focus> findByFunzionalitaId(long id){
		return focusRepository.findByFunzionalitaId(id);
	}
	
}
