package uz.khberv.countryinfodesktop.factory;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import uz.khberv.countryinfodesktop.model.Country;
import uz.khberv.countryinfodesktop.utils.ImageLoader;

import java.nio.charset.StandardCharsets;

public class CountryCellFactory implements Callback<ListView<Country>, ListCell<Country>> {
    @Override
    public ListCell<Country> call(ListView<Country> param) {
        return new ListCell<>() {
            @Override
            protected void updateItem(Country item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                    return;
                }

                HBox itemFrame = new HBox();
                VBox namesFrame = new VBox();
                Label countryName = new Label();
                Label countryNativeName = new Label();
                ImageView countryFlag = new ImageView();

                itemFrame.setAlignment(Pos.CENTER_LEFT);
                itemFrame.setSpacing(10);

                countryName.setText(item.name);
                countryName.getStyleClass().add("lbl-country-name");

                countryNativeName.setText(new String(item.nativeName.getBytes(), StandardCharsets.UTF_8));
                countryNativeName.getStyleClass().add("lbl-country-native-name");

                new Thread(() -> {
                    countryFlag.setFitWidth(50);
                    countryFlag.setFitHeight(30);
                    ImageLoader.loadImage(item.flag, countryFlag);
                }).start();

                namesFrame.getChildren().add(countryName);
                namesFrame.getChildren().add(countryNativeName);
                itemFrame.getChildren().add(countryFlag);
                itemFrame.getChildren().add(namesFrame);
                setGraphic(itemFrame);
            }
        };
    }
}
