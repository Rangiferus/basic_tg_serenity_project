package com.company.basic_project.tasks;

import com.company.basic_project.config.TestConfig;
import com.company.basic_project.model.Shop;
import net.thucydides.model.util.EnvironmentVariables;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseTask {
    protected final Logger log = LoggerFactory.getLogger(this.getClass());
    protected static EnvironmentVariables env;
    protected static final Shop shop = TestConfig.getShop();

}
