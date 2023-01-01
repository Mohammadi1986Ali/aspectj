package com.javalab.tutorial.aspectj.annotationbase;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class SecuredMethodTest {

    private static SecuredMethod securedMethod;

    @BeforeAll
    public static void setUp() {
        securedMethod = new SecuredMethod();
    }

    @Test
    public void shouldLoggedByAspect_whenMethodsOfSecuredMethodCalled() {
        securedMethod.lockedMethod();
    }
}