package com.mark.bankservice.service.impl;

import com.github.rholder.retry.*;
import com.google.common.base.Predicates;
import com.mark.bankservice.dao.DbLinkMapper;
import com.mark.bankservice.service.IIpQueryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Created by mark .
 * Data   : 2019/3/8
 */
@Service
public class IpQueryServiceImpl implements IIpQueryService {

    private final DbLinkMapper dbLinkMapper;
    private final Logger logger = LoggerFactory.getLogger(IpQueryServiceImpl.class);

    @Autowired
    public IpQueryServiceImpl(DbLinkMapper dbLinkMapper) {
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

    @Retryable(value = RuntimeException.class)
    @Override
    public String queryIpAddress() {
        logger.info("执行调用");
        throw new RuntimeException("重试");
    }

    @Retryable(value = RuntimeException.class,backoff = @Backoff(delay = 5000L,multiplier = 2))
    @Override
    public void isIpAddress(boolean flag) {
        logger.info("执行调用");
        if (flag){
            logger.info("执行成功");
        }else {
            throw new RuntimeException("重试");
        }
    }


    public void testRetryer(){
        Callable<Boolean> callable = () -> {
            logger.info("执行方法");
            return false;
        };

        Retryer<Boolean> retryer = RetryerBuilder.<Boolean>newBuilder()
                //抛出runtime异常、checked异常时都会重试，但是抛出error不会重试。
                .retryIfException()
                //返回false也需要重试
                .retryIfResult(Predicates.equalTo(false))
                //重调策略
                .withWaitStrategy(WaitStrategies.fixedWait(10, TimeUnit.SECONDS))
                //尝试次数
                .withStopStrategy(StopStrategies.stopAfterAttempt(3))
                .build();

        try {
            retryer.call(callable);
        } catch (ExecutionException | RetryException e) {
            e.printStackTrace();
        }
    }

}
