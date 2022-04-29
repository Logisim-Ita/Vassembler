package vas.vas.Assem;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import vas.vas.Support.File;

public class pop_up {
    static String messaggio = "";
    public void error_load(String messaggio){
        pop_up.messaggio += messaggio;
    }

    public static void clear(){
        pop_up.messaggio = "";
    }

    public static void pop_exit(String messaggio){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Perlo Avviso");
        alert.setHeaderText(null);
        alert.setContentText(messaggio);
        ButtonType buttonTypeOne = new ButtonType("Chiudi comunque");
        ButtonType buttonTypeTwo = new ButtonType("Ho cambiato idea");

        alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);

        alert.showAndWait().ifPresent(type -> {
            if (type == buttonTypeOne){
                System.exit(0);
            }
        });
    }

    public static void pop_exit(String messaggio, TextArea codezone) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Perlo Avviso");
        alert.setHeaderText(null);
        alert.setContentText(messaggio);

        ButtonType buttonTypeOne = new ButtonType("Apri nuovo file");
        ButtonType buttonTypeTwo = new ButtonType("Ho cambiato idea");

        alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);

        alert.showAndWait().ifPresent(type -> {
            if (type == buttonTypeOne){
                File.path = "";
                File.code = "main:";
                codezone.setText("main:");
            }
        });
    }

    public static void pop_up() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Perlo Avviso");
        alert.setGraphic(null);
        alert.setHeaderText(null);
        alert.setContentText(messaggio);
        ButtonType buttonTypeOne = new ButtonType("Roger!");
        alert.getButtonTypes().setAll(buttonTypeOne);
        alert.showAndWait();
    }
}
