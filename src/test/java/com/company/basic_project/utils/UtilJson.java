package com.company.basic_project.utils;

import com.company.basic_project.exceptions.ErrorTestScript;
import com.google.gson.GsonBuilder;
import com.jayway.jsonpath.*;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONValue;
import net.serenitybdd.screenplay.Question;

public class UtilJson {

    private static final Configuration JSON_PATH_CONF = Configuration.defaultConfiguration()
            .addOptions(Option.ALWAYS_RETURN_LIST)  // returns a list even if JSONpath returns a single value
            .addOptions(Option.REQUIRE_PROPERTIES); // raises PathNotFoundException when queries like [*].id not found
    // .jsonProvider(new JacksonJsonProvider()


    /*
     ArrayNode node = JsonPath.parse(json).read("$[?(@.groups[?(@.type == 'phase' && @.name == 'alpha')] empty false)]");
     node.size();
     node.get(0).get("attribute");
    */

    public static boolean jsonPathExists(String jsonStr, String jsonPath) {
        try {
            return ((JSONArray) JsonPath.using(JSON_PATH_CONF).parse(jsonStr).read(jsonPath)).size() > 0;
        } catch (InvalidPathException | IllegalArgumentException | NullPointerException e) {
            return false;
        }
    }

    public static Question<Boolean> attributeNotEmpty(String jsonStr, String jsonPath) {
        boolean result;
        Object attrValue;
        try {
            attrValue = getFirst(jsonStr, jsonPath);
            if (attrValue instanceof JSONArray && ((JSONArray) attrValue).size() == 0) {
                result = false;
            } else {
                result = attrValue != null;
            }
        } catch (com.jayway.jsonpath.PathNotFoundException e) {
            result = false;
        }
        boolean finalResult = result;
        return Question.about("JsonPath '" + jsonPath + "' exists and is not empty")
                .answeredBy(actor -> finalResult);
    }

    public static DocumentContext parseJson(String jsonStr) {
        return JsonPath.using(JSON_PATH_CONF).parse(jsonStr);
    }

    public static boolean isJsonValid(String jsonStr) {
        return JSONValue.isValidJsonStrict(jsonStr);
    }

    public static JSONArray getAll(String jsonStr, String jsonPath) {
        if (!jsonPathExists(jsonStr, jsonPath)) {
            throw new ErrorTestScript("Required JSONpath " + jsonPath + " not found in provided JSON string");
        }
        return JsonPath.using(JSON_PATH_CONF).parse(jsonStr).read(jsonPath);
    }

    public static Object getFirst(String jsonStr, String jsonPath) {
        return getAll(jsonStr, jsonPath).get(0);
    }

    public static double getAsDouble(String jsonStr, String jsonPath) {
        return Double.parseDouble(getAll(jsonStr, jsonPath).get(0).toString());
    }

    public static JSONArray getArray(String jsonStr, String jsonPath) {
        return JsonPath.using(JSON_PATH_CONF).parse(jsonStr).read(jsonPath + "[*]");
    }

    public static String getAsString(String jsonStr, String jsonPath) {
        Object jsonObj = getFirst(jsonStr, jsonPath);
        if (!(jsonObj instanceof String)) {
//            //return new Gson().toJson(jsonObj, jsonObj.getClass());
            return new GsonBuilder().setPrettyPrinting().create().toJson(jsonObj, jsonObj.getClass());
        }
        return jsonObj.toString();
    }

    // JsonFormatter.prettyPrint(str);
}
