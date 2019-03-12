package com.mark.bankservice.controller;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by mark .
 * Data   : 2018/3/12
 * @author mark
 */
@RestController
public class BankServiceController {
    private org.slf4j.Logger logger = LoggerFactory.getLogger(BankServiceController.class);


    private final DiscoveryClient discoveryClient;

    @Autowired
    public BankServiceController(DiscoveryClient discoveryClient) {
        this.discoveryClient = discoveryClient;
        List<String> services = this.discoveryClient.getServices();
        logger.info("Get services from server: {}",services.toString());
    }

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String index() {
        List<ServiceInstance> instances = discoveryClient.getInstances("bank-service");
        for (ServiceInstance instance : instances) {
            logger.info("/hello,host:" + instance.getHost() + ",service_id:" + instance.getServiceId());
        }
        return "Hello World";
    }
}
