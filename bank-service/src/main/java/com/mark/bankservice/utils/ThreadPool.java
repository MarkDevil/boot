package com.mark.bankservice.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by mark .
 * Data   : 2019-08-07
 * @author mark
 */
@Component
@Slf4j
public class ThreadPool {

    private ExecutorService executorService;
    private static final int COREPOOLSIZE = Runtime.getRuntime().availableProcessors();
    private static final int MAXPOOLSIZE = COREPOOLSIZE * 2;


    @PostConstruct
    public void init(){
        ArrayBlockingQueue queue = new ArrayBlockingQueue(10);
        executorService = new ThreadPoolExecutor(
                COREPOOLSIZE,MAXPOOLSIZE,10, TimeUnit.SECONDS,
                queue,
                new CustomThreadFactory());
    }


    static class CustomThreadFactory implements ThreadFactory{

        private final AtomicInteger threadNum = new AtomicInteger(1);

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r, "Thread:" + threadNum.getAndIncrement());
            log.info(t.getName() + " has been created");
            return t;
        }
    }

    /**
     * 执行提交的任务
     * @param callable
     */
    public Future execute(Callable callable){
        return executorService.submit(callable);
    }

    /**
     * 关闭线程池
     * @param isForce
     */
    public void shutDownPool(boolean isForce){
        if (executorService.isShutdown()){
            log.info("线程池已经关闭");
        }else if (isForce){
            executorService.shutdownNow();
        }else {
            executorService.shutdown();
        }
    }

}
