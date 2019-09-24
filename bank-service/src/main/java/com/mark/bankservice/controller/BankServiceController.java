package com.mark.bankservice.controller;

import com.mark.bankservice.dto.DbLinkDto;
import com.mark.bankservice.service.IIpQueryService;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class BankServiceController {

    @Resource
    private IIpQueryService iIpQueryService;


    @RequestMapping(value = "/findDbLinks")
    public List<DbLinkDto> findDbLinks(@RequestParam("page") int page, @RequestParam("pageSize") int pageSize) {
        return iIpQueryService.findIpAddressByPage(page,pageSize);
    }
}
