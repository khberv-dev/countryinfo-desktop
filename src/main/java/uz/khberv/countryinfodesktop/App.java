package uz.khberv.countryinfodesktop;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import uz.khberv.countryinfodesktop.model.Country;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

public class App extends javafx.application.Application {
    public static ArrayList<Country> countryList = new ArrayList<>();

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setTitle("Country info");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}