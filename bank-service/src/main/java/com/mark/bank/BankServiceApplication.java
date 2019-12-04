package com.mark.bank;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

/**
 * @author mark
 */
@EnableRetry
@SpringBootApplication
@MapperScan(basePackages = {"com.mark.bank.dao"})
public class BankServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(BankServiceApplication.class, args);
	}
}
