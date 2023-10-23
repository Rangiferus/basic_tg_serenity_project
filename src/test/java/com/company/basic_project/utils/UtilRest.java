package com.company.basic_project.utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import lombok.SneakyThrows;
import org.json.JSONObject;

public class UtilRest {

    private static String logLevel;

    public static String getLogLevel() {
        return logLevel;
    }


    public static void setLogLevelDebug() {
        logLevel = "DEBUG";
        RestAssured.replaceFiltersWith((reqSpec, respSpec, filterContext) -> { // SerenityRest.filters() adds logs to already existing
            System.out.println("--> " + reqSpec.getMethod() + " " + reqSpec.getURI());
            printRequestBody(reqSpec);
            Response response = filterContext.next(reqSpec, respSpec);
            System.out.println("<-- Response " + response.getStatusCode());
            response.getBody().prettyPrint();
            return response;
        });
    }

    public static void setLogLevelTrace() {
        logLevel = "TRACE";
        RestAssured.replaceFiltersWith((reqSpec, respSpec, filterContext) -> { // SerenityRest.filters() adds logs to already existing
            System.out.println("--> " + reqSpec.getMethod() + " " + reqSpec.getURI());
            System.out.println("Headers: " + reqSpec.getHeaders());
            System.out.println("Cookies: " + reqSpec.getCookies());
            printRequestBody(reqSpec);
            Response responce = filterContext.next(reqSpec, respSpec);
            System.out.println("<-- Response " + responce.getStatusCode());
            System.out.println("Headers: " + responce.getHeaders());
            System.out.println("Time: " + responce.getTime());
            responce.getBody().prettyPrint();
            return responce;
        });
    }

    public static void setLogLevelInfo() {
        logLevel = "INFO";
        RestAssured.replaceFiltersWith((reqSpec, respSpec, filterContext) -> {
            System.out.print("--> " + reqSpec.getMethod() + " " + reqSpec.getURI());
            Response responce = filterContext.next(reqSpec, respSpec);
            System.out.println(" Response code: " + responce.getStatusCode());
            return responce;
        });
    }

    public static void setLogLevelNone() {
        logLevel = "NONE";
        RestAssured.replaceFiltersWith((reqSpec, respSpec, filterContext) -> filterContext.next(reqSpec, respSpec));
    }

    public static void setLogLevel(String logLevel) {
        switch (logLevel.toUpperCase()) {
            case "NONE":
                setLogLevelNone();
                break;
            case "TRACE":
                setLogLevelTrace();
                break;
            case "DEBUG":
                setLogLevelDebug();
                break;
            case "INFO":
                setLogLevelInfo();
                break;
        }
    }


    @SneakyThrows
    private static void printRequestBody(FilterableRequestSpecification reqSpec) {
        if (reqSpec.getBody() != null) {
            if (UtilJson.isJsonValid(reqSpec.getBody().toString())) {
                System.out.println(new JSONObject(reqSpec.getBody().toString()).toString(4));
            } else {
                System.out.println(reqSpec.getBody().toString());
            }
        }

    }


}
