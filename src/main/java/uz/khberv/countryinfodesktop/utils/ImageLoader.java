package uz.khberv.countryinfodesktop.utils;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

public class ImageLoader {
    private static final HashMap<String, byte[]> cache = new HashMap<>();

    public static void loadImage(String url, ImageView image) {
        boolean done = false;

        try {
            byte[] data;

            if (cache.get(url) == null) {
                HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
                data = connection.getInputStream().readAllBytes();
                cache.put(url, data);
            } else {
                data = cache.get(url);
            }

            Image img = new Image(new ByteArrayInputStream(data));

            image.setImage(img);

            done = data.length > 0;
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            if (!done) loadImage(url, image);
        }
    }
}
