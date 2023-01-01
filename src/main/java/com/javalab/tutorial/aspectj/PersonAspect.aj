package com.javalab.tutorial.aspectj;

import com.javalab.tutorial.model.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public aspect PersonAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(PersonAspect.class);

    pointcut personAspect(int dept, Person person):
            call(boolean com.javalab.tutorial.model.Person.handleDebt(int)) && target(person) && args(dept);

    before(int dept, Person person) : personAspect(dept, person) {
        LOGGER.info("Person deposit is: {}", person.getDeposit());
        LOGGER.info("Dept value is: {}", dept);
    }

    boolean around(int dept, Person person) : personAspect(dept, person) {
        if (person.getDeposit() < dept) {
            LOGGER.info("Withdrawal rejected!");
            return false;
        }
        return proceed(dept, person);
    }

    after(int dept, Person person) : personAspect(dept, person) {
        LOGGER.info("Person new deposit is: {}", person.getDeposit());
    }
}