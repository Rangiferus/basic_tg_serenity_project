package com.company.basic_project.actors;

import com.company.basic_project.abilities.Authenticate;
import com.company.basic_project.config.TestConfig;
import net.serenitybdd.screenplay.Ability;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.thucydides.model.util.EnvironmentVariables;

/**
 * The Cast is a factory we use to provide actors for our scenarios.
 * Each actor is given the ability to browse the web and query backend REST API using RestAssured.
 * We assign this cast to a scenario in the SettingTheStage class.
 * <p>
 * There's no need to specify BrowseTheWeb ability if this class extends OnlineCast.
 */
public class CastOfCustomers extends OnlineCast {

    public CastOfCustomers(EnvironmentVariables environmentVariables) {
        super(); // doesn't seem to be required
    }

    @Override
    public Actor actorNamed(String actorName, Ability... abilities) {
        Actor actor = super.actorNamed(actorName, abilities);
        if (!TestConfig.getNotAuthenticatedUsernames().contains(actorName)) {
            actor.can(Authenticate.withCredentials(
                            TestConfig.getCustomerNumber(actor.getName()),
                            TestConfig.getCustomerPassword(actor.getName())));
        }
        if (TestConfig.getWebShopUsernames().contains(actorName)) {
            actor.can(CallAnApi
                    .at(TestConfig.getBackendAPIBaseUrl())
            );
        }
        if (TestConfig.getMarketingAppUsernames().contains(actorName) || TestConfig.getNotAuthenticatedUsernames().contains(actorName)) {
            actor.can(CallAnApi
                    .at(TestConfig.getMarketingAppAPIBaseUrl())
            );
        }
        return actor;
    }
}
