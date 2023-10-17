module com.dashboard.groupfiveproject {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.dashboard.groupfiveproject to javafx.fxml;
    exports com.dashboard.groupfiveproject;
}