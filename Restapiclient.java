package Restapiclient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Restapiclient {

    public static void main(String[] args) {

        try {
            String apiUrl =
                "https://api.open-meteo.com/v1/forecast?latitude=13.08&longitude=80.27&current_weather=true";

            URL url = new URL(apiUrl);
            HttpURLConnection connection =
                (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(
                new InputStreamReader(connection.getInputStream()));

            StringBuilder response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            reader.close();

            String json = response.toString();

            // Simple manual extraction
            String temperature =
                json.split("\"temperature\":")[1].split(",")[0];
            String windspeed =
                json.split("\"windspeed\":")[1].split(",")[0];

            System.out.println("------ WEATHER REPORT ------");
            System.out.println("Temperature : " + temperature + " °C");
            System.out.println("Wind Speed  : " + windspeed + " km/h");

        } catch (Exception e) {
            System.out.println("Error while calling REST API");
        }
    }
}
