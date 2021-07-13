package com.intesigroup.testcasefactory.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import com.intesigroup.testcasefactory.domain.TestCase;

@Service
public interface TestCaseService {
	public Optional<TestCase> findById(long id); 
	public List<TestCase> find(Long idProgetto,Long idInterfaccia,Long idFunzionalita,Long idFocus);
	public void deleteById(long id);
	public TestCase save (TestCase testCase);
	public Long countByProgettoId(Long progettoId);
	public Slice<TestCase> getPagingTestCase(Long idProgetto, Long idInterfaccia, Long idFunzionalita, Long idFocus, Pageable pagina);
	public Slice<TestCase> findAll(Pageable pagina);
}
