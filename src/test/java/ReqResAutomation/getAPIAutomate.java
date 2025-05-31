package ReqResAutomation;

import core.BaseTest;
import core.EndPointsUtils;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.*;


@Slf4j
public class getAPIAutomate {


   @BeforeTest
    private void setupSpec(){
        requestSpecification = new RequestSpecBuilder()
                .setBaseUri(BaseTest.url.REQRES_URL)
                .setContentType(ContentType.JSON)
                .build();
    }

//    @Test(priority = 1, description = "This will fetch single user from API, " +
//            "and will validate and print the status code with the response body")
//    public void getAPISingleUsers(){
//       Response response = given()
//               .when()
//               .header("x-api-key","reqres-free-v1")
//               .get(EndPointsUtils.reqResEndPoints.SINGLE_LIST_OF_USERS_GET)
//               .then().extract().response();
//       System.out.println(response.getStatusCode());
//       System.out.println(response.prettyPrint());
//       Assert.assertEquals(response.getStatusCode(),200);
//    }
//
//
//    @Test(priority = 2, description = "This will fetch multiple users for API, " +
//            "print the list of users and showcased HASH-MAP" +
//            "Check the response-time")
//    public void getAPIListOfUsersReqresCheck() {
//       Response response = given()
//               .when()
//               .get(EndPointsUtils.reqResEndPoints.MULTIPLE_USERS_LIST_GET)
//               .then().statusCode(200)
//               .extract()
//               .response();
//        int count = response.jsonPath().getList("data").size();
//        HashMap<Integer, String> mapResponse = new HashMap<>();
//        for (int i = 0; i < count; i++) {
//            int id = response.path("data[" + i + "].id");
//            String first_name = response.path("data[" + i + "].first_name");
//            mapResponse.put(id, first_name);
//            System.out.println("ID: " + id + " | First Name: " + first_name);
//        }
//        long responseTime = response.time();
//        Assert.assertTrue(responseTime < 100);
//        Assert.assertEquals(response.path("data[1].first_name"),"Lindsay");
//    }
//
//    @Test(priority = 3, description = "This will check 404, if user not found")
//    public void getcheckUserNotFound(){
//       int response = given()
//               .when()
//               .get(EndPointsUtils.reqResEndPoints.SINGLE_USER_NOT_FOUND_GET)
//               .then()
//               .extract().statusCode();
//       System.out.println("Here's response of the above API :" + response);
//       Assert.assertEquals(response, 401);
//    }
//
//    @Test(priority = 4, description = "This will fetch list of resources, " +
//            "and Print the list of details")
//    public void getListResources(){
//       Response response =  given()
//               .when()
//               .get(EndPointsUtils.reqResEndPoints.LIST_RESOURCE_GET)
//               .then()
//               .extract().response();
//       int size = response.jsonPath().getList("data").size();
//       System.out.println("Size of data array: " + size);
//       List<Integer> listId = new ArrayList<>();
//       List<String> listName = new ArrayList<>();
//       List<String> listYear = new ArrayList<>();
//
//       for (int i=0;i<size;i++)
//       {
//           int id = response.path("data[" + i + "].id");
//           String name = response.path("data[" + i + "].name");
//           int year = response.path("data[" + i + "].year");
//           listId.add(id);
//           listName.add(name);
//           listYear.add(String.valueOf(year));
//           System.out.println("Id: " + id + ", Name: " + name + ", Year: " + year);
//       }
//    }

    @Test(priority = 5, description = "Get single resource, and operate using List")
    public void getSingleResource(){
        Response response = given()
                .header("x-api-key","reqres-free-v1")
                .when()
                .get(EndPointsUtils.reqResEndPoints.SINGLE_RESOURCE_GET)
                .then()
                .extract().response();
        //The below steps is not important, we can directly use response.path("data[0].name")
        System.out.println(response.asPrettyString());
        List<String> checkName = new ArrayList<>();
        checkName.add(response.path("data.name"));
        System.out.println(checkName);
        Assert.assertTrue(checkName.contains("fuchsia rose"));
    }

    @Test(priority = 6, description = "Single user not found, error code check")
    public void checkErrorCode() {
        int statusCode = given()
                .spec(requestSpecification)
                .header("x-api-key","reqres-free-v1")
                .when()
                .get(EndPointsUtils.reqResEndPoints.RESOURCE_NOT_FOUND_GET)
                .then()
                .log().all()
                .extract()
                .statusCode();
        Assert.assertEquals(statusCode, 404);
    }



    @Test(priority = 7, description = "Check response time, " +
            "and verify if we are getting more response time than delay provided")
    public void getDelayInResponse(){
        int delayInResponse = 5;
        long response = given()
                .when()
                .get(EndPointsUtils.reqResEndPoints.DELAY_API_GET(delayInResponse))
                .then()
                .extract().time();
        long responseInSeconds = response / 1000;
        System.out.println(response);
        System.out.println(responseInSeconds);
        Assert.assertTrue(responseInSeconds>=delayInResponse);
    }



}
