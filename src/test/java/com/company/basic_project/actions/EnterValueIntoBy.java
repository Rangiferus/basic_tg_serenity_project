package com.company.basic_project.actions;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.model.collect.NewList;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actions.EnterValue;
import net.serenitybdd.screenplay.actions.WebElementLocator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class EnterValueIntoBy extends EnterValue {
    private final List<By> locators;
    private final String locatorNames;

    protected WebElement resolveFor(Actor theUser) {
        return WebElementLocator.forLocators(this.locators).andActor(theUser);
    }

    public EnterValueIntoBy(List<By> locators, CharSequence... theText) {
        super(theText);
        this.locators = NewList.copyOf(locators);
        this.locatorNames = locators.size() == 1 ? locators.get(0).toString() : locators.toString();
    }

    @Step("{0} enters #theTextAsAString into #locatorNames")
    public <T extends Actor> void performAs(T theUser) {
        this.textValue().ifPresent((text) -> {
//            this.resolveFor(theUser).sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.chord(Keys.DELETE));
            this.resolveFor(theUser).clear();
            this.resolveFor(theUser).sendKeys(text);
        });
        if (this.getFollowedByKeys() != null && this.getFollowedByKeys().length > 0) {
            this.resolveFor(theUser).sendKeys(this.getFollowedByKeys());
        }

    }



}
