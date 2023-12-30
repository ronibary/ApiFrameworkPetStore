import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.CoreMatchers.equalTo;

public class RestBasics_AddPlace_With_Deserialization {
    public static void main(String[] args) throws IOException {

        //validate if add place API is working as expected

        // the principle of RestAssured work like BDD

        //given - all input details
        //when - Submit the API
        //Then - validate the response


        //Hamcrest library - use it for log and for
        // assertions of values , some of the methods in hamcrest
        // need static import

        // read the json from a file
        // handle static json file read the file and convert it into string
        // content of the file can convert from bytes to String



        //String addPlaceBody = Payload.AddPlace();



        RestAssured.defaultParser = Parser.JSON;
        RestAssured.baseURI = "https://rahulshettyacademy.com";


        String response = given().log().all().queryParam("key","qaclick123")
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "  \"location\": {\n" +
                        "    \"lat\": -38.383494,\n" +
                        "    \"lng\": 33.427362\n" +
                        "  },\n" +
                        "  \"accuracy\": 50,\n" +
                        "  \"name\": \"Frontline house\",\n" +
                        "  \"phone_number\": \"(+91) 983 893 3937\",\n" +
                        "  \"address\": \"29, side layout, cohen 09\",\n" +
                        "  \"types\": [\n" +
                        "    \"shoe park\",\n" +
                        "    \"shop\"\n" +
                        "  ],\n" +
                        "  \"website\": \"http://google.com\",\n" +
                        "  \"language\": \"French-IN\"\n" +
                        "}")
                .when().post("maps/api/place/add/json")
                .then().log().all().assertThat()
                .statusCode(SC_OK)
                .body("scope",equalTo("APP"))
                .header("Server","Apache/2.4.52 (Ubuntu)")
                .extract()
                .response()
                .asString();

        System.out.println("response = " + response);



    }
}
