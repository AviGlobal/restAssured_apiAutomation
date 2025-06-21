package core;

import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;



public class OllamaClient {

    public static String generatePromptForTestData(String userInput) throws IOException{

        System.out.println("User Input:" + userInput);
        String prompt = "Return a JSON array of test cases for the following API description: \"" + userInput + "\". " +
                "Cover all cases positive and negative and should be a JSON object of input fields " +
                "Return only the JSON array. Do not include explanations or extra text.";

        System.out.println("Generated Prompt:" + prompt);
        return prompt;

    }

    public static String generateTestData(String prompt) throws Exception {
        URL url = new URL("http://localhost:11434/api/generate");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);

        // Escaping prompt properly in JSON
        String payload = String.format("{\"model\":\"mistral\", \"prompt\":%s, \"stream\": true}",
                JSONObject.quote(prompt));

        try (OutputStream os = conn.getOutputStream()) {
            os.write(payload.getBytes(StandardCharsets.UTF_8));
            os.flush();
        }

        StringBuilder response = new StringBuilder();

        try (Scanner scanner = new Scanner(conn.getInputStream(), StandardCharsets.UTF_8)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                // Parse JSON object per line
                if (line.trim().startsWith("{") && line.contains("\"response\"")) {
                    JSONObject json = new JSONObject(line);
                    if (json.has("response")) {
                        response.append(json.getString("response"));
                    }
                }
            }
        }

        return response.toString().trim();
    }

}

