package com.intesigroup.testcasefactory.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.intesigroup.testcasefactory.domain.Approccio;
@Service
public interface ApproccioService {
	public List<Approccio> findAll();
	public Optional<Approccio> findById(long id);

}
