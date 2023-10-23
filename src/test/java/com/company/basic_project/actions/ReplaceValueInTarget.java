package com.company.basic_project.actions;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actions.EnterValue;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.Keys;

public class ReplaceValueInTarget extends EnterValue {
    private final Target target;

    public ReplaceValueInTarget(Target target, CharSequence... theText) {
        super(theText);
        this.target = target;
    }

    @Step("{0} enters #theTextAsAString into #target")
    public <T extends Actor> void performAs(T theUser) {
        this.textValue().ifPresent((text) -> {
            this.target.resolveFor(theUser).sendKeys(Keys.HOME, Keys.chord(Keys.SHIFT, Keys.END), text[0], Keys.TAB);
        });
    }

}
