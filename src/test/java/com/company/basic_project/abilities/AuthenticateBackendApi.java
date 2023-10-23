package com.company.basic_project.abilities;

import com.company.basic_project.exceptions.ErrorCannotAuthenticate;
import net.serenitybdd.core.eventbus.Broadcaster;
import net.serenitybdd.screenplay.Ability;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.RefersToActor;
import net.serenitybdd.screenplay.events.ActorPerformanceEvent;

public class AuthenticateBackendApi implements Ability, RefersToActor {
    private Actor actor;
    private final String subscriptionKey;
    private final String shopId;

    protected AuthenticateBackendApi(String subscriptionKey, String shopId) {
        this.subscriptionKey = subscriptionKey;
        this.shopId = shopId;
        this.registerForEventNotification();
    }

    private void registerForEventNotification() {
        Broadcaster.getEventBus().register(this);
    }

    public static AuthenticateBackendApi withHeaders(String subscriptionKey, String shopId) {
        return new AuthenticateBackendApi(subscriptionKey, shopId);
    }

    // retrieves the Ability from an Actor within the Interaction
    public static AuthenticateBackendApi as(Actor actor) {
        // complain if someone's asking the impossible
        if (actor.abilityTo(AuthenticateBackendApi.class) == null) {
            throw new ErrorCannotAuthenticate(actor.getName());
        }
        return actor.abilityTo(AuthenticateBackendApi.class).asActor(actor);
    }

    private boolean messageIsForThisActor(ActorPerformanceEvent event) {
        return event.getName().equals(this.actor.getName());
    }

    @SuppressWarnings("unchecked")
    public <T extends Ability> T asActor(Actor actor) {
        this.actor = actor;
        return (T)this;
    }

    public String subscriptionKey() {
        return this.subscriptionKey;
    }

    public String shopId() {
        return this.shopId;
    }
}
