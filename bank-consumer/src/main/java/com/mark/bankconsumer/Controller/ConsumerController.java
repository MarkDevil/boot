package com.mark.bankconsumer.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by mark .
 * Data   : 2018/3/12
 */
@RestController
public class ConsumerController {

    final RestTemplate restTemplate;


    @Autowired
    public ConsumerController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @RequestMapping(value = "/ribbon-consumer",method = RequestMethod.GET)
    public String helloController() {
        return restTemplate.getForEntity("http://BANK-SERVICE/hello", String.class).getBody();
    }


    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String getShowIndex() {
        return restTemplate.getForEntity("http://BANK-SERVICE/", String.class).getBody();
    }

}
