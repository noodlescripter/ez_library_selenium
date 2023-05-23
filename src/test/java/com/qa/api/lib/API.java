package com.qa.api.lib;

import com.google.gson.Gson;
import com.qa.lib.ThrowError;
import groovy.transform.stc.SecondParam;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.security.PublicKey;
import java.util.HashMap;


public class API {
    private String baseURI = null;
    private String token = null;

    public API(String baseURI, HashMap<String, String> body , String endpoint) {
        this.baseURI = baseURI;
        RestAssured.baseURI = baseURI;
        RequestSpecification requestSpec = RestAssured.given();
        requestSpec.header("Content-Type", "application/json");
        requestSpec.body(new Gson().toJson(body));
        Response response = requestSpec.post(endpoint);
        token = response.jsonPath().getString("access_token");
    }

    public Response get(String endpoint) {
        RequestSpecification requestSpec = RestAssured.given();
        requestSpec.header("Authorization", "Bearer " + token);
        return requestSpec.get(endpoint);
    }

    public Response post(String endpoint, HashMap<String, String> body) {
        RequestSpecification requestSpec = RestAssured.given();
        requestSpec.header("Content-Type", "application/json");
        requestSpec.header("Authorization", "Bearer " + token);
        requestSpec.body(new Gson().toJson(body));
        return requestSpec.post(endpoint);
    }

    public Response put(String endpoint, String body) {
        RequestSpecification requestSpec = RestAssured.given();
        requestSpec.header("Content-Type", "application/json");
        requestSpec.header("Authorization", "Bearer " + token);
        requestSpec.body(body);
        return requestSpec.put(endpoint);
    }

    public Response delete(String endpoint) {
        RequestSpecification requestSpec = RestAssured.given();
        requestSpec.header("Authorization", "Bearer " + token);
        return requestSpec.delete(endpoint);
    }
}
