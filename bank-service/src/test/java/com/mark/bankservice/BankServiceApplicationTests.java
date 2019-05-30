package com.mark.bankservice;

import com.google.common.collect.Lists;
import com.mark.bankservice.service.impl.IpQueryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class BankServiceApplicationTests extends AbstractTestNGSpringContextTests {

	@Autowired
	private IpQueryServiceImpl queryService;

	@Test
	public void testRetryAble() {
		queryService.isIpAddress(false);
	}

	@Test
	public void testGuavaRetry() {
		queryService.testRetryer();
	}

	@Test
	public void testPartion(){
		List<String> ls = Arrays.asList("1,2,3,4,5,6,7,8,9,10".split(","));
		System.out.println(Lists.partition(ls, 2).size());

	}

}
