package org.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import java.io.IOException;


@SpringBootApplication
public class Main implements ApplicationContextAware {
    @Autowired
    JokeController jokeController;
    private static ApplicationContext ctx;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
        for (int i = 0; i < 25; i++) {
            readJokesFromApi();
        }
    }

    private static void readJokesFromApi() {
        try {
            String apiUrl = "https://v2.jokeapi.dev/joke/Any";
            URL url = new URL(apiUrl);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("GET");

            int responseCode = conn.getResponseCode();

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            makeTheJokeFromJSON(response.toString());

            conn.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void makeTheJokeFromJSON(String response) {
        String jsonResponse = response;
        // Step 2: Parse the JSON response using Jackson
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(jsonResponse);

            String category = jsonNode.get("category").asText();
            String type = jsonNode.get("type").asText();
            boolean religious = jsonNode.get("flags").get("religious").asBoolean();
            boolean political = jsonNode.get("flags").get("political").asBoolean();
            boolean racist = jsonNode.get("flags").get("racist").asBoolean();
            boolean sexist = jsonNode.get("flags").get("sexist").asBoolean();
            boolean explicit = jsonNode.get("flags").get("explicit").asBoolean();
            long id = jsonNode.get("id").asLong();
            String lang = jsonNode.get("lang").asText();
            Joke joke = null;
            if (type.equalsIgnoreCase("single")) {
                String theJoke = jsonNode.get("joke").asText();
                joke = new Joke(category, type, theJoke, "", "", religious, political, racist, sexist, explicit, lang,id);
            } else if (type.equalsIgnoreCase("twopart")) {
                String setup = jsonNode.get("setup").asText();
                String delivery = jsonNode.get("delivery").asText();
                joke = new Joke(category, type, "", setup, delivery, religious, political, racist, sexist, explicit, lang,id);
            }
            ctx.getBean(JokeController.class).createJoke(joke);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ctx = applicationContext;

    }
}
