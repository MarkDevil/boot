package com.mark.bankservice.service;

import com.mark.bankservice.dao.DbLinkMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by mark .
 * Data   : 2019/3/8
 */
@Service
public class IpQueryService {

    private final DbLinkMapper dbLinkMapper;
    private final Logger logger = LoggerFactory.getLogger(IpQueryService.class);

    @Autowired
    public IpQueryService(DbLinkMapper dbLinkMapper) {
        this.dbLinkMapper = dbLinkMapper;
    }

    @PostConstruct
    public void init(){
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                logger.info(dbLinkMapper.findAllDbLinks().toString());
            }
        },0,3000);

    }
}
