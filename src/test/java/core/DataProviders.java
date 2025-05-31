package core;

import org.testng.annotations.DataProvider;

public class DataProviders {

    @DataProvider(name = "registerData")
    public Object[][] provideRegisterData() {
        return new Object[][]{
                {"eve.holt@reqres.in", "pistol"},
                {"sydney@fife", ""},
                {"", "pistol"},
                {"invalid@email.com", "123456"}
        };
    }

    @DataProvider(name="loginData")
    public Object[][] loginRegisterData(){
        return new Object[][]
                {
                        {"eve.holt@reqres.in", "cityslicka"},
                        {"sydney@fife", ""},
                        {"", "pistol"},
                        {"invalid@email.com", "123456"}
                };
    }
}
