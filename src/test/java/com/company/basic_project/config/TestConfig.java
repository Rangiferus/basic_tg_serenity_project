package com.company.basic_project.config;

import com.company.basic_project.exceptions.ErrorTestConfig;
import com.company.basic_project.model.Shop;
import net.serenitybdd.model.environment.EnvironmentSpecificConfiguration;
import net.serenitybdd.model.environment.UndefinedEnvironmentVariableException;
import net.thucydides.model.util.EnvironmentVariables;

import java.util.Arrays;
import java.util.List;

public class TestConfig {

    // Automatically injected by Serenity
    private static EnvironmentVariables env;
    public static final String CLOUD_CROSS_BROWSER_TESTING = "crossbrowsertesting";
    public static final String CLOUD_LAMBDA_TEST = "lambdatest";
    public static final String CLOUD_SAUCE_LABS = "saucelabs";
    public static final String CLOUD_BROWSER_STACK = "browserstack";

    public TestConfig(EnvironmentVariables env) {
        TestConfig.env = env;
    }

    public static void readEnv(EnvironmentVariables env) {
        TestConfig.env = env;
    }

    private static String getShopAndEnvSpecific(String property) {
        String value = EnvironmentSpecificConfiguration.from(env).getProperty(env.getProperty("shop") + "." + property);
        if (value.equals("N/A")) {
            throw new ErrorTestConfig("Missing configuration property value for " + property + ". Set it in serenity.conf.");
        }
        return value;
    }

    private static String getEnvSpecific(String property) {
        String value = EnvironmentSpecificConfiguration.from(env).getProperty(property);
        if (value.equals("N/A")) {
            throw new ErrorTestConfig("Missing configuration property value for " + property + ". Set it in serenity.conf.");
        }
        return value;
    }

    public static String getProperty(String propName) {
        return EnvironmentSpecificConfiguration.from(env).getProperty(propName);
    }

    public static String getTestedEnv() {
        return env.getProperty("environment");
    }

    public static boolean testMobileApp() {
        return Boolean.parseBoolean(env.getProperty("test.on.mobile.device")) && Boolean.parseBoolean(env.getProperty("test.mobile.app"));
    }

    public static boolean testOnMobileDevice() {
        return Boolean.parseBoolean(env.getProperty("test.on.mobile.device"));
    }

    public static String getWebDriver() {
        return env.getProperty("webdriver.driver");
    }

    public static String remoteCloudProvider() {
        return env.getProperty("remote.cloud.provider");
    }

    public static String getShopId() {
        try {
            return Shop.get(env.getProperty("shop")).shopId;
        } catch (NullPointerException e) {
            throw new UndefinedEnvironmentVariableException("Unknown 'shop': " + env.getProperty("shop"));
        }
    }

    public static String getTestConfigShopId() {
        try {
            return Shop.get(env.getProperty("shop")).testConfigShopId;
        } catch (NullPointerException e) {
            throw new UndefinedEnvironmentVariableException("Unknown 'shop': " + env.getProperty("shop"));
        }
    }

    public static Shop getShop() {
        return Shop.get(getTestConfigShopId());
    }

    public static String getTestProductImageLocation() {
        return env.getProperty("testProduct.image.location");
    }

    public static String getBaseUrl() {
        return getShopAndEnvSpecific("url");
    }

    public static String getCustomerEmail(String userName) {
        return getShopAndEnvSpecific("users." + userName + ".email");
    }

    public static String getCustomerPassword(String userName) {
        return getShopAndEnvSpecific("users." + userName + ".password");
    }

    public static String getCustomerWarehouse(String userName) {
        return getShopAndEnvSpecific("users." + userName + ".warehouse");
    }

    public static String getCustomerNumber(String userName) {
        return getShopAndEnvSpecific("users." + userName + ".customer_number");
    }

    public static String getAzureAPIBaseUrl() {
        return EnvironmentSpecificConfiguration.from(env).getProperty("azure_rest_api.baseurl");
    }

    public static String getBackendAPIBaseUrl() {
        return getEnvSpecific("backend_api.baseurl");
    }

    public static String getMarketingAppAPIBaseUrl() {
        return getEnvSpecific("marketing_app_api.baseurl");
    }

    public static String getBackendRestSubscriptionKey() {
        return getEnvSpecific("backend.subscription_key");
    }

    public static String getMarketingAppRestSubscriptionKey() {
        return getEnvSpecific("marketing_app.subscription_key");
    }

    public static String getCommercetoolsAPIBaseUrl() {
        return getEnvSpecific("commercetools.baseurl");
    }

    public static String getCommercetoolsAuthUrl() {
        return getEnvSpecific("commercetools.authurl");
    }

    public static String getCommercetoolsProjectKey() {
        return getShopAndEnvSpecific("commercetools.project_key");
    }

    public static String getCommercetoolsClientId() {
        return getShopAndEnvSpecific("commercetools.client_id");
    }

    public static String getCommercetoolsSecret() {
        return getShopAndEnvSpecific("commercetools.secret");
    }

    public static String getServiceBusConnectionString() {
        return getEnvSpecific("servicebus.connection_string");
    }

    public static String getServiceBusTopicImportShelfPrices() {
        return getEnvSpecific("servicebus.topic.import_prices");
    }

    public static String getServiceBusTopicImportCustomerPrices() {
        return getEnvSpecific("servicebus.topic.import_customer_prices");
    }

    public static String getServiceBusTopicImportDeliveryDates() {
        return getEnvSpecific("servicebus.topic.import_delivery_dates");
    }

    public static String getServiceBusTopicImportProductTypes() {
        return getShopAndEnvSpecific("servicebus.topic.import_product_types");
    }

    public static String getServiceBusTopicImportProducts() {
        return getShopAndEnvSpecific("servicebus.topic.import_products");
    }

    public static String getServiceBusTopicImportCustomers() {
        return getEnvSpecific("servicebus.topic.import_customers");
    }

    public static String templateLocation() {
        return env.getProperty("payloads");
//        return System.getProperty("user.dir") + "/src/test/resources/payloads";
        // How to get location of the calling class:
        // return System.getProperty("user.dir") + "/src/test/java/" + this.getClass().getPackage().getName().replaceAll("\\.", "/");
    }

    public static String getAPIlogLevel() {
        return env.getProperty("log.level.api");
    }

    public static String getConsolelogLevel() {
        return env.getProperty("log.level.console");
    }

    public static boolean getLogoutAfterScenario() {
        return Boolean.parseBoolean(env.getProperty("testsuite.logout_after_each_scenario"));
    }

    public static boolean getAllScenariosInOneSession() {
        return !env.getProperty("serenity.restart.browser.for.each").equalsIgnoreCase("scenario");
    }

    public static String getPriceDbConnectionString() {
        return getShopAndEnvSpecific("price_db_connection_string");
    }

    public static String getPriceDbUser() {
        return getShopAndEnvSpecific("price_db_user");
    }

    public static String getPriceDbPassword() {
        return getShopAndEnvSpecific("price_db_password");
    }

    public static String getPangeaDataDbConnectionString() {
        return getEnvSpecific("pangeadata_db_connection_string");
    }

    public static String getPangeaDataDbUser() {
        return getEnvSpecific("pangeadata_db_user");
    }

    public static String getPangeaDataDbPassword() {
        return getEnvSpecific("pangeadata_db_password");
    }


    public static String getAzureSearchIndexUrl() { return getShopAndEnvSpecific("azure.search_index.url"); }

    public static String getAzureSearchIndexApiKey() { return getEnvSpecific("azure.search_index.apikey"); }

    public static boolean isProdEnv() {
        return getTestedEnv().equalsIgnoreCase("prod");
    }

    public static List<String> getWebShopUsernames() {
        return  Arrays.asList(env.getProperty("usernames.webshops").split(",\\s*", -1));
    }

    public static List<String> getMarketingAppUsernames() {
        return  Arrays.asList(env.getProperty("usernames.marketing_app").split(",\\s*", -1));
    }

    public static List<String> getNotAuthenticatedUsernames() {
        return  Arrays.asList(env.getProperty("usernames.not_authenticated").split(",\\s*", -1));
    }

    public static boolean isRunningFromAzurePipeline() {
        return System.getenv("BUILD_BUILDNUMBER") != null;
    }

}