package com.company.basic_project.step_definitions;

import com.company.basic_project.exceptions.ErrorTestScript;
import io.cucumber.java.en.When;
import net.serenitybdd.core.Serenity;
import org.assertj.core.api.Assertions;

public class Steps extends BaseSteps {


    @When("step to {word}")
    public void step_status(String status) {
        switch (status) {
            case "pass":
                log.info("Step passed");
                break;
            case "fail":
                Assertions.fail("Step failed");
            case "break":
                throw new RuntimeException("Step broken");
            default:
                throw new ErrorTestScript("Invalid step status");
        }

    }

    @When("do nothing")
    public void do_nothing() {
        Serenity.reportThat("Passing without doing anything", () -> {});
    }

    @When("pass assertion")
    public void pass_assertion() {
        Assertions.assertThat(2).isEqualTo(2);
    }
}
