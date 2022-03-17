package vas.vas.Support;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

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
                        File.saveasm(text);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    exit = true;
                    break;
                case "salva18":
                    try {
                        File.saveB18(text);
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
                    build_list.list();
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
