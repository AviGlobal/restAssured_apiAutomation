package ReqResAutomation;

import core.BaseTest;
import core.DateUtil;
import core.EndPointsUtils;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;


@Slf4j
public class postAPIAutomate {



    @BeforeTest
    public void setupSpec(){
        requestSpecification =  new RequestSpecBuilder()
                .setBaseUri(BaseTest.url.REQRES_URL)
                .setContentType(ContentType.JSON)
                .build();
    }



     /* Showcasing " Using a Raw JSON String "
    Way to add JSON  */

    @Test(priority = 1, description = "Post user details," +
            "Check status-code" +
            "Check response and validate the response")
    public static String postUserCheckResponse(){
        String payload = "{ \"name\": \"morpheus\", \"job\": \"leader\" }";
        Response response =  given()
                .baseUri(BaseTest.url.REQRES_URL)
                .contentType(ContentType.JSON)
                .header("x-api-key","reqres-free-v1")
                .basePath(EndPointsUtils.reqResEndPoints.USER_DETAILS_POST)
                .body(payload)
                .when()
                .post()
                .then()
                .statusCode(201)
                .extract()
                .response();
        System.out.println(response.statusCode());
        System.out.println("Response Body: " + response.getBody().asPrettyString());
        String completeDate = response.getBody().path("createdAt");
        String fetchDate = completeDate.substring(0,10);
        Assert.assertEquals(fetchDate, DateUtil.getCurrentDate());
        return payload;
    }



     /* Showcasing " Using MAP to create a JSON and getting multiple sets to test by DATA-PROVIDER */
    @Test(
            priority = 2,
            description = "Check registration API with multiple datasets",
            dataProvider = "registerData",
            dataProviderClass = core.DataProviders.class
    )
    public void postRegisterSuccess(String email, String password){
        Map<String, String> payload = new HashMap<>();
        payload.put("email",email);
        payload.put("password",password);
        Response response = given()
                .spec(requestSpecification)
                .header("x-api-key","reqres-free-v1")
                .basePath(EndPointsUtils.reqResEndPoints.REGISTER_POST)
                .body(payload)
                .when()
                .post()
                .then()
                .extract()
                .response();
        int statusCode = response.getStatusCode();
        System.out.println("Email: " + email + " | Status Code: " + statusCode);
        Assert.assertEquals(statusCode, 200, "Status code mismatch for email: " + email);
    }




     /* Showcasing " Using MAP to create a JSON and getting multiple sets to test by DATA-PROVIDER */
    @Test(
            priority = 3,
            description = "Check login API with multiple datasets",
            dataProvider = "loginData",
            dataProviderClass = core.DataProviders.class
    )
    public void postLoginSuccess(String email, String password){
        Map<String, String> payload = new HashMap<>();
        payload.put("email",email);
        payload.put("password",password);
        Response response = given()
                .spec(requestSpecification)
                .header("x-api-key","reqres-free-v1")
                .basePath(EndPointsUtils.reqResEndPoints.LOGIN_POST)
                .body(payload)
                .when()
                .post()
                .then()
                .extract().response();
        int statusCode = response.getStatusCode();
        System.out.println("Email: " + email + " | Status Code: " + statusCode);
    }


    @Test(priority = 4, description = "Check unsuccessful registration")
    public void postRegisterUnsuccess(){
        Map<String,String> payload = new HashMap<>();
        payload.put("email", "sydney@fife");
        payload.put("password",null);
        Response response = given()
                .spec(requestSpecification)
                .header("x-api-key","reqres-free-v1")
                .basePath(EndPointsUtils.reqResEndPoints.REGISTER_POST)
                .body(payload)
                .when()
                .post()
                .then()
                .extract().response();
        Assert.assertEquals(response.getStatusCode(),400);
    }

    @Test(priority = 5, description = "Check unsuccessful login")
    public void postLoginUnsuccess(){
        Map<String,String> payload = new HashMap<>();
        payload.put("email", "sydney@fife");
        payload.put("password",null);
        Response response = given()
                .spec(requestSpecification)
                .header("x-api-key","reqres-free-v1")
                .basePath(EndPointsUtils.reqResEndPoints.LOGIN_POST)
                .body(payload)
                .when()
                .post()
                .then()
                .extract().response();
        Assert.assertEquals(response.getStatusCode(),400);
    }

}
