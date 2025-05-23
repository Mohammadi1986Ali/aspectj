package com.ai4everyone.tutorial.aspectj.noannotation;

import com.ai4everyone.tutorial.aspectj.noannotation.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public aspect AccountAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(AccountAspect.class);

    pointcut callWithdraw(int dept, Account account):
            call(boolean com.ai4everyone.tutorial.aspectj.noannotation.Account.withdraw(int)) && target(account) && args(dept);

    before(int dept, Account account) : callWithdraw(dept, account) {
        LOGGER.info("Account#getDeposit --> Deposit before withdraw: {}", account.getDeposit());
        LOGGER.info("Dept: {}", dept);
    }

    boolean around(int dept, Account account) : callWithdraw(dept, account) {
        if (account.getDeposit() < dept) {
            LOGGER.info("Withdrawal rejected!!!");
            return false;
        }
        return proceed(dept, account);
    }

    after(int dept, Account account) : callWithdraw(dept, account) {
        LOGGER.info("Account#getDeposit --> Deposit after withdraw: {}", account.getDeposit());
    }
}
