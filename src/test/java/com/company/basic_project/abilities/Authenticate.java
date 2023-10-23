package com.company.basic_project.abilities;

import com.company.basic_project.exceptions.ErrorCannotAuthenticate;
import net.serenitybdd.core.eventbus.Broadcaster;
import net.serenitybdd.screenplay.Ability;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.RefersToActor;
import net.serenitybdd.screenplay.events.ActorPerformanceEvent;

public class Authenticate implements Ability, RefersToActor {
    private Actor actor;
    private final String customerNumber;
    private final String password;

    protected Authenticate(String customerNumber, String password) {
        this.password = password;
        this.customerNumber = customerNumber;
        this.registerForEventNotification();
    }

    private void registerForEventNotification() {
        Broadcaster.getEventBus().register(this);
    }

    // instantiates the Ability and enables fluent DSL
    public static Authenticate withCredentials(String customerNumber, String password) {
        return new Authenticate(customerNumber, password);
    }

    // retrieves the Ability from an Actor within the Interaction
    public static Authenticate as(Actor actor) {
        // complain if someone's asking the impossible
        if (actor.abilityTo(Authenticate.class) == null) {
            throw new ErrorCannotAuthenticate(actor.getName());
        }
        return actor.abilityTo(Authenticate.class).asActor(actor);
    }

    private boolean messageIsForThisActor(ActorPerformanceEvent event) {
        return event.getName().equals(this.actor.getName());
    }

    @SuppressWarnings("unchecked")
    public <T extends Ability> T asActor(Actor actor) {
        this.actor = actor;
        return (T) this;
    }

    public String customerNumber() {
        return this.customerNumber;
    }

    public String password() {
        return this.password;
    }

}
