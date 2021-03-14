package com.jakartalabs.utils;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class APIActions {

public static void verifyAPIResponseDetails(Response response) {
System.out.println("response code:" + response.getStatusCode());
System.out.println("reponse header:" + response.getHeader("Server"));
System.out.println("reponse time:" + response.timeIn(TimeUnit.MILLISECONDS));
System.out.println("response body:" + response.getBody().asString());
assertEquals(String.valueOf(response.getStatusCode()).startsWith("20"), true);
assertEquals(response.getHeader("Server").contains("Ubuntu"), true);
assertEquals(response.timeIn(TimeUnit.MILLISECONDS) < 10000, true);
}

public static <T> T getResponseDataUsingJsonPath(Response response, String jsonPath) {
JsonPath jPath = response.jsonPath();
System.out.println(jsonPath + " value is:" + jPath.get(jsonPath));

return jPath.get(jsonPath);
}

public static JsonObject updateJsonFieldValue(String payload, String jsonField, String value) {
JsonObject jsonObject = new JsonParser().parse(payload).getAsJsonObject();

jsonObject.remove(jsonField);
jsonObject.addProperty(jsonField, value);
return jsonObject;
}

}
