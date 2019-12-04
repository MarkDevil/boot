package com.mark.bank.service.impl;

import com.github.pagehelper.PageHelper;
import com.mark.bank.dao.DbLinkDao;
import com.mark.bank.dto.DbLinkDto;
import com.mark.bank.dto.PageBean;
import com.mark.bank.service.IIpQueryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;

/**
 * Created by mark .
 * Data   : 2019/3/8
 */
@Service
@Slf4j
public class IpQueryServiceImpl implements IIpQueryService {

    @Resource
    private DbLinkDao dbLinkDao;


    @PostConstruct
    public void init(){
//        new Timer().scheduleAtFixedRate(new TimerTask() {
//            @Override
//            public void run() {
//                logger.info(dbLinkMapper.findAllDbLinks().toString());
//            }
//        },0,3000);

    }

    @Retryable(value = RuntimeException.class)
    @Override
    public String queryIpAddress() {
        log.info("执行调用");
        return "执行成功";
    }

    @Retryable(value = RuntimeException.class,backoff = @Backoff(delay = 5000L,multiplier = 2))
    @Override
    public void isIpAddress(boolean flag) {
        log.info("执行调用");
        if (flag){
            log.info("执行成功");
        }else {
            throw new RuntimeException("重试");
        }
    }

    @Override
    public List<DbLinkDto> findIpAddressByPage(int currentPage, int pageSize) {
        PageHelper.startPage(currentPage,pageSize,true);
        List<DbLinkDto> dbLinkDtos = dbLinkDao.findAllDbLinks();
        log.info("查询到所有数据为:{}",dbLinkDtos.toString());
        int nums = dbLinkDtos.size();
        PageBean<DbLinkDto> pagedatas = new PageBean<>(currentPage, pageSize, nums);
        pagedatas.setItems(dbLinkDtos);
        return pagedatas.getItems();
    }


}
