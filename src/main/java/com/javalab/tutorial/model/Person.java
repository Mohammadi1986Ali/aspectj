package com.javalab.tutorial.model;

public class Person {

    private int deposit;

    public Person(int deposit) {
        this.deposit = deposit;
    }

    public int getDeposit() {
        return deposit;
    }

    public void setDeposit(int deposit) {
        this.deposit = deposit;
    }

    public boolean handleDebt(int debt) {
        if (deposit < debt) {
            return false;
        }
        deposit -= debt;
        return true;
    }
}
