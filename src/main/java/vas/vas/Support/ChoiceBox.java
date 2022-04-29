package vas.vas.Support;

import javafx.scene.control.Button;

import java.io.*;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

import static vas.vas.Assem.Elaboration.getFileFromResourceAsStream;
import static vas.vas.Support.pop_up.generated_file;
import static vas.vas.Support.pop_up.generating_file;

public class ChoiceBox {
    public static boolean developer_mode = false;
    public static boolean classic_mode = true;
    public static boolean Instruction_Mode = true;

    public static String path_developer = "";
    public static boolean file_already_exists = false;

    public static void developer_path() throws IOException {
        String path = System.getProperty("user.dir");
        path += "/instruction.txt";
        path_developer = path;
    }

    public static void generate_developer_file() throws IOException {
        File file = new File(path_developer);
        file.createNewFile();
        PrintWriter writer = new PrintWriter(path_developer, StandardCharsets.UTF_8);
        writer.print(new BufferedReader(
                new InputStreamReader(getFileFromResourceAsStream("vas/vas/instructions/instruction.txt"), StandardCharsets.UTF_8))
                .lines()
                .collect(Collectors.joining("\n")));
        writer.close();
        generated_file();
    }

    public static boolean developer_file_exist(){
        File file = new File(path_developer);
        if(file.exists()){
            return true;
        }
        else return false;

    }

    public static void developer_mode(Button mode){
        if(path_developer.equals("")){
            try {
                developer_path();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        file_already_exists = developer_file_exist();

        if(Instruction_Mode && !file_already_exists){
            generating_file();
            try {
                generate_developer_file();
            } catch (IOException e) {
                e.printStackTrace();
            }
            mode.setText("Developer Mode");
            Instruction_Mode = developer_mode;
        }
        else if(Instruction_Mode){
            mode.setText("Developer Mode");
            Instruction_Mode = developer_mode;
        }
        else {
            mode.setText("Normal Mode");
            Instruction_Mode = classic_mode;
        }

    }
}
