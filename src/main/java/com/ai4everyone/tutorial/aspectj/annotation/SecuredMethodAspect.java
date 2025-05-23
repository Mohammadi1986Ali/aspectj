package com.ai4everyone.tutorial.aspectj.annotation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class SecuredMethodAspect {

    private final static Logger LOGGER = LoggerFactory.getLogger(SecuredMethodAspect.class);

    @Pointcut("@annotation(secured)")
    public void callMethod(Secured secured) {
    }

    @Around("callMethod(secured) && execution(* *(..))")
    public Object around(ProceedingJoinPoint pjp, Secured secured) throws Throwable {
        LOGGER.info("{} lock is {}", pjp.getSignature().toLongString(), secured.isLocked());
        return pjp.proceed();
    }
}
