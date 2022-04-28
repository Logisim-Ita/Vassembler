package vas.vas;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import vas.vas.Assem.Elaboration;
import vas.vas.Assem.pop_up;
import vas.vas.Support.File;
import vas.vas.Support.Thread;
import vas.vas.Support.beauty;

import java.io.IOException;

public class Controller {
    @FXML
    private TextArea codezone;

    @FXML
    private TextArea hexTextA;

    /**
     * Tasto per terminare il programma
     */
    public void stopApplication(){
        if(File.code.equals(codezone.getText())){
            System.exit(0);
        }
        else{
            pop_up.pop_exit("Non hai salvato perderai i progressi se non salvi!");
        }
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
        codezone.setText(beauty.editor(buffer));
    }

    public void save() throws IOException {
        File.save(codezone.getText());
    }
     /**
         * Pulsante per salvare i progressi in asm
         */
     public void save_as(){
        File.save(codezone.getText(),".asm");
     }
     public void newFile(){
        if(!File.code.equals(codezone.getText())){
            pop_up.pop_exit("Non hai salvato perderai i progressi se non salvi!",codezone);
        }
        else{
            File.path = "";
            File.code = "main:";
            codezone.setText("main:");
        }
     }
    /**
     * Pulsante per salvare i progressi in b18
     */
    public void save18(){
        File.save(hexTextA.getText(),".b18");
    }

    /**
     * Caricare un file asm scelto dall'utente
     */
    public void loadASM() throws IOException {
        File.loadASM(codezone);
    }

    public void list(){
        java.lang.Thread thread = new Thread("file",hexTextA);
        thread.start();
    }

    public void assembla(){
        Elaboration el = new Elaboration();
        el.setInstructions();
        hexTextA.setText(el.translation(codezone.getText()));
        hexTextA.setEditable(false);
        pop_up.pop_up();
        pop_up.clear();
    }
}
