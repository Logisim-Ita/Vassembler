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

    public static void save(String buffer,String ext) throws IOException {
        JFrame frame = new JFrame();
        FileDialog fd = new FileDialog(frame, "Scegli dove salvare il file", FileDialog.SAVE);
        fd.setDirectory("");
        fd.setVisible(true);
        if(fd.getDirectory() != null && fd.getFile() != null){
            if(!fd.getFile().endsWith(ext)){
                fd.setFile(fd.getFile()+ext);
            }
            PrintWriter writer = new PrintWriter(fd.getDirectory()+fd.getFile(), StandardCharsets.UTF_8);
            writer.print(buffer);
            writer.close();
        }
    }

    /**
     * Carica il contenuto di un file .asm
     * @param codezone l'area di testo dove verranno caricati i contenuti del file
     * @return
     * @throws IOException
     */
    public static void loadASM(TextArea codezone) throws IOException {
        JFrame frame = new JFrame();
        FileDialog fd = new FileDialog(frame, "Scegli il file da caricare", FileDialog.LOAD);
        fd.setDirectory("");
        fd.setVisible(true);
        if(fd.getDirectory() != null && fd.getFile() != null){
            codezone.setText(full_file_reader(fd.getDirectory()+fd.getFile()));
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
