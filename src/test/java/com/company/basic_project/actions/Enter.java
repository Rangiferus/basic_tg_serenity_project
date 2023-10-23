package com.company.basic_project.actions;

import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.EnterValue;
import net.serenitybdd.screenplay.actions.EnterValueIntoBy;
import net.serenitybdd.screenplay.targets.Target;
import net.thucydides.core.webdriver.shadow.ByShadow;
import org.openqa.selenium.By;

import java.util.Arrays;

/**
 * A replacement for the standard Serenity 3.2.0 Enter class as a workaround for a Chrome bug.
 */
public class Enter {
    private final CharSequence[] theText;

    public Enter(CharSequence... theText) {
        if (containsOnlyNullValues(theText)) {
            this.theText = new CharSequence[]{""};
        } else {
            this.theText = theText;
        }

    }

    private boolean containsOnlyNullValues(CharSequence[] theText) {
        return theText.length == 1 && theText[0] == null;
    }

    public static Enter theValue(CharSequence... text) {
        return new Enter(text);
    }

    public EnterValue into(String cssOrXpathForElement) {
        return Tasks.instrumented(EnterValueIntoTarget.class, Target.the(cssOrXpathForElement).locatedBy(cssOrXpathForElement), this.theText);
    }

    public EnterValue into(Target target) {
        return Tasks.instrumented(EnterValueIntoTarget.class, target, this.theText);
    }

    public EnterValue into(WebElementFacade element) {
        return Tasks.instrumented(EnterValueIntoElement.class, element, this.theText);
    }

    public EnterValue into(By... locators) {
        return Tasks.instrumented(EnterValueIntoBy.class, Arrays.asList(locators), this.theText);
    }
//
//    public EnterValue intoQuantityInput(Target target) {
//        return Tasks.instrumented(ReplaceValueInTarget.class, target, this.theText);
//    }

    public EnterValue intoQuantityInput(WebElementFacade element) {
        return Tasks.instrumented(ReplaceValueInElement.class, element, this.theText[0]);
    }

    public EnterValue intoQuantityInput(Target target) {
        return Tasks.instrumented(ReplaceValueInTarget.class, target, this.theText);
    }

    public EnterValue intoQuantityInput(ByShadow locator) {
        return Tasks.instrumented(ReplaceValueInBy.class, locator, this.theText[0]);
    }

}
