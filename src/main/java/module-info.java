module vas.vas {
    requires javafx.controls;
    requires javafx.fxml;

    requires java.desktop;

    opens vas.vas to javafx.fxml;
    exports vas.vas;
    exports vas.vas.Support;
    opens vas.vas.Support to javafx.fxml;
}