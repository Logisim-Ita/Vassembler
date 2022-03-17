package vas.vas;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import vas.vas.Support.Abbellimento;
import vas.vas.Support.Thread;

public class Controller {

    @FXML
    private TextArea codezone;

    @FXML
    private TextArea hexTextA;
    //Create un nuovo fxml apposito che prende istruzioni da file le mostra
    public void webList(){

    }

    /**
     * Tasto per terminare il programma
     */
    public void stopApplication(){
        System.exit(0);
    }

    /**
     * Tasto per rendere a icona il programma
     */
    public void minimize(){
        Hollow.iconified();
    }

    /**
     * Tasto per sistemare graficamente il codice
     */
    public void beautify(){
        String buffer = codezone.getText();
        codezone.setText(Abbellimento.editor(buffer));
    }

    /**
     * Pulsante per salvare i progressi in asm
     */
    public void saveASM(){
        String buffer;
        buffer = codezone.getText();
        java.lang.Thread thread = new Thread("salvaAsm",buffer);
        new java.lang.Thread(thread).start();
    }
    /**
     * Pulsante per salvare i progressi in b18
     */
    public void save18(){
        String buffer;
        hexTextA.setEditable(false);
        buffer = hexTextA.getText();
        java.lang.Thread thread = new Thread("salva18",buffer);
        new java.lang.Thread(thread).start();
    }

    /**
     * Caricare un file asm scelto dall'utente
     */
    public void loadASM(){
        java.lang.Thread thread = new Thread("loadASM",codezone);
        thread.start();
    }

    public void list(){
        java.lang.Thread thread = new Thread("file");
        thread.start();
    }
}
