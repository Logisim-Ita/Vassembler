package vas.vas.Support;

import java.util.ArrayList;
import java.util.List;

public class Abbellimento {
    static List<Character> list;

    public static String editor(String buffer){
        convert(buffer);
        enters();           //Mettere invio dopo ogni etichetta
        space_invaders();   //Rimuovere tutti gli spazzi in eccesso
        tabs_dopo_invio();  //Tabbare dopo ogni invio se non Ã¨ presente un'etichetta
        virgole();
        tabbaaaa();
        parentesi_invaders();
        StringBuilder string = new StringBuilder();
        //print
        for(int i = 0;i<list.size();i++){
            string.append(list.get(i));
        }
        return string.toString();
    }

    public static void tabs_dopo_invio(){
        for(int i=0;i<list.size();i++){
            if(list.get(i) == '\n' && list.get(i+1) != '\t'){
                if(list.get(i+1) == ' '){
                    list.remove(i+1);
                    list.add(i+1,'\t');
                }
                else if(no_qualcosa(i,':')){
                    list.add(i+1,'\t');
                }
            }
        }
    }

    public static void tabbaaaa(){
        for(int i=0;i<list.size();i++){
            if(list.get(i) == ';' && list.get(i-1) != '\t'){
                list.add(i,'\t');
                list.add(i,'\t');
                i+=2;
            }
            else if(list.get(i) == ';' && list.get(i-2) == ':' ){
                list.add(i,'\t');
                list.add(i,'\t');
                i+=2;
            }
            else if(list.get(i) == ';' && list.get(i-2) != '\t' ){
                list.add(i,'\t');
                i++;
            }
        }
    }

    public static void parentesi_invaders(){
        for(int i=0;i<list.size();i++){
            if(list.get(i) == '('){
                while(list.get(i) != ')'){
                    if(list.get(i) == ' '){
                        list.remove(i);
                    }
                    else{
                        i++;
                    }
                }
            }
        }
    }

    public static boolean no_qualcosa(int index,char charmander){
        int i = index;
        while(list.get(i) != '\n'){
            if(list.get(i) == charmander){
                return false;
            }
            i++;
        }
        return true;
    }

    public static boolean letters(int index){
        while(list.get(index) != ';'){
            if(list.get(index) != ' ' && list.get(index) != '\t'){
                return true;
            }
            index++;
        }
        return false;
    }

    public static void enters(){        //Mette degli invio dopo ogni : se non ci sono
        for(int i=0;i<list.size();i++){
            if(list.get(i) == ':' && list.get(i+1) == ';' ){
                list.add(i+1,'\t');
            }
            else if(list.get(i) == ':' && !no_qualcosa(i,';') && letters(i+1)){
                list.add(i+1,'\n');
            }
            else if(list.get(i) == ':' && !no_qualcosa(i,';') && list.get(i+1) != '\t'){
                list.add(i+1,'\t');
            }
            else if(list.get(i) == ':' && list.get(i+1) != '\n' & list.get(i+1) != '\t'){
                list.add(i+1,'\n');
            }
        }
    }

    public static void space_invaders(){
        for(int i=0;i<list.size();i++){
            if(list.get(i) == ' ' && list.get(i+1) == ' '){
                list.remove(i);
                i--;
            }
            if(list.get(0) == ' ' || list.get(0) == '\t') list.remove(0);
        }
    }

    public static void convert(String buffer){
        List<Character> list = new ArrayList<>();

        for (char ch: buffer.toCharArray()) {
            list.add(ch);
        }
        Abbellimento.list = list;

    }

    public static void virgole(){
        for(int i=0;i<list.size();i++){
            if(list.get(i) == ',' && list.get(i-1) == ' '){
                list.remove(i-1);
            }
            else if(list.get(i) == ',' && list.get(i+1) != ' '){
                list.add(i+1,' ');
            }
        }
    }
}