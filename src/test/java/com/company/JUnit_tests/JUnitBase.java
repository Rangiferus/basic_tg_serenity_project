package com.company.JUnit_tests;

import com.company.basic_project.actors.CastOfCustomers;
import com.company.basic_project.config.TestConfig;
import com.company.basic_project.utils.UtilRest;
import net.serenitybdd.screenplay.actors.OnStage;
import net.thucydides.model.environment.SystemEnvironmentVariables;
import net.thucydides.model.util.EnvironmentVariables;
import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JUnitBase {
    // ---> Set shop and environment in serenity.conf

    protected final Logger log = LoggerFactory.getLogger(this.getClass());
    protected String actorName = "Daniel";
    protected EnvironmentVariables env;

    @Before
    public void initJUnitTest() {
        env = SystemEnvironmentVariables.createEnvironmentVariables();
        new TestConfig(this.env);
        OnStage.setTheStage(new CastOfCustomers(env));
        UtilRest.setLogLevelDebug();
        log.info("Settings:\n\tEnvironment: " + TestConfig.getTestedEnv() + "\n\tShop: " + TestConfig.getShopId() + "\n\tActor: " + actorName + "\n\n");
    }


}
