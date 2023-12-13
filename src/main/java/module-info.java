module uz.khberv.countryinfodesktop {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;
    requires java.net.http;

    requires org.controlsfx.controls;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;

    opens uz.khberv.countryinfodesktop to javafx.fxml;
    exports uz.khberv.countryinfodesktop;
    exports uz.khberv.countryinfodesktop.controller;
    exports uz.khberv.countryinfodesktop.model;
    opens uz.khberv.countryinfodesktop.controller to javafx.fxml;
}