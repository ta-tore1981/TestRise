package com.intesigroup.testcasefactory.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.intesigroup.testcasefactory.domain.TipoTest;

@Service
public interface TipoTestService {
	public List<TipoTest> findAll();
	public Optional<TipoTest> findById(long id);
}
