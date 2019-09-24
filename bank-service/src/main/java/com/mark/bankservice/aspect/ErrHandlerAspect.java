package com.mark.bankservice.aspect;

import com.mark.bankservice.dto.Response;
import com.mark.bankservice.utils.ConcurrentStopWatch;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * Created by mark .
 * Data   : 2019-08-01
 */
@Aspect
@Component
@Lazy()
@Slf4j
public class ErrHandlerAspect {

    private ConcurrentStopWatch concurrentStopWatch = new ConcurrentStopWatch();

    @Pointcut(value = "execution(* com.*.*(..))")
    private void pointCut(){

    }

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint joinPoint) {
        Response<?> response;
        try {
            concurrentStopWatch.start("执行时间");
            response = (Response<?>) joinPoint.proceed();
        }catch (Throwable throwable){
            response = handlerException(joinPoint,throwable);
        }
        concurrentStopWatch.stop();
        concurrentStopWatch.prettyPrint();
        return response;
    }

    private <T> Response<T> handlerException(ProceedingJoinPoint pjp, Throwable e) {
        Response<T> response = null;
        if (e instanceof RuntimeException) {
            log.error("RuntimeException{方法：" + pjp.getSignature() + "， 参数：" + pjp.getArgs() + ",异常：" + e.getMessage() + "}", e);
            response = new Response(101,"",null);
        } else {
            log.error("异常{方法：" + pjp.getSignature() + "， 参数：" + pjp.getArgs() + ",异常：" + e.getMessage() + "}", e);
            response = new Response(101,"",null);
        }
        return response;
    }

}
