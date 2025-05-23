package com.ai4everyone.tutorial.aspectj.annotation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SecuredMethod {

    private static final Logger LOGGER = LoggerFactory.getLogger(SecuredMethod.class);

    @Secured(isLocked = true)
    public void lockedMethod() {
        LOGGER.info("SecuredMethod#lockedMethod");
    }

    @Secured(isLocked = false)
    public void unlockedMethod() {
        LOGGER.info("SecuredMethod#unlockedMethod");
    }
}
