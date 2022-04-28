package vas.vas.Support;

import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import vas.vas.Assem.pop_up;

import javax.swing.*;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class File {
    public static String path = "";
    public static String code = "main:";
    public static void save(String buffer) throws IOException {
        if(path.equals("")){
            save(buffer,".asm");
        }
        else{
            code = buffer;
            PrintWriter writer = new PrintWriter(path, StandardCharsets.UTF_8);
            writer.print(buffer);
            writer.close();
        }
    }

    public static void save(String text, String format) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Salva il file");
        if(format.equals(".asm")){
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("ASM Files", "*.asm")
            );
        }
        else{
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("B18 Files", "*.b18")
            );
        }
        java.io.File file = fileChooser.showSaveDialog(null);
        if (file != null) {
            if (!file.getName().endsWith(format)) {
                file = new java.io.File(file.getPath() + format);
            }
            try {
                FileWriter fileWriter = new FileWriter(file);
                if(format.equals(".asm")){
                    fileWriter.write(text);
                    code = text;
                    path = file.getPath();
                }
                else{
                    fileWriter.write("v2.0 raw\n" + text);
                }
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void loadASM(TextArea codezone) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Apri il file");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("ASM Files", "*.asm")
        );
        java.io.File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            String text = new String(Files.readAllBytes(Paths.get(file.getPath())));
            codezone.setText(text);
            path = file.getPath();
            code = text;
        }
    }

    /**
     * Legge il contenuto di un file
     * @param filePath
     * @return
     * @throws IOException
     */
    public static String full_file_reader(String filePath) {
        String content = "";
        try{
            content = new String ( Files.readAllBytes( Paths.get(filePath) ) );
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }
}
