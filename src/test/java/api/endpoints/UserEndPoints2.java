package api.endpoints;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.ResourceBundle;

import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_OK;


//UserEndPints.java
// Created for perform Create, Read, Update, Delete requests t the user API.


public class UserEndPoints2 {


    // method created for getting URL's from properties file
    static ResourceBundle getURL()
    {
        // Load properties file  // name of the properties file
        ResourceBundle routes= ResourceBundle.getBundle("routes");
        return routes;
    }



    public static Response createUser(User payload)
    {
        String post_url = getURL().getString("post_url");

        Response response=given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .when()
                .post(post_url);

        return response;
    }

    public static Response readUser(String userName)
    {
        String get_url = getURL().getString("get_url");

        Response response = given()
                            .pathParams("username",userName)
                            .when()
                            .get(get_url);
        return response;
    }


    public static User getUser(String userName)
    {

        String get_url =getURL().getString("update_url");

        User user = given()
                .pathParams("username",userName)
                .when()
                .get(get_url)
                .then().assertThat().log().all().statusCode(SC_OK)
                .extract()
                .body()
                .as(User.class);

        return user;
    }

    public static Response updateUser(String userName,User payload)
    {
        String update_url = getURL().getString("update_url");

        Response response=given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("username", userName)
                .body(payload)
                .when()
                .put(update_url);

        return response;
    }

    public static Response deleteUser(String userName)
    {
        String delete_url = getURL().getString("delete_url");

        Response response = given()
                .pathParams("username",userName)
                .when()
                .delete(delete_url);

        return response;
    }
}
