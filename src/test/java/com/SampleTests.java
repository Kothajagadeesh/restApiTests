package com;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.util.List;

import static io.restassured.RestAssured.given;

public class SampleTests {

    Response response;

    @BeforeClass
    public void init() {
        RestAssured.baseURI = "https://reqres.in";
        RestAssured.basePath = "/api";
    }

    @Test
    public void sampleTest() {
//        String apiUrl = "https://reqres.in/api/users?page=2";
        response = given().when().get("/users?page=2");
        System.out.println(response.getStatusCode());
    }

    @Test
    public void sampleTestWithHeaders() {
        //String apiUrl = "https://reqres.in/api/users?page=2";
        response = given().
                headers("Content-Type", "application/json").
                headers("username", "test@yopmail.com").
                headers("password", "123456").when().get("/users?page=2");
        System.out.println(response.getStatusCode());
    }

    @Test
    public void sampleWithBodyAsFile() {
        //String apiUrl = "https://reqres.in/api/users";
        File file = new File(System.getProperty("user.dir") + "/src/main/resources/data.json");
        response = given().
                headers("Content-Type", "application/json").
                body(file).when().post("/users");
        System.out.println(response.getStatusCode());
    }

    @Test
    public void sampleWithJsonString() {
        //String apiUrl = "https://reqres.in/api/users";
        String jsonFile = "{\n" +
                "  \"name\": \"morpheus\",\n" +
                "  \"job\": \"leader\"\n" +
                "}";
        response = given().
                headers("Content-Type", "application/json").
                body(jsonFile).when().post("/users");
        System.out.println(response.getStatusCode());
        System.out.println(response.getBody().asString());
    }

    @Test
    public void sampleWithJsonPath() {
        File file = new File(System.getProperty("user.dir") + "/src/main/resources/test.json");
        JsonPath jsonPath = new JsonPath(file);
        List<String> s = jsonPath.get("store.book.author");
        String firstAuthor = jsonPath.get("store.book[0].author");
        System.out.println(s);
        System.out.println(firstAuthor);
    }

    @Test
    public void sampleJsonPathWithResponse() {
        //String apiUrl = "https://reqres.in/api/users";
        File file = new File(System.getProperty("user.dir") + "/src/main/resources/data.json");
        response = given().
                headers("Content-Type", "application/json").
                body(file).when().post("/users");
        System.out.println(response.getStatusCode());
        System.out.println(response.getBody().asString());
        JsonPath jsonPath = new JsonPath(response.getBody().asInputStream());
        System.out.println(jsonPath.getString("name"));
    }

    @Test
    public void sampleWithCookies() {
//        String apiUrl = "https://reqres.in/api/users";
        File file = new File(System.getProperty("user.dir") + "/src/main/resources/data.json");
        response = given().
                headers("Content-Type", "application/json").
                body(file).when().post("/users");
    }
}
