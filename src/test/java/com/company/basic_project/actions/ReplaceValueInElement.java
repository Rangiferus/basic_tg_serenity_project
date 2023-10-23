package com.company.basic_project.actions;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actions.EnterValue;
import org.openqa.selenium.Keys;

public class ReplaceValueInElement extends EnterValue {
    private final WebElementFacade element;

    public ReplaceValueInElement(WebElementFacade element, CharSequence theText) {
        super(theText);
        this.element = element;
    }

    @Step("{0} enters #theTextAsAString into #element")
    public <T extends Actor> void performAs(T theUser) {
        this.textValue().ifPresent((text) -> {
            this.element.sendKeys(Keys.HOME, Keys.chord(Keys.SHIFT, Keys.END), text[0], Keys.TAB);
        });
    }

}
