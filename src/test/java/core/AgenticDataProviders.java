package core;


import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class AgenticDataProviders {


    @DataProvider(name = "apiTestData")
    public Object[][] getTestData(Method method) throws Exception {
        // Get prompt from test description
        String prompt = OllamaClient.generatePromptForTestData(method.getAnnotation(Test.class).description());

        // Get response from Ollama (should return JSON array of objects)
        String response = OllamaClient.generateTestData(prompt);
        System.out.println("Raw JSON response:\n" + response);

        // Parse response into JSONArray
        JSONArray jsonArray = new JSONArray(response);

        // Prepare Object[][] where each row is a Map<String, String>
        Object[][] testData = new Object[jsonArray.length()][1];
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            Map<String, String> testCase = new HashMap<>();

            for (String key : obj.keySet()) {
                Object value = obj.get(key);
                testCase.put(key, String.valueOf(value));  // Safe conversion
            }

            testData[i] = new Object[]{testCase};
        }

        return testData;
    }
}
