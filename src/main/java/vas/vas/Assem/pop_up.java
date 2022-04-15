package vas.vas.Assem;

import javax.swing.*;

public class pop_up {
    static String messaggio = "";
    public void error_load(String messaggio){
        pop_up.messaggio += messaggio;
    }

    public static void error_out(){
        if(!messaggio.equals("")) {
            JFrame jFrame = new JFrame();
            JOptionPane.showMessageDialog(jFrame,messaggio);
            messaggio = "";
        }
    }

    public static boolean check_instruction(){
        return messaggio.contains("instruction.txt");
    }
}
