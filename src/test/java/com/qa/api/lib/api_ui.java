package com.qa.api.lib;

import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class api_ui {
    private String baseURI = null;

    public api_ui(String baseURI) {
        this.baseURI = baseURI;
        RestAssured.baseURI = baseURI;
    }

    public Response get(String endpoint) {
        RequestSpecification requestSpec = RestAssured.given();
        return requestSpec.get(endpoint);
    }

    public Response post(String endpoint, HashMap<String, String> body) {
        RequestSpecification requestSpec = RestAssured.given();
        requestSpec.header("Content-Type", "application/json");
        requestSpec.body(new Gson().toJson(body));
        return requestSpec.post(endpoint);
    }

    public Response put(String endpoint, HashMap<String, String> body) {
        RequestSpecification requestSpec = RestAssured.given();
        requestSpec.header("Content-Type", "application/json");
        requestSpec.body(new Gson().toJson(body));
        return requestSpec.put(endpoint);
    }

    public Response delete(String endpoint) {
        RequestSpecification requestSpec = RestAssured.given();
        return requestSpec.delete(endpoint);
    }



}
