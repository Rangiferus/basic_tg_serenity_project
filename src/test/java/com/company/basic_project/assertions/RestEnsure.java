package com.company.basic_project.assertions;

import io.restassured.response.ValidatableResponse;
import net.serenitybdd.screenplay.rest.interactions.Ensure;

import java.util.function.Consumer;

public class RestEnsure extends Ensure {

    public RestEnsure(String description, Consumer<ValidatableResponse> check) {
        super(description, check);
    }
}
