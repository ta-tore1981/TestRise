package com.intesigroup.testcasefactory.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.intesigroup.testcasefactory.domain.TestCase;
import com.intesigroup.testcasefactory.repository.PagingTestCaseReporitoy;
import com.intesigroup.testcasefactory.repository.TestCaseRepository;

@Service
@Transactional
public class TestCaseServiceImpl implements TestCaseService{
	@Autowired
	TestCaseRepository testCaseRepository;
	
	@Autowired
	PagingTestCaseReporitoy pagingTestCaseReporitoy;
	
	@Override
	public Optional<TestCase> findById(long id) {
		return testCaseRepository.findById(id);
	}
	@Override
	public void deleteById(long id) {
		testCaseRepository.deleteById(id);
	}
	@Override
	public List<TestCase> find(Long idProgetto,Long idInterfaccia,Long idFunzionalita,Long idFocus) {
		List <TestCase> testCase= testCaseRepository.findAll();
		if (idProgetto!=null && idProgetto!=0) {
			testCase=testCase.stream().filter(u->u.getProgetto().getId()==idProgetto).collect(Collectors.toList());
		}
		if (idInterfaccia!=null && idInterfaccia!=0) {
			testCase=testCase.stream().filter(u->u.getInterfaccia().getId()==idInterfaccia).collect(Collectors.toList());
		}
		if (idFunzionalita!=null && idFunzionalita!=0) {
			testCase=testCase.stream().filter(u->u.getFunzionalita().getId()==idFunzionalita).collect(Collectors.toList());
		}
		if (idFocus!=null && idFocus!=0) {
			testCase=testCase.stream().filter(u->u.getFocus().getId()==idFocus).collect(Collectors.toList());
		}
		return testCase;
	}
	@Override
	public TestCase save(TestCase testCase) {
		return testCaseRepository.saveAndFlush(testCase);	
	}

	public Long countByProgettoId(Long progettoId) {
		return testCaseRepository.countByProgettoId(progettoId);
	}
	
	public Slice<TestCase> getPagingTestCase(Long idProgetto, Long idInterfaccia, Long idFunzionalita, Long idFocus, Pageable pagina) {
		Slice<TestCase> slice = null;
		if (pagina==null)  pagina=PageRequest.of(0, 20);
		
		if (idProgetto!=0 && idInterfaccia!=0 && idFunzionalita!=0  && idFocus!=0) 
			slice=pagingTestCaseReporitoy.findAllByProgettoIdAndInterfacciaIdAndFunzionalitaIdAndFocusId(idProgetto, idInterfaccia, idFunzionalita,idFocus, pagina);
		
		else if (idProgetto!=0 && idInterfaccia!=0 && idFunzionalita!=0) 
			slice = pagingTestCaseReporitoy.findAllByProgettoIdAndInterfacciaIdAndFunzionalitaId(idProgetto, idInterfaccia, idFunzionalita, pagina);
		
		else if (idProgetto!=0 && idInterfaccia!=0) {
			slice = pagingTestCaseReporitoy.findAllByProgettoIdAndInterfacciaId(idProgetto, idInterfaccia, pagina);
		}
		else if (idProgetto!=0) {
			slice=pagingTestCaseReporitoy.findAllByProgettoId(idProgetto, pagina);
		}
		else {
			slice = pagingTestCaseReporitoy.findAll(pagina);
		}
		return slice;
		
	}
	@Override
	public Slice<TestCase> findAll(Pageable pagina) {
		return pagingTestCaseReporitoy.findAll(pagina);
	}
}
