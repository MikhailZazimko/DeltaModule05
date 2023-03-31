package com.logger;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Aspect
@Slf4j
@EnableAspectJAutoProxy
@Component
public class LocatorAspect {

    @Pointcut("isRepositoryInterface() || isRepoByPackage()")
    public void isRepository() {
    }


    @Pointcut("this(org.springframework.data.repository.Repository)")
    private void isRepositoryInterface() {
    }


    @Pointcut("within(com..repository.impl.*)")
    private void isRepoByPackage() {
    }

}
