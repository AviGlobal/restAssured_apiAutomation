package ReqResAutomation;

import core.AgenticDataProviders;
import core.BaseTest;
import core.EndPointsUtils;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;


@Slf4j
public class postAPIAgeniticAITest {


    @BeforeTest
    public void SetupSpec() {
        requestSpecification = new RequestSpecBuilder()
                .setBaseUri(BaseTest.url.REQRES_URL)
                .setContentType(ContentType.JSON)

                .build();
    }

    @Test(dataProvider = "apiTestData",
            dataProviderClass = AgenticDataProviders.class, description = "Best working API looks like: {\n" +
            "    \"email\": \"eve.holt@reqres.in\",\n" +
            "    \"password\": \"cityslicka\"\n" +
            "}")
    public void testLogin(Map<String, String> testData) {
        JSONObject body = new JSONObject();
        body.put("email",  testData.get("email"));
        body.put("password",  testData.get("password"));

        Response response = given()
                .contentType("application/json")
                .header("x-api-key","reqres-free-v1")
                .body(body.toString())
                .post(EndPointsUtils.reqResEndPoints.LOGIN_POST);

        System.out.println("Testing: " + body);
        System.out.println("Status: " + response.statusCode());
        System.out.println("Response: " + response.getBody().asString());
        System.out.println("--------------------------------------");

    }

}
