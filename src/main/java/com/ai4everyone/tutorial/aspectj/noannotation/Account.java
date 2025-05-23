package com.ai4everyone.tutorial.aspectj.noannotation;

public class Account {

    private int deposit;

    public Account(int deposit) {
        this.deposit = deposit;
    }

    public int getDeposit() {
        return deposit;
    }

    public void setDeposit(int deposit) {
        this.deposit = deposit;
    }

    public boolean withdraw(int debt) {
        if (deposit < debt) {
            return false;
        }
        deposit -= debt;
        return true;
    }
}
