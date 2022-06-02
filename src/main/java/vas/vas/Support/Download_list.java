package vas.vas.Support;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class Download_list {
    public static String instructions;
    //Function to read the instructions form github
    /*
    public static void update_list() throws IOException {
        BufferedInputStream in = new BufferedInputStream(new URL("https://github.com/Logisim-Ita/Vassembler/releases/download/Instructions-latest/instruction.txt").openStream());
        byte[] data = new byte[1024];
        int numRead;
        String str = "";
        while ((numRead = in.read(data)) != -1) {
            str = new String(data, 0, numRead);
        }
        instructions = str;
        System.out.println(instructions);
    }*/

    public static void update_list(){
        try {
            URL url = new URL("https://github.com/Logisim-Ita/Vassembler/releases/download/Instructions-latest/instruction.txt");
            Scanner s = new Scanner(url.openStream());
            StringBuilder str = new StringBuilder();
            while (s.hasNext()) {
                str.append(s.nextLine()).append("\n");
            }
            instructions = str.toString();
            System.out.println(instructions);

        }
        catch(IOException ex) {

            ex.printStackTrace();
        }
    }

    public static boolean internet_check(){
        try {
            URL url = new URL("http://www.google.com");
            URLConnection connection = url.openConnection();
            connection.connect();
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
