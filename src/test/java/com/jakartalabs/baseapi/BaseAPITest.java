package com.jakartalabs.baseapi;

import com.google.gson.Gson;
import com.jakartalabs.utils.DataUtils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public abstract class BaseAPITest {

	protected RequestSpecification commonRequestSpec = new RequestSpecBuilder()
			.setBaseUri(DataUtils.getDataFromExcel("Config", "BaseUrlAPI")).setContentType("application/json").build()
			.log().all();

	protected Gson gson = new Gson();

}
