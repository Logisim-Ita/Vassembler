package vas.vas;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import vas.vas.Support.Thread;

public class Controller {

    @FXML
    private TextArea codezone;

    @FXML
    private TextArea hexTextA;
    //Create un nuovo fxml apposito che prende istruzioni da file le mostra
    public void webList(){

    }
    public void stopApplication(){
        System.exit(0);
    }

    public void minimize(){
        Hollow.iconified();
    }

    public void saveASM(){
        String buffer;
        buffer = codezone.getText();
        java.lang.Thread thread = new Thread("salvaAsm",buffer);
        new java.lang.Thread(thread).start();
    }
    public void save18(){
        String buffer;
        hexTextA.setEditable(false);
        buffer = hexTextA.getText();
        java.lang.Thread thread = new Thread("salva18",buffer);
        new java.lang.Thread(thread).start();
    }
    public void loadASM(){
        java.lang.Thread thread = new Thread("loadASM",codezone);
        thread.start();
    }
}
