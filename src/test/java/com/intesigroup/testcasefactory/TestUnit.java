package com.intesigroup.testcasefactory;

import org.junit.Test;
import org.junit.runner.RunWith;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.intesigroup.testcasefactory.domain.TestCase;
import com.intesigroup.testcasefactory.repository.PagingTestCaseReporitoy;

import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;

import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)


public class TestUnit {

	@Autowired
	PagingTestCaseReporitoy pagingTestCaseReporitoy;
	private static final Logger logger = Logger.getLogger(TestUnit.class.getName());
	@Test
	public void testRicerca() {
		Slice<TestCase> slice = pagingTestCaseReporitoy.findAllByProgettoId(Long.valueOf(177), PageRequest.of(0, 3, Sort.by("testId")));
		assertThat(slice.getSize()==0);
		logger.info("slice.size= " + slice.getSize());
		logger.info("slice.number= " + slice.getNumber());
		logger.info("slice.numberOfElemnts= " + slice.getNumberOfElements());
	}
	
	

}
