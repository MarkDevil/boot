package com.mark.bankservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

/**
 * @author mark
 */
@MapperScan("com.mark.bankservice.dao")
@EnableRetry
@SpringBootApplication
public class BankServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(BankServiceApplication.class, args);
	}
}
