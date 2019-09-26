package com;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

public class SampleTests {

    Response response;

    @Test
    public void sampleTest() {
        String apiUrl = "https://reqres.in/api/users?page=2";
        response = given().when().get(apiUrl);
        System.out.println(response.getStatusCode());
    }

    @Test
    public void sampleTestWithHeaders() {
        String apiUrl = "https://reqres.in/api/users?page=2";
        response = given().
                headers("Content-Type", "application/json").
                headers("username", "test@yopmail.com").
                headers("password", "123456").when().get(apiUrl);
        System.out.println(response.getStatusCode());
    }

    @Test
    public void sampleWithBodyAsFile() {
        String apiUrl = "https://reqres.in/api/users";
        String jsonFile = "{\n" +
                "  \"name\": \"morpheus\",\n" +
                "  \"job\": \"leader\"\n" +
                "}";
        File file = new File(System.getProperty("user.dir")+"/src/main/resources/data.json");
        response = given().
                headers("Content-Type", "application/json").
                body(file).when().post(apiUrl);
        System.out.println(response.getStatusCode());
    }

    @Test
    public void sampleWithJsonString() {
        String apiUrl = "https://reqres.in/api/users";
        String jsonFile = "{\n" +
                "  \"name\": \"morpheus\",\n" +
                "  \"job\": \"leader\"\n" +
                "}";
        response = given().
                headers("Content-Type", "application/json").
                body(jsonFile).when().post(apiUrl);
        System.out.println(response.getStatusCode());
        System.out.println(response.getBody().asString());
    }
}
