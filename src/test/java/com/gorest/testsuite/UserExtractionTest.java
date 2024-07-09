package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class UserExtractionTest {
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

    // 1. Extract the All Ids
    @Test
    public void test001() {
        List<Integer> ids = response.extract().path("id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The all ids are : " + ids);
        System.out.println("------------------End of Test---------------------------");
    }

    //2.Extract All names
    @Test
    public void test002() {
        List<String> names = response.extract().path("name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The all names are : " + names);
        System.out.println("------------------End of Test---------------------------");
    }
    // 3. Extract the name of 5th object
    @Test
    public void test003() {
        String nameofObject = response.extract().path("[4].name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The name of 5th object is : " + nameofObject);
        System.out.println("------------------End of Test---------------------------");
    }

    //4. Extract the names of all object whose status = inactive
    @Test
    public void test004() {
        List<String> names = response.extract().path("findAll{it.status == 'inactive'}.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The names of all object whose status = inactive is : " + names);
        System.out.println("------------------End of Test---------------------------");
    }
    //5.Extract the gender of all the object whose status = active
    @Test
    public void test005() {
        List<String> gender = response.extract().path("findAll{it.status == 'active'}.gender");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The gender of all the object whose status = active is : " + gender);
        System.out.println("------------------End of Test---------------------------");
    }
    // 6. Print the names of the object whose gender = female
    @Test
    public void test006() {
        List<String> genderF = response.extract().path("findAll{it.gender == 'female'}.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The names of the object whose gender = female is : " + genderF);
        System.out.println("------------------End of Test---------------------------");
    }
    //7. Get all the emails of the object where status = inactive
    @Test
    public void test007() {
        List<String> email = response.extract().path("findAll{it.status == 'inactive'}.email");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The emails of the object where status = inactive is : " + email);
        System.out.println("------------------End of Test---------------------------");
    }

    // 8. Get the ids of the object where gender = male
    @Test
    public void test008() {
        List<Integer> ids = response.extract().path("findAll{it.gender == 'male'}.id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The ids of the object where gender = male is : " + ids);
        System.out.println("------------------End of Test---------------------------");
    }

    // 9. Getall the status
    @Test
    public void test009() {
        List<String> status = response.extract().path("status");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("All the status : " + status);
        System.out.println("------------------End of Test---------------------------");
    }

    // 10. Get email of the object where name = Devasree Varrier
    @Test
    public void test010() {
        String email = response.extract().path("find{it.name == 'Devasree Varrier'}.email");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The email of the object where name = Devasree Varrier is : " + email);
        System.out.println("------------------End of Test---------------------------");
    }
    // 11. Get gender of id = 7018224
    @Test
    public void test011() {
        String idGender = response.extract().path("find{it.id == 7018224}.gender");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The gender of id = 7018224 is : " + idGender);
        System.out.println("------------------End of Test---------------------------");
    }

}
