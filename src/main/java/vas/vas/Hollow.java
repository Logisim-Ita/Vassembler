package vas.vas;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import vas.vas.Support.LngDefines;
import vas.vas.Support.Others;

import java.io.IOException;
import java.util.Objects;

public class Hollow extends Application {
    double x,y = 0;
    public static Stage stage;
    public static void iconified() {
        stage.setIconified(true);
    }
    public void load_settings() throws IOException {
        if(Others.settings_exists()){
            Others.settings_file();
        }
        else{
            Others.duplicate_settings();
            Others.settings_file();
        }
    }
    @Override
    public void start(Stage stage) throws Exception{
        load_settings();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("fxml/main.fxml")));
        stage.setTitle("Vassembler");
        Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("icons/icons8_puzzle_30px.png")));
        stage.getIcons().add(icon);
        Scene sc = new Scene(root);
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
