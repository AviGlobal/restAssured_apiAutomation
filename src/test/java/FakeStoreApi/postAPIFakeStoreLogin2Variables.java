package FakeStoreApi;

import core.AgenticDataProviders;
import core.BaseTest;
import core.EndPointsUtils;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.util.Map;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;

public class postAPIFakeStoreLogin2Variables {


    @BeforeTest
    public void SetupSpec() {
        requestSpecification = new RequestSpecBuilder()
                .setBaseUri(BaseTest.url.FAKE_API)
                .setContentType(ContentType.JSON)
                .build();
    }

    @Test(dataProvider = "apiTestData", dataProviderClass = AgenticDataProviders.class,
            description = "Best working API looks like, here's the username and password only accept string. String payload = \"{\\\"username\\\":\\\"string\\\"," +
                    "\\\"password\\\":\\\"string\\\"}\";\n")

    public void testLogin(Map<String, String> testData) {
        JSONObject body = new JSONObject();
        body.put("username",  testData.get("username"));
        body.put("password",  testData.get("password"));


        Response response = given()
                .contentType("application/json")
                .body(body.toString())
                .post(EndPointsUtils.fakeStoreAPI.LOGIN_CHECK);

        System.out.println("Testing: " + body);
        System.out.println("Status: " + response.statusCode());
        System.out.println("Response: " + response.getBody().asString());
        System.out.println("--------------------------------------");

    }
}
