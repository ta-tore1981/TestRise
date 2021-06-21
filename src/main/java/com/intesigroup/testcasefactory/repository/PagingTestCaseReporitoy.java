package com.intesigroup.testcasefactory.repository;

import java.util.List;


import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.intesigroup.testcasefactory.domain.TestCase;

public interface PagingTestCaseReporitoy extends PagingAndSortingRepository<TestCase , Long>{
	Slice<TestCase> findAllByProgettoId(Long idProgetto, Pageable  page);
	Slice<TestCase> findAllByProgettoIdAndInterfacciaId(Long idProgetto,Long idInterfaccia, Pageable  page);
	Slice<TestCase> findAllByProgettoIdAndInterfacciaIdAndFunzionalitaId(Long idProgetto, Long idInterfaccia,Long idFunzionalita, Pageable  page);
	Slice<TestCase> findAllByProgettoIdAndInterfacciaIdAndFunzionalitaIdAndFocusId(Long idProgetto, Long idInterfaccia,Long idFunzionalita, Long idFocus, Pageable  page);
}
