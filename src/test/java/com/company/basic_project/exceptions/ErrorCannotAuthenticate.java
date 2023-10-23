package com.company.basic_project.exceptions;

public class ErrorCannotAuthenticate extends RuntimeException {
    public ErrorCannotAuthenticate(String actorName) {
        super("The actor " + actorName + " does not have the ability to authenticate");
    }
}
