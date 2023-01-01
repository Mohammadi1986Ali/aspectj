package com.javalab.tutorial.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    private Person person;

    @BeforeEach
    public void setUp() {
        person = new Person(100);
    }

    @Test
    public void givenDept10_whenPersonDepositIs100_thenHandleDeptShouldReturnTrueAndNewDepositIs90() {
        assertTrue(person.handleDebt(10));
        assertEquals(90, person.getDeposit());
    }

    @Test
    public void givenDept200_whenPersonDepositIs100_thenHandleDeptShouldReturnFalseAndNewDepositIs100() {
        assertFalse(person.handleDebt(200));
        assertEquals(100, person.getDeposit());
    }
}