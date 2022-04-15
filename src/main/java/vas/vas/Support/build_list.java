package vas.vas.Support;

import javafx.scene.control.TextArea;
import vas.vas.Assem.pop_up;
import vas.vas.Main;

import java.io.File;

public class build_list {
    static pop_up er = new pop_up();
    public static void list(TextArea hexArea){
        File file = new File(Main.class.getProtectionDomain().getCodeSource().getLocation().getPath());
        try {
            String path = file.getCanonicalPath();
            String[] path_split = path.split("/");
            String path_final = "";
            for (int i = 0; i < path_split.length - 1; i++) {
                path_final += path_split[i] + "/";
            }
            path_final += "instruction.txt";
            hexArea.setText(vas.vas.Support.File.full_file_reader(path_final));
        } catch (Exception e) {
            er.error_load("Errore nella lettura del file instruction.txt");
            Thread thread = new Thread("pop");
            thread.start();
        }

    }
}
