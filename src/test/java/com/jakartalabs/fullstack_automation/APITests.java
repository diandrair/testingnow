
package com.jakartalabs.fullstack_automation;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import com.google.gson.JsonObject;
import com.jakartalabs.baseapi.BaseAPITest;
import com.jakartalabs.utils.APIActions;
import com.jakartalabs.utils.DataUtils;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class APITests extends BaseAPITest {

String authtoken = "token not found";

@Test(priority = 1)
public void testloginAPI() {

String loginPayload = DataUtils.getDataFromExcel("Payloads", "LoginPayload");

Response loginResponse = RestAssured.given().spec(commonRequestSpec).body(loginPayload).when()
.post(APIEndpoints.loginAPI);

APIActions.verifyAPIResponseDetails(loginResponse);

authtoken = APIActions.getResponseDataUsingJsonPath(loginResponse, JsonPaths.authToken);

assertEquals(authtoken.length() > 10, true);

}

@Test(priority = 1)
public void testloginAPIWithIncorrectCredentials() {

String loginPayload = DataUtils.getDataFromExcel("Payloads", "LoginPayload");
JsonObject jsonObject = APIActions.updateJsonFieldValue(loginPayload, "email", "897456321");

Response loginResponse = RestAssured.given().spec(commonRequestSpec).body(jsonObject).when()
.post(APIEndpoints.loginAPI);

assertEquals(loginResponse.getStatusCode(), 422);

}

@Test(priority = 2)
public void testDashboardAPI() {

Response dashboardResponse = RestAssured.given().spec(commonRequestSpec).formParam("status", "invited")
.header("authtoken", authtoken).when().get(APIEndpoints.dashboardAPI);

APIActions.verifyAPIResponseDetails(dashboardResponse);

int buildCardCount = APIActions.getResponseDataUsingJsonPath(dashboardResponse,
JsonPaths.completedBuildCardCount);

assertEquals(buildCardCount, 5);

}

}
