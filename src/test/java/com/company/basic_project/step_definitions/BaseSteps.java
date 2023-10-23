package com.company.basic_project.step_definitions;

import lombok.SneakyThrows;
import net.thucydides.model.util.EnvironmentVariables;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class BaseSteps {
    protected final Logger log = LoggerFactory.getLogger(this.getClass());
    protected EnvironmentVariables env;

    @SneakyThrows
    public static void sleep(int seconds) {
        TimeUnit.SECONDS.sleep(seconds);
    }

    @SneakyThrows
    public static void sleepMillis(int milliSeconds) {
        TimeUnit.MILLISECONDS.sleep(milliSeconds);
    }

}
