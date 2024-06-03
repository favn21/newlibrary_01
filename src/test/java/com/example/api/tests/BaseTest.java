package com.example.api.tests;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.junit.BeforeClass;

public abstract class BaseTest {
    protected static String token;

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = "http://localhost:8080";

        token = RestAssured.given()
                .contentType("application/json")
                .body("{ \"login\": \"user\", \"password\": \"password\" }")
                .get("/auth/login")
                .jsonPath()
                .getString("token");
    }

    protected RequestSpecification givenAuth() {
        return RestAssured.given().header("Authorization", "Bearer " + token);
    }
}
