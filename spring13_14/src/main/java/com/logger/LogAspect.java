package com.logger;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LogAspect {

    @Around("com.logger.LocatorAspect.isRepository()")
    public Object getMethodInAnnotatedService(ProceedingJoinPoint point) {
        String methodRepository = point.toShortString();
        try {
            log.warn("Before call {} ", methodRepository);
            Object result = point.proceed();
            log.warn("After call {} ", methodRepository);
            return result;
        } catch (Throwable e) {
            log.error("ERROR call {} ", methodRepository);
            throw new RuntimeException(e);
        }
    }

}
