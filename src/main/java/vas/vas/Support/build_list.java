package vas.vas.Support;

import javafx.scene.control.TextArea;

import java.io.BufferedReader;
import java.io.InputStreamReader;
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
}
