package vas.vas.Support;

public class build_list {

    public static void list(){
        String buffer = File.full_file_reader("instructions.txt");
        System.out.println(buffer);
    }
}
