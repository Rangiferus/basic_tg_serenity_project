package com.company.basic_project.tasks;

import net.serenitybdd.markers.IsSilent;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class Log extends BaseTask implements Task, IsSilent {
    String logLevel;
    String text;

    Log(String logLevel, String text) {
        this.logLevel = logLevel;
        this.text = text;
    }

    public static Log info(String text) {
        return instrumented(Log.class, "INFO", text);
    }

    public static Log debug(String text) {
        return instrumented(Log.class, "DEBUG", text);
    }

    public static Log warn(String text) {
        return instrumented(Log.class, "WARN", text);
    }

    public static Log error(String text) {
        return instrumented(Log.class, "ERROR", text);
    }

    public <T extends Actor> void performAs(T actor) {
        switch (logLevel) {
            case "INFO":
                log.info(text);
                break;
            case "DEBUG":
                log.debug(text);
                break;
            case "WARN":
                log.warn(text);
                break;
            case "ERROR":
                log.error(text);
                break;
        }
    }
}
