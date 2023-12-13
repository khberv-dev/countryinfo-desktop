package uz.khberv.countryinfodesktop.utils;

import org.json.JSONArray;
import uz.khberv.countryinfodesktop.App;
import uz.khberv.countryinfodesktop.model.Country;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Request {
    private static final String API_URL = "https://restcountries.com/v3.1/all";
//    private static final String API_URL = "http://localhost/ctry.json";

    public static void loadFullData() {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(API_URL).openConnection();
            InputStream stream = connection.getInputStream();

            String response = new String(stream.readAllBytes());
            JSONArray list = new JSONArray(response);

            for (int i = 0; i < list.length(); i++) {
                Country country = Country.parseFromJSON(list.getJSONObject(i));
                if (country != null) App.countryList.add(country);
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
