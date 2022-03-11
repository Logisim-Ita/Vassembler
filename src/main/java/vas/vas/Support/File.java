package vas.vas.Support;

import javafx.scene.control.TextArea;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class File {
    public static void saveasm(String buffer) throws IOException {
        JFrame frame = new JFrame();
        FileDialog fd = new FileDialog(frame, "Scegli dove salvare", FileDialog.SAVE);
        fd.setDirectory("");
        fd.setVisible(true);
        if(fd.getDirectory() != null && fd.getFile() != null){
            java.io.File file = new java.io.File(fd.getDirectory()+fd.getFile()+".asm");
            PrintWriter writer = new PrintWriter(file, StandardCharsets.UTF_8);
            writer.print(buffer);
            writer.close();
        }
    }
    public static void saveB18(String buffer) throws IOException {
        JFrame frame = new JFrame();
        FileDialog fd = new FileDialog(frame, "Scegli dove salvare", FileDialog.SAVE);
        fd.setDirectory("");
        fd.setVisible(true);
        if(fd.getDirectory() != null && fd.getFile() != null){
            java.io.File file = new java.io.File(fd.getDirectory()+fd.getFile()+".b18");
            PrintWriter writer = new PrintWriter(file, StandardCharsets.UTF_8);
            writer.print(buffer);
            writer.close();
        }
    }
    public static void loadASM(TextArea codezone) throws IOException {
        JFrame frame = new JFrame();
        FileDialog fd = new FileDialog(frame, "Scegli il file da caricare", FileDialog.LOAD);
        fd.setDirectory("");
        fd.setVisible(true);
        if(fd.getDirectory() != null && fd.getFile() != null){
            codezone.setText(full_file_reader(fd.getDirectory()+fd.getFile()));
        }
    }
    private static String full_file_reader(String filePath) {
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
