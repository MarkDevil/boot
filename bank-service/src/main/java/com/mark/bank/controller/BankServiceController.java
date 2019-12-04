package com.mark.bank.controller;

import com.mark.bank.dto.DbLinkDto;
import com.mark.bank.service.IIpQueryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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


    @PostMapping(value = "/findDbLinks")
    public List<DbLinkDto> findDbLinks(@RequestParam("page") int page, @RequestParam("pageSize") int pageSize) {
        return iIpQueryService.findIpAddressByPage(page,pageSize);
    }

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String check() {
        return "hello";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "hello";
    }
}
