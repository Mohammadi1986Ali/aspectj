package com.ai4everyone.tutorial.aspectj.noannotation;

import ch.qos.logback.classic.Level;
import com.ai4everyone.tutorial.aspectj.MemoryAppender;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    private static MemoryAppender memoryAppender;
    private static final String LOGGER_NAME = "com.ai4everyone.tutorial";
    private static final String WITHDRAWAL_REJECTED_MSG = "Withdrawal rejected!!!";

    @BeforeAll
    public static void setUp() {
        memoryAppender = new MemoryAppender(LOGGER_NAME, Level.DEBUG);
        memoryAppender.config();
        memoryAppender.start();
    }

    @AfterAll
    public static void cleanUp() {
        memoryAppender.reset();
        memoryAppender.stop();
    }

    @Test
    public void givenAccount_whenWithdrawCalled_thenAspectShouldLoggedCorrectly() {
        Account account = new Account(100);
        account.withdraw(150);

        assertEquals(1, memoryAppender.search(WITHDRAWAL_REJECTED_MSG, Level.INFO).size());
    }
}
