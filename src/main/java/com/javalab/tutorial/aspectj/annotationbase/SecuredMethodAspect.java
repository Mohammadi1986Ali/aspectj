package com.javalab.tutorial.aspectj.annotationbase;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class SecuredMethodAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(SecuredMethodAspect.class);

    @Pointcut("@annotation(secured)")
    public void callMethod(Secured secured) {
    }

    @Around("callMethod(secured)")
    public Object around(ProceedingJoinPoint pjp, Secured secured) throws Throwable {
        LOGGER.info("{} lock is {}", pjp.getSignature().toLongString(), secured.isLocked());
        return pjp.proceed();
    }
}
