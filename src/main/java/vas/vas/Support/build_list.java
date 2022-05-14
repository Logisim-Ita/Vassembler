package vas.vas.Support;

import javafx.scene.control.TextArea;
import vas.vas.Assem.Read;

import java.io.*;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

import static vas.vas.Assem.Elaboration.getFileFromResourceAsStream;

public class build_list {
    public static void list(TextArea hexArea){
        hexArea.setText(new BufferedReader(
                new InputStreamReader(getFileFromResourceAsStream("vas/vas/others/instruction.txt"), StandardCharsets.UTF_8))
                .lines()
                .collect(Collectors.joining("\n")));
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
}
