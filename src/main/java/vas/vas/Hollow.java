package vas.vas;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Objects;

public class Hollow extends Application {
    double x,y = 0;
    static Stage stage;
    public static void iconified() {
        stage.setIconified(true);
    }
    @Override
    public void start(Stage stage) throws Exception{
        //Carico il file .fxml che definisce la grafica del programma
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("fxml/main.fxml")));
        //Titolo del programma
        stage.setTitle("Vassembler");
        //Icona del programma
        Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("icons/icons8_puzzle_30px.png")));
        stage.getIcons().add(icon);
        Scene sc = new Scene(root);
        //Movimento del programma
        stage.initStyle(StageStyle.UNDECORATED);
        root.setOnMousePressed(evt ->{
            x = evt.getSceneX();
            y = evt.getSceneY();
        });
        root.setOnMouseDragged(evt ->{
            stage.setX(evt.getScreenX()-x);
            stage.setY(evt.getScreenY()-y);
        });
        stage.setScene(sc);
        stage.show();
        Hollow.stage = stage;
    }
    public static void main(String[] args) {
        launch(args);
    }

}
