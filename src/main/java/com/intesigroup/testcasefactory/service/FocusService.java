package com.intesigroup.testcasefactory.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.intesigroup.testcasefactory.domain.Focus;

@Service
public interface FocusService {
	public void save(Focus focus);
	public List<Focus> findAll();
	public Optional<Focus> getFocus(long id);
	public void deleteById(long id);
	public List<Focus> findByFunzionalitaId(long id);
}
