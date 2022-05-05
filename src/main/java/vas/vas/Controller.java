package vas.vas;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import vas.vas.Assem.Elaboration;
import vas.vas.Support.*;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Controller {
    @FXML
    public TextArea codezone;

    @FXML
    public TextArea hexTextA;

    @FXML
    public Button listmode;

    @FXML
    public Button save;

    @FXML
    public Button save_as;

    @FXML
    private Button create_new;

    @FXML
    public Button open_asm;

    @FXML
    public Button assemble;

    @FXML
    public Button export_rom;

    @FXML
    public Button beautify;

    @FXML
    public Button instruction_list;

    @FXML
    public Button language;

    public void initialize(){
        if(Others.Instruction_Mode == Others.classic_mode) listmode.setText(LngDefines.LNG_Normal_Mode_using);
        else listmode.setText(LngDefines.LNG_Developer_Mode_using);
        if(LngDefines.LNG_active == LngDefines.LNG_IT) language.setText(LngDefines.LNG_Language_using);
        else language.setText(LngDefines.LNG_Language_using);
        save.setText(LngDefines.LNG_Save_using);
        save_as.setText(LngDefines.LNG_Save_as_using);
        create_new.setText(LngDefines.LNG_Create_new_using);
        open_asm.setText(LngDefines.LNG_Open_using);
        assemble.setText(LngDefines.LNG_Assemble_using);
        export_rom.setText(LngDefines.LNG_Export_Rom_using);
        beautify.setText(LngDefines.LNG_Beautify_using);
        instruction_list.setText(LngDefines.LNG_Instruction_list_using);
    }

    public void change_language() throws FileNotFoundException {
        LngDefines.swapLNG();
        initialize();
        Others.change_language_settings();
    }

    public void stopApplication(){
        if(File.code.equals(codezone.getText())){
            System.exit(0);
        }
        else{
            pop_up.pop_exit(LngDefines.LNG_Progress_lost_using);
        }
    }

    /**
     * Tasto per rendere a icona il programma
     */
    public void minimize(){
        Hollow.iconified();
    }

    public void instructions_mode() throws FileNotFoundException {
        Others.developer_mode(listmode);
        Others.change_mode_settings();
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
            pop_up.pop_exit(LngDefines.LNG_Progress_lost_using,codezone);
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
        build_list.list(hexTextA);
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
