package com.intesigroup.testcasefactory.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.intesigroup.testcasefactory.domain.Approccio;
import com.intesigroup.testcasefactory.repository.ApproccioRepository;
@Service
public class ApproccioServiceImpl implements ApproccioService{
	@Autowired 
	ApproccioRepository approccioRepository;
	
	@Override
	public List<Approccio> findAll(){
		return approccioRepository.findAll();
	}
	@Override
	public Optional<Approccio> findById(long id){
		return approccioRepository.findById(id);
	}

}
