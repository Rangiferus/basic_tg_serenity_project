package com.company.basic_project.exceptions;

public class ErrorTestConfig extends RuntimeException {
    public ErrorTestConfig(String msg) {
        super(msg);
    }
}
