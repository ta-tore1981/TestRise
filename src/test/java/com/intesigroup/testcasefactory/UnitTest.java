package com.intesigroup.testcasefactory;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)


class UnitTest {
	
	@LocalServerPort
	private int port;
	
	@Autowired
	private RestTemplate restTemplate;
	
	
	@Test
	  void testProjectPage() throws Exception{
		assertThat(this.restTemplate.getForObject("http://localhost:"+port+"/",String.class)).contains("67ghfhg");
		
	}
}
