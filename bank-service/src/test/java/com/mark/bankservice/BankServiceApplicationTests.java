package com.mark.bankservice;

import com.mark.bankservice.service.impl.IpQueryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

@SpringBootTest
public class BankServiceApplicationTests extends AbstractTestNGSpringContextTests {

	@Autowired
	private IpQueryServiceImpl queryService;

	@Test
	public void testRetryAble() {
		queryService.isIpAddress(false);
	}

}
