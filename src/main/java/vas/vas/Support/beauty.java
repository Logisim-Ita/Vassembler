package vas.vas.Support;

public class beauty {
    public static String editor(String buffer) {
        String result = buffer;
        String tmp = "";
        result = beautyEnterTab(result);
        result = beautyTab(result);
        result = startSpace(result);
        while(!result.equals(tmp)) {
            tmp = result;
            result = beautySpace(result);
            result = beautySpaceEnter(result);
        }
        result = parentesi(result);
        result = comma(result);
        result = beautyTabD(result);
        result = beautyTabEnter(result);
        result = beautyCommentTab(result);
        result = beautyCommentEt(result);
        result = Tags(result);
        return result;
    }

    public static String beautyEnterTab(String buffer) {
        String result = buffer;
        for(int i = result.length()-1; i > 0; i--) {
            if(result.charAt(i-1) == '\n' && result.charAt(i) != '\t') {
                result = result.substring(0,i) + "\t" + result.substring(i);
            }
        }
        return result;
    }

    public static String Tags(String buffer) {
        String result = buffer;
        boolean flag = false;
        for(int i = result.length()-1; i > 0; i--) {
            if(result.charAt(i) == ':') {
                flag = true;
            }
            if(result.charAt(i) == '\t' && flag) {
                result = result.substring(0,i) + result.substring(i+1);
                flag = false;
            }
            if(result.charAt(i) == '\n') {
                flag = false;
            }
        }
        return result;
    }

    public static String beautyCommentTab(String buffer) {
        String result = buffer;
        for(int i = 0; i < result.length(); i++) {
            if(result.charAt(i) == ';' && (result.charAt(i-1) != ' ' && result.charAt(i-1) != '\t')) {
                result = result.substring(0,i) + " " + result.substring(i);
            }
            if(result.charAt(i) == ';' && result.charAt(i-1) == ' ') {
                result = result.substring(0,i) + "\t" + result.substring(i);
            }
        }
        return result;
    }

    public static String beautyTabEnter(String buffer) {
        String result = buffer;
        String tmp = "";
        while(!result.equals(tmp)) {
            tmp = result;
            result = result.replaceAll("\n ", "\n\t");
        }
        return result;
    }

    public static String beautyCommentEt(String buffer) {
        return buffer.replace(": ;",":\t\t;");
    }


    public static String beautySpaceEnter(String buffer) {
        return buffer.replace(" \n","\n");
    }

    public static String beautyTabD(String buffer) {
        return buffer.replace(" :",":");
    }


    private static String parentesi(String buffer) {
        String result = buffer;

        result = result.replaceAll("\\(\\s+", "(");
        result = result.replaceAll("\\s+\\)", ")");

        return result;
    }

    public static String comma(String buffer) {
        String result = buffer;
        int i = 0;
        while(i < result.length()) {
            if(result.charAt(i) == ' ') {
                if(result.charAt(i+1) == ',') {
                    result = result.substring(0,i) + result.substring(i+1);
                }
                else {
                    result = result.substring(0,i) + " " + result.substring(i+1);
                }
            }
            if(result.charAt(i) == ',' && result.charAt(i+1) != ' ') {
                result = result.substring(0,i+1) + " " + result.substring(i+1);
            }
            i++;
        }
        return result;
    }

    public static String startSpace(String buffer) {
        String result = buffer;
        result = result.replaceAll("^\\s+", "");
        return result;
    }

    public static String beautyTab(String buffer) {
        return buffer.replace("\t"," ");
    }

    public static String beautySpace(String buffer) {
        return buffer.replace("  "," ");
    }
}