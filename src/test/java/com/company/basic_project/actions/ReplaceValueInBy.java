package com.company.basic_project.actions;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.EnterValue;
import net.thucydides.core.webdriver.shadow.ByShadow;
import org.openqa.selenium.Keys;

public class ReplaceValueInBy extends EnterValue {
    private final ByShadow locator;
    private final String locatorNames;

//    protected WebElement resolveFor(Actor theUser) {
//        return WebElementLocator.forLocators(this.locator).andActor(theUser);
//    }

    public ReplaceValueInBy(ByShadow locator, CharSequence theText) {
        super(theText);
        this.locator = locator;
        this.locatorNames = locator.toString();
    }

    @Step("{0} enters #theTextAsAString into #locatorNames")
    public <T extends Actor> void performAs(T theUser) {
        this.textValue().ifPresent((text) -> {
            BrowseTheWeb.as(theUser).find(this.locator).sendKeys(Keys.HOME, Keys.chord(Keys.SHIFT, Keys.END), text[0], Keys.TAB);
        });
    }



}
