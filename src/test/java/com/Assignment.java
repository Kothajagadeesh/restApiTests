package com;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

public class Assignment {
    Response response;

    @BeforeClass
    public void init() {
        RestAssured.baseURI = "https://dipqan-explayer-hl7fhir-lb-cc47fb476aea9b57.elb.us-west-2.amazonaws.com/api/hl7-fhir-message/validate/fhirmessage";
    }

    @Test
    public void assignment() {

        File file = new File(System.getProperty("user.dir") + "/src/main/resources/pavan.json");
        response = given().
                headers("Content-Type", "application/json").
                body(file).when().post("dipqan-explayer-hl7fhir-lb-cc47fb476ae9b57.elb.us-west-2.amazonaws.com");
        System.out.println(response.getStatusCode());
    }

}
