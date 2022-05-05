package vas.vas.Support;

import javafx.scene.control.Button;
import vas.vas.Hollow;

import java.io.*;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

import static vas.vas.Assem.Elaboration.getFileFromResourceAsStream;
import static vas.vas.Support.pop_up.generated_file;
import static vas.vas.Support.pop_up.generating_file;

public class Others {
    public static boolean developer_mode = false;
    public static boolean classic_mode = true;
    public static boolean Instruction_Mode;

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
        if(!Download_list.internet_check()){
            writer.print(new BufferedReader(
                    new InputStreamReader(getFileFromResourceAsStream("vas/vas/others/instruction.txt"), StandardCharsets.UTF_8))
                    .lines()
                    .collect(Collectors.joining("\n")));
        }
        writer.print(Download_list.instructions);
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
            mode.setText(LngDefines.LNG_Developer_Mode_using);
            Instruction_Mode = developer_mode;
        }
        else if(Instruction_Mode){
            mode.setText(LngDefines.LNG_Developer_Mode_using);
            Instruction_Mode = developer_mode;
        }
        else {
            mode.setText(LngDefines.LNG_Normal_Mode_using);
            Instruction_Mode = classic_mode;
        }

    }

    public static boolean settings_exists(){
        String dataFolder = System.getProperty("user.home") + "\\.Vassembler\\settings";
        File file = new File(dataFolder);
        return file.exists();
    }

    public static void duplicate_settings() throws IOException {
        String dataFolder = System.getProperty("user.home") + "\\.Vassembler";
        File file = new File(dataFolder);
        file.mkdirs();
        File f = new File(dataFolder + "\\settings");
        PrintWriter writer = new PrintWriter(f, StandardCharsets.UTF_8);
        writer.print(new BufferedReader(
                new InputStreamReader(getFileFromResourceAsStream("vas/vas/others/settings"), StandardCharsets.UTF_8))
                .lines()
                .collect(Collectors.joining("\n")));
        writer.close();
    }

    public static void settings_file() throws IOException {
        String dataFolder = System.getProperty("user.home") + "\\.Vassembler";
        File file = new File(dataFolder + "\\settings");
        InputStream inputStream = new FileInputStream(file);
        String settings = new BufferedReader(
                new InputStreamReader(inputStream, StandardCharsets.UTF_8))
                .lines()
                .collect(Collectors.joining("\n"));
        if(settings.contains("en")){
            LngDefines.LNG_active = LngDefines.LNG_EN;
            LngDefines.Inglese();
        }
        else{
            LngDefines.LNG_active = LngDefines.LNG_IT;
            LngDefines.Italiano();
        }

        if(settings.contains("developer")){
            Instruction_Mode = developer_mode;
        }
        else Instruction_Mode = classic_mode;
    }

    public static void change_language_settings() throws FileNotFoundException {
        String dataFolder = System.getProperty("user.home") + "\\.Vassembler";
        File file = new File(dataFolder + "\\settings");
        InputStream inputStream = new FileInputStream(file);
        String settings = new BufferedReader(
                new InputStreamReader(inputStream, StandardCharsets.UTF_8))
                .lines()
                .collect(Collectors.joining("\n"));
        if(settings.contains("en")){
            settings = settings.replace("en", "it");
        }
        else settings = settings.replace("it", "en");
        try {
            PrintWriter writer = new PrintWriter(file, StandardCharsets.UTF_8);
            writer.print(settings);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void change_mode_settings() throws FileNotFoundException {
        String dataFolder = System.getProperty("user.home") + "\\.Vassembler";
        File file = new File(dataFolder + "\\settings");
        InputStream inputStream = new FileInputStream(file);
        String settings = new BufferedReader(
                new InputStreamReader(inputStream, StandardCharsets.UTF_8))
                .lines()
                .collect(Collectors.joining("\n"));
        if(settings.contains("developer")){
            settings = settings.replace("developer", "classic");
            Hollow.normal_style();
        }
        else {
            settings = settings.replace("classic", "developer");
            Hollow.developer_style();
        }
        try {
            PrintWriter writer = new PrintWriter(file, StandardCharsets.UTF_8);
            writer.print(settings);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
