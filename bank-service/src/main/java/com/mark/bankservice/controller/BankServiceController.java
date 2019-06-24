package com.mark.bankservice.controller;

import com.mark.bankservice.dto.DbLinkDto;
import com.mark.bankservice.service.IIpQueryService;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by mark .
 * Data   : 2018/3/12
 * @author mark
 */
@RestController
@RequestMapping("/api")
public class BankServiceController {
    private org.slf4j.Logger logger = LoggerFactory.getLogger(BankServiceController.class);


    @Resource
    private IIpQueryService iIpQueryService;


    @RequestMapping(value = "/findDbLinks")
    public List<DbLinkDto> findDbLinks(@RequestParam("page") int page, @RequestParam("pageSize") int pageSize) {
        return iIpQueryService.findIpAddressByPage(page,pageSize);
    }
}
