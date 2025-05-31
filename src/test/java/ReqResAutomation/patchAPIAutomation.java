package ReqResAutomation;

import core.BaseTest;
import core.EndPointsUtils;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;

public class patchAPIAutomation {

    String updatedObjectInPayload = "{\"job\": \"SDET2\" }";

    @BeforeTest
    private void setupSpec() {
        String result = postAPIAutomate.postUserCheckResponse();
        System.out.println("POST Response : " +result);
        requestSpecification = new RequestSpecBuilder()
                .setBaseUri(BaseTest.url.REQRES_URL)
                .setContentType(ContentType.JSON)
                .build();
    }

    @Test(priority = 1, description = "This will update user details using PATCH, then fetch the same user using GET, " +
            "and will validate and print the status codes with response bodies")
    public void updateUsingPatchAPI() {
        Response patchResponse = given()
                .spec(requestSpecification)
                .header("x-api-key", "reqres-free-v1")
                .basePath(EndPointsUtils.reqResEndPoints.SINGLE_LIST_OF_USERS_GET) // or use PATCH endpoint if different
                .body(updatedObjectInPayload)
                .when()
                .patch()
                .then()
                .statusCode(200)
                .extract().response();
        System.out.println("PATCH Response:"+ patchResponse.asPrettyString());
        Assert.assertEquals(patchResponse.getStatusCode(),200);
    }
}