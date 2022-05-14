package vas.vas;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import vas.vas.Support.Download_list;
import vas.vas.Support.LngDefines;
import vas.vas.Support.Others;
import vas.vas.Support.pop_up;

import java.io.IOException;
import java.util.Objects;

public class Hollow extends Application {
    double x,y = 0;
    public static Stage stage;
    public static Scene sc;
    public static void iconified() {
        stage.setIconified(true);
    }

    public static void normal_style(){
        sc.getStylesheets().clear();
        sc.getStylesheets().add(Hollow.class.getResource("css/styling.css").toExternalForm());
        stage.setScene(sc);
        stage.show();
    }
    public static void developer_style(){
        sc.getStylesheets().clear();
        sc.getStylesheets().add(Hollow.class.getResource("css/developer_styling.css").toExternalForm());
        stage.setScene(sc);
        stage.show();
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
        sc = new Scene(root);
        stage.initStyle(StageStyle.UNDECORATED);
        root.setOnMousePressed(evt ->{
            x = evt.getSceneX();
            y = evt.getSceneY();
        });
        root.setOnMouseDragged(evt ->{
            stage.setX(evt.getScreenX()-x);
            stage.setY(evt.getScreenY()-y);
        });
        Hollow.stage = stage;
        if(Others.Instruction_Mode == Others.developer_mode){
            developer_style();
        }
        else{
            normal_style();
        }
        if(Download_list.internet_check()){
            Download_list.update_list();
        }
        else {
            pop_up pop = new pop_up();
            pop.error_load(LngDefines.LNG_Internet_connection_error_using);
            pop_up.pop_up();
            pop_up.clear();
        }
        Others.check_insructions_update();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
