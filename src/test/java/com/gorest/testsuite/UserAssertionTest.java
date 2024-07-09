package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class UserAssertionTest {

    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in";

        response = given()
                .queryParam("page", 1)
                .queryParam("Per_page", 20)
                .when()
                .get("/public/v2/users")
                .then().statusCode(200);
    }

    // 1. Verify the if the total record is 20
    @Test
    public void test001() {
        response.body("size()", equalTo(10));
    }

    //2. Verify the if the name of id = 7018223 is equal to ”Aryan Varman”
    @Test
    public void test002() {
        response.body("find{it.id ==7018223}.name", equalTo("Aryan Varman"));
    }

    // 3. Checkthe single ‘Name’ in the Array list (Anjushri Kakkar)
    @Test
    public void test003() {
        response.body("name", hasItem("Anjushri Kakkar"));
    }

    //4. Checkthe multiple ‘Names’ in the ArrayList (Aryan Varman, Adhrit Jain, Devasree Varrier)
    @Test
    public void test004() {
        response.body("name", hasItems("Aryan Varman", "Adhrit Jain", "Devasree Varrier"));
    }

    // 5. Verify the email of userid = 7018223 is equal “aryan_varman@wilkinson-tremblay.test”
    @Test
    public void test005() {
        response.body("find{it.id ==7018223}.email", equalTo("aryan_varman@wilkinson-tremblay.test"));
    }

    //6. Verify the status is “inactive” of user name is “Aryan Varman'
    @Test
    public void test006() {
        response.body("find{it.name =='Aryan Varman'}.status", equalTo("inactive"));
    }

    //7. Verify the Gender = male of user name is “Aryan Varman”
    @Test
    public void test007() {
        response.body("find{it.name =='Aryan Varman'}.gender", equalTo("male"));
    }
}
