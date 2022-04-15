package vas.vas.Support;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import vas.vas.Assem.pop_up;

import java.io.*;

public class Thread extends java.lang.Thread {
    private final String task;
    private String text;
    @FXML
    private TextArea codezone;

    private volatile boolean exit = false;
    public void run(){
        while(!exit){
            switch (task){
                case "salvaAsm":
                    try {
                        File.save(text,".asm");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    exit = true;
                    break;
                case "salva18":
                    try {
                        File.save(text,".b18");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    exit = true;
                    break;
                case "loadASM":
                    try {
                        File.loadASM(codezone);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    exit = true;
                    break;
                case "file":
                    build_list.list(codezone);
                    exit = true;
                    break;
                case "pop":
                    pop_up.error_out();
                    exit = true;
                    break;
            }
        }
    }
    public Thread(String task) {
        this.task = task;
    }

    public Thread(String task, String text) {
        this.task = task;
        this.text = text;
    }
    public Thread(String task, TextArea codezone) {
        this.task = task;
        this.codezone = codezone;
    }

}
