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

    public static void pop_exit(String messaggio) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Perlo Avviso");
        alert.setHeaderText(null);
        alert.setContentText(messaggio);
        alert.showAndWait();
        if (alert.getResult() == ButtonType.OK) {
            System.exit(0);
        }
    }

    public static void pop_exit(String messaggio, TextArea codezone) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Perlo Avviso");
        alert.setHeaderText(null);
        alert.setContentText(messaggio);
        alert.showAndWait();
        //se l'utente ha premuto ok termina il programma
        if (alert.getResult() == ButtonType.OK) {
            File.path = "";
            File.code = "main:";
            codezone.setText("main:");
        }
    }

    public static void pop_up() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Perlo Avviso");
        alert.setHeaderText(null);
        alert.setContentText(messaggio);
        alert.showAndWait();
    }

}
