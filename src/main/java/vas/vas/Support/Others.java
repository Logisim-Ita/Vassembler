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

    public static boolean settings_exists(){
        String dataFolder = System.getProperty("user.home") + File.separator + ".Vassembler"+ File.separator +"settings";
        File file = new File(dataFolder);
        System.out.println(file.exists());
        return file.exists();
    }

    public static String using_list() throws IOException {
        String instructionSet;
        if(Others.Instruction_Mode == Others.developer_mode){
            if(Others.path_developer.equals("")){
                Others.developer_path();
            }
            if(!Others.developer_file_exist()){
                pop_up.generating_file();
                Others.generate_developer_file();
            }
            instructionSet = new BufferedReader(
                    new InputStreamReader(new FileInputStream(Others.path_developer), StandardCharsets.UTF_8))
                    .lines()
                    .collect(Collectors.joining("\n"));
        }
        else if (Others.Instruction_Mode == Others.classic_mode && !Download_list.internet_check() && Others.instructions_exists()){
            instructionSet = Others.read_instruction_file();
        }
        else if(Others.Instruction_Mode == Others.classic_mode && !Download_list.internet_check() && !Others.instructions_exists()){
            instructionSet = new BufferedReader(
                    new InputStreamReader(getFileFromResourceAsStream("vas/vas/others/instruction.txt"), StandardCharsets.UTF_8))
                    .lines()
                    .collect(Collectors.joining("\n"));
        }
        else{
            instructionSet = Download_list.instructions;
        }
        return instructionSet;
    }

    public static boolean instructions_exists(){
        String dataFolder = System.getProperty("user.home") + File.separator + ".Vassembler"+ File.separator +"instruction.txt";
        File file = new File(dataFolder);
        System.out.println(file.exists());
        return file.exists();
    }

    public static String read_instruction_file() {
        try{
            String dataFolder = System.getProperty("user.home") + File.separator + ".Vassembler"+ File.separator +"instruction.txt";
            File file = new File(dataFolder);
            BufferedReader br = new BufferedReader(new FileReader(file));
            String st;
            StringBuilder sb = new StringBuilder();
            while ((st = br.readLine()) != null) {
                sb.append(st);
                sb.append("\n");
            }
            br.close();
            return sb.toString();
        }
        catch (IOException e){
            return "";
        }
    }
    public static void check_insructions_update() throws IOException {
        String dataFolder = System.getProperty("user.home") + File.separator + ".Vassembler";
        File file = new File(dataFolder);
        file.mkdir();
        if(Download_list.internet_check() && !read_instruction_file().equals(Download_list.instructions)){
            File f = new File(dataFolder + File.separator + "instruction.txt");
            PrintWriter writer = new PrintWriter(f, StandardCharsets.UTF_8);
            writer.print(Download_list.instructions);
            writer.close();
        }
        else if(!read_instruction_file().equals(Download_list.instructions)){
            generate_file(System.getProperty("user.home") + File.separator + ".Vassembler"+ File.separator +"instruction.txt");
        }
    }

    public static void generate_developer_file() throws IOException {
        if(Download_list.internet_check()){
            File f = new File(path_developer);
            PrintWriter writer = new PrintWriter(f, StandardCharsets.UTF_8);
            writer.print(Download_list.instructions);
            writer.close();
        }
        else if(instructions_exists()){
            File f = new File(path_developer);
            PrintWriter writer = new PrintWriter(f, StandardCharsets.UTF_8);
            writer.print(read_instruction_file());
            writer.close();
        }
        else generate_file(path_developer);
    }

    public static void generate_file(String path_to) throws IOException {
        String dataFolder = System.getProperty("user.home") + File.separator + ".Vassembler";
        File file = new File(dataFolder);
        file.mkdir();
        PrintWriter writer = new PrintWriter(path_to, StandardCharsets.UTF_8);
        if(!Download_list.internet_check()){
            writer.print(new BufferedReader(
                    new InputStreamReader(getFileFromResourceAsStream("vas/vas/others/instruction.txt"), StandardCharsets.UTF_8))
                    .lines()
                    .collect(Collectors.joining("\n")));
        }
        writer.print(Download_list.instructions);
        writer.close();
        if(path_to.equals(path_developer)) generated_file();
    }

    public static boolean developer_file_exist(){
        File file = new File(path_developer);
        return file.exists();
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

    public static void duplicate_settings() throws IOException {
        String dataFolder = System.getProperty("user.home") + File.separator + ".Vassembler";
        File file = new File(dataFolder);
        file.mkdir();
        File f = new File(dataFolder + File.separator + "settings");
        PrintWriter writer = new PrintWriter(f, StandardCharsets.UTF_8);
        writer.print(new BufferedReader(
                new InputStreamReader(getFileFromResourceAsStream("vas/vas/others/settings"), StandardCharsets.UTF_8))
                .lines()
                .collect(Collectors.joining("\n")));
        writer.close();
    }

    public static void settings_file() throws IOException {
        String dataFolder = System.getProperty("user.home") + File.separator + ".Vassembler";
        File f = new File(dataFolder);
        f.mkdir();
        File file = new File(dataFolder + File.separator + "settings");
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
        String dataFolder = System.getProperty("user.home") + File.separator + ".Vassembler";
        File file = new File(dataFolder + File.separator + "settings");
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
        String dataFolder = System.getProperty("user.home") + File.separator + ".Vassembler";
        File file = new File(dataFolder + File.separator + "settings");
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