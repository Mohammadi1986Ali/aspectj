package com.ai4everyone.tutorial.aspectj.annotation;

import ch.qos.logback.classic.Level;
import com.ai4everyone.tutorial.aspectj.MemoryAppender;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SecuredMethodAspectTest {
    private static MemoryAppender memoryAppender;
    private static SecuredMethod securedMethod;
    private static final String LOGGER_NAME = "com.ai4everyone.tutorial";
    private static final String LOCKED_METHOD_MSG = "lock is true";
    private static final String UNLOCKED_METHOD_MSG = "lock is false";

    @BeforeAll
    public static void setUp() {
        memoryAppender = new MemoryAppender(LOGGER_NAME, Level.DEBUG);
        memoryAppender.config();
        memoryAppender.start();
        securedMethod = new SecuredMethod();
    }

    @AfterAll
    public static void cleanUp() {
        memoryAppender.reset();
        memoryAppender.stop();
    }

    @Test
    public void shouldLoggedByAspect_whenMethodsOfSecuredMethodCalled() {
        securedMethod.lockedMethod();
        assertAll(
                () -> assertEquals(1, memoryAppender.search(LOCKED_METHOD_MSG, Level.INFO).size()),
                () -> assertTrue(memoryAppender.search(UNLOCKED_METHOD_MSG, Level.INFO).isEmpty())
        );
    }
}
