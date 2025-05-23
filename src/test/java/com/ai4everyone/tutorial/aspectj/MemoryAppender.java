package com.ai4everyone.tutorial.aspectj;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class MemoryAppender extends ListAppender<ILoggingEvent> {

    private String loggerName;
    private Level logLevel;

    public MemoryAppender(String loggerName, Level logLevel) {
        this.loggerName = loggerName;
        this.logLevel = logLevel;
    }

    public void reset() {
        this.list.clear();
    }

    public boolean contains(String string, Level level) {
        return this.list.stream()
                .anyMatch(event -> event.toString().contains(string) && event.getLevel().equals(level));
    }

    public int countEventsForLogger(String loggerName) {
        return (int) this.list.stream()
                .filter(event -> event.getLoggerName().contains(loggerName))
                .count();
    }

    public List<ILoggingEvent> search(String string) {
        return this.list.stream()
                .filter(event -> event.toString().contains(string))
                .collect(Collectors.toList());
    }

    public List<ILoggingEvent> search(String string, Level level) {
        return this.list.stream()
                .filter(event -> event.toString().contains(string) && event.getLevel().equals(level))
                .collect(Collectors.toList());
    }

    public List<ILoggingEvent> getLoggedEvents() {
        return Collections.unmodifiableList(this.list);
    }

    public void config() {
        Logger logger = (Logger) LoggerFactory.getLogger(this.loggerName);
        this.setContext((LoggerContext) LoggerFactory.getILoggerFactory());
        logger.setLevel(this.logLevel);
        logger.addAppender(this);
    }
}