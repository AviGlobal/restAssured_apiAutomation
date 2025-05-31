package ReqResAutomation;

import core.BaseTest;
import core.EndPointsUtils;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;

@Slf4j
public class updateAPIAutomate {

    String updatedPutPayload = "{ \"name\": \"morph1\", \"job\": \"lead_SDET\" }";

    @BeforeTest
    public void setupSpec(){
        String result = postAPIAutomate.postUserCheckResponse();
        System.out.println("POST Response : " +result);
        requestSpecification = new RequestSpecBuilder()
                .setBaseUri(BaseTest.url.REQRES_URL)
                .setContentType(ContentType.JSON)
                .build();
    }

    @Test(priority = 1, description = "Update data by PUT method")
    public void updateUsingPutAPI(){
        Response putResponse = given()
                .spec(requestSpecification)
                .header("x-api-key", "reqres-free-v1")
                .basePath(EndPointsUtils.reqResEndPoints.SINGLE_LIST_OF_USERS_GET)
                .body(updatedPutPayload)
                .when()
                .put()
                .then()
                .extract().response();
        System.out.println("PUT response:"+ putResponse.asPrettyString());
        Assert.assertEquals(putResponse.getStatusCode(),200);
    }
}
