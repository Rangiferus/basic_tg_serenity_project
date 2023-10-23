package com.company.basic_project.actions;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actions.EnterValue;
import net.serenitybdd.screenplay.targets.Target;

public class EnterValueIntoTarget extends EnterValue {
    private final Target target;

    public EnterValueIntoTarget(Target target, CharSequence... theText) {
        super(theText);
        this.target = target;
    }

    @Step("{0} enters #theTextAsAString into #target")
    public <T extends Actor> void performAs(T theUser) {
        this.textValue().ifPresent((text) -> {
//            this.target.resolveFor(theUser).sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.chord(Keys.DELETE));
            this.target.resolveFor(theUser).clear();
            this.target.resolveFor(theUser).type(text);
        });
        if (super.getFollowedByKeys().length > 0) {
            this.target.resolveFor(theUser).sendKeys(this.getFollowedByKeys());
        }

    }
}
