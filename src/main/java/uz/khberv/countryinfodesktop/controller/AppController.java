package uz.khberv.countryinfodesktop.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import uz.khberv.countryinfodesktop.App;
import uz.khberv.countryinfodesktop.factory.CountryCellFactory;
import uz.khberv.countryinfodesktop.model.Country;
import uz.khberv.countryinfodesktop.utils.ImageLoader;
import uz.khberv.countryinfodesktop.utils.Request;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class AppController {
    public ListView<Country> countryListView;
    public TextField inpSearch;
    private final ObservableList<Country> countryListResult = FXCollections.observableList(new ArrayList<>());
    public ImageView countryFlag;
    public Label countryName;
    public Label countryNativeName;
    public Label countryCapital;
    public Label countryPopulation;
    public Label countryArea;
    public Label countryCurrency;
    public Label countryContinent;
    public VBox countryInfoBox;

    private void bindCountryListView() {
        countryListResult.addAll(App.countryList);
        countryListView.setItems(countryListResult);
        countryListView.setCellFactory(new CountryCellFactory());

        countryListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, selected) -> {
            ImageLoader.loadImage(selected.flag, countryFlag);
            String currency = selected.currencyCode + " [" + selected.currencyName + ", " + selected.currencySymbol + "]";

            countryInfoBox.setVisible(true);
            countryNativeName.setText(new String(selected.nativeName.getBytes(), StandardCharsets.UTF_8));
            countryName.setText(selected.name);
            countryCapital.setText(selected.capital);
            countryContinent.setText(selected.continent);
            countryPopulation.setText(String.format("%,d", selected.population));
            countryArea.setText(String.format("%,d sq.km", selected.area));
            countryCurrency.setText(new String(currency.getBytes(), StandardCharsets.UTF_8));
        });
    }

    private void bindSearch() {
        inpSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            countryListResult.clear();
            for (Country country : App.countryList) {
                if (country.name.toLowerCase().contains(newValue.toLowerCase()))
                    countryListResult.add(country);
            }
            countryListView.refresh();
        });
    }

    private void startLoadingCountryList() {
        new Thread(() -> {
            Request.loadFullData();
            bindCountryListView();
        }).start();
    }

    @FXML
    private void initialize() {
        startLoadingCountryList();
        bindSearch();
    }
}