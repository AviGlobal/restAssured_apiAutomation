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

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;


@Slf4j
public class deleteAPIAutomate {

    @BeforeTest
    public void setupSpec(){
        requestSpecification = new RequestSpecBuilder()
                .setBaseUri(BaseTest.url.REQRES_URL)
                .setContentType(ContentType.JSON)
                .build();
    }

    @Test(priority = 1, description = "Delete API")
    public void deleteReqResAPI(){
        Response response = given()
                .spec(requestSpecification)
                .header("x-api-key", "reqres-free-v1")
                .basePath(EndPointsUtils.reqResEndPoints.SINGLE_LIST_OF_USERS_GET)
                .when()
                .delete()
                .then()
                .extract().response();
        int statusCode = response.getStatusCode();
        System.out.println(statusCode);
        Assert.assertEquals(statusCode,204);
        System.out.println(response.asPrettyString());
    }


}
