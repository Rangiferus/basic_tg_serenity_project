package com.company.basic_project.actions;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actions.EnterValue;

public class EnterValueIntoElement extends EnterValue {
    private final WebElementFacade element;

    public EnterValueIntoElement(WebElementFacade element, CharSequence... theText) {
        super(theText);
        this.element = element;
    }

    @Step("{0} enters #theTextAsAString into #element")
    public <T extends Actor> void performAs(T theUser) {
        this.textValue().ifPresent((text) -> {
//            this.element.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.chord(Keys.DELETE));
            this.element.clear();
            this.element.type(text);
        });
        if (this.getFollowedByKeys().length > 0) {
            this.element.sendKeys(this.getFollowedByKeys());
        }
    }

}
