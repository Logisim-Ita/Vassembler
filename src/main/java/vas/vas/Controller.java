package vas.vas;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import vas.vas.Assem.Elaboration;
import vas.vas.Support.*;
import vas.vas.Support.Thread;

import java.io.IOException;

public class Controller {
    @FXML
    private TextArea codezone;

    @FXML
    private TextArea hexTextA;

    @FXML
    private Button listmode;

    @FXML
    private Button save;

    @FXML
    private Button save_as;

    @FXML
    private Button create_new;

    @FXML
    private Button open_asm;

    @FXML
    private Button assemble;

    @FXML
    private Button export_rom;

    @FXML
    private Button beautify;

    @FXML
    private Button instruction_list;

    @FXML
    private Button language;
    /**
     * Tasto per terminare il programma
     */

    public void change_language(){
        LngDefines.swapLNG();

        listmode.setText(LngDefines.LNG_Normal_Mode_using);
        save.setText(LngDefines.LNG_Save_using);
        save_as.setText(LngDefines.LNG_Save_as_using);
        create_new.setText(LngDefines.LNG_Create_new_using);
        open_asm.setText(LngDefines.LNG_Open_using);
        assemble.setText(LngDefines.LNG_Assemble_using);
        export_rom.setText(LngDefines.LNG_Export_Rom_using);
        beautify.setText(LngDefines.LNG_Beautify_using);
        instruction_list.setText(LngDefines.LNG_Instruction_list_using);
        language.setText(LngDefines.LNG_Language_using);
    }


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

    public void instructions_mode(){
        Others.developer_mode(listmode);
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

    public void assembla() throws IOException {
        Elaboration el = new Elaboration();
        el.setInstructions();
        hexTextA.setText(el.translation(codezone.getText()));
        hexTextA.setEditable(false);
        pop_up.pop_up();
        pop_up.clear();
    }
}
