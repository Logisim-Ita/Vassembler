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
    /**
     * Salva in un file .asm i contenuti del buffer
     * @param buffer
     * @throws IOException
     */
    public static void saveasm(String buffer) throws IOException {
        JFrame frame = new JFrame();
        FileDialog fd = new FileDialog(frame, "Scegli dove salvare", FileDialog.SAVE);
        fd.setDirectory("");
        fd.setVisible(true);
        //Controlla che l'utente abbia effettivamente selezionato una cartella e un nome valido
        if(fd.getDirectory() != null && fd.getFile() != null){
            java.io.File file = new java.io.File(fd.getDirectory()+fd.getFile()+".asm");
            PrintWriter writer = new PrintWriter(file, StandardCharsets.UTF_8);
            writer.print(buffer);
            writer.close();
        }
    }

    /**
     * Salva in un file .b18 i contenuti del buffer
     * @param buffer
     * @throws IOException
     */
    public static void saveB18(String buffer) throws IOException {
        JFrame frame = new JFrame();
        FileDialog fd = new FileDialog(frame, "Scegli dove salvare", FileDialog.SAVE);
        fd.setDirectory("");
        fd.setVisible(true);
        //Controlla che l'utente abbia effettivamente selezionato una cartella e un nome valido
        if(fd.getDirectory() != null && fd.getFile() != null) return;
        java.io.File file = new java.io.File(fd.getDirectory()+fd.getFile()+".b18");
        PrintWriter writer = new PrintWriter(file, StandardCharsets.UTF_8);
        writer.print(buffer);
        writer.close();

    }

    /**
     * Fa scegliere all'utente il file da cui caricare i dati, e prende il contenuto e lo mette nella text area
     * @param codezone
     * @throws IOException
     */
    public static void loadASM(TextArea codezone) throws IOException {
        JFrame frame = new JFrame();
        FileDialog fd = new FileDialog(frame, "Scegli il file da caricare", FileDialog.LOAD);
        fd.setDirectory("");
        fd.setVisible(true);
        //Controlla che l'utente abbia effettivamente selezionato una cartella e un nome valido
        if(fd.getDirectory() != null && fd.getFile() != null){
            codezone.setText(full_file_reader(fd.getDirectory()+fd.getFile()));
        }
    }

    /**
     * Passato il path di un file lo legge e ritorna un buffer con tutte le informazioni contenute nel file
     * @param filePath
     * @return
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
