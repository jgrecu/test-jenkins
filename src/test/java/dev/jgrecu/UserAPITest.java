package dev.jgrecu;


import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class UserAPITest {
    @BeforeEach
    public void setup() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
//        RestAssured.basePath = "/api/v1";
    }

    @Test
    public void testGetUsers() {
        given()
                .header("Authorization", "Bearer YOUR_TOKEN")
                .when()
                .get("/users")
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    @Test
    public void testCreateUser() {
        String requestBody = "{\"name\":\"John Doe\",\"email\":\"john@example.com\"}";

        given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer YOUR_TOKEN")
                .body(requestBody)
                .when()
                .post("/users")
                .then()
                .statusCode(201)
                .body("name", equalTo("John Doe"))
                .body("id", notNullValue());
    }

    @Test
    public void testGetSpecificUser() {
        given()
                .header("Authorization", "Bearer YOUR_TOKEN")
                .when()
                .get("/users/3")
                .then()
                .statusCode(200)
                .body("id", equalTo(3))
                .body("name", notNullValue());
    }
}