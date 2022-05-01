package vas.vas.Support;

public class LngDefines {
    public static boolean LNG_EN = true;
    public static boolean LNG_IT = false;

    public static boolean LNG_active = LNG_IT;

    public static String LNG_Save_it = "Salva";
    public static String LNG_Save_en = "Save";
    public static String LNG_Save_as_it = "Salva con nome";
    public static String LNG_Save_as_en = "Save as";
    public static String LNG_Create_new_it = "Crea nuovo";
    public static String LNG_Create_new_en = "Create new";
    public static String LNG_Open_it = "Apri file asm";
    public static String LNG_Open_en = "Open file asm";
    public static String LNG_Assemble_it = "Assembla";
    public static String LNG_Assemble_en = "Assemble";
    public static String LNG_Export_Rom_it = "Esporta ROM Logisim";
    public static String LNG_Export_Rom_en = "Export ROM Logisim";
    public static String LNG_Beautify_it = "Abbellisci il codice";
    public static String LNG_Beautify_en = "Beautify the Code";
    public static String LNG_Instruction_list_it = "Lista Istruzioni";
    public static String LNG_Instruction_list_en = "Instruction List";
    public static String LNG_Normal_Mode_it = "Modalità Classica";
    public static String LNG_Normal_Mode_en = "Normal Mode";
    public static String LNG_Developer_Mode_it = "Modalità Sviluppatore";
    public static String LNG_Developer_Mode_en = "Developer Mode";
    public static String LNG_Language_it = "Lingua: Italiano";
    public static String LNG_Language_en = "Language: English";

    public static String LNG_Save_using;
    public static String LNG_Save_as_using;
    public static String LNG_Create_new_using;
    public static String LNG_Open_using;
    public static String LNG_Assemble_using;
    public static String LNG_Export_Rom_using;
    public static String LNG_Beautify_using;
    public static String LNG_Instruction_list_using;
    public static String LNG_Normal_Mode_using;
    public static String LNG_Developer_Mode_using;
    public static String LNG_Language_using;

    public static void Italiano(){
        LNG_Save_using = LNG_Save_it;
        LNG_Save_as_using = LNG_Save_as_it;
        LNG_Create_new_using = LNG_Create_new_it;
        LNG_Open_using = LNG_Open_it;
        LNG_Assemble_using = LNG_Assemble_it;
        LNG_Export_Rom_using = LNG_Export_Rom_it;
        LNG_Beautify_using = LNG_Beautify_it;
        LNG_Instruction_list_using = LNG_Instruction_list_it;
        LNG_Normal_Mode_using = LNG_Normal_Mode_it;
        LNG_Developer_Mode_using = LNG_Developer_Mode_it;
        LNG_Language_using = LNG_Language_it;
    }

    public static void Inglese(){
        LNG_Save_using = LNG_Save_en;
        LNG_Save_as_using = LNG_Save_as_en;
        LNG_Create_new_using = LNG_Create_new_en;
        LNG_Open_using = LNG_Open_en;
        LNG_Assemble_using = LNG_Assemble_en;
        LNG_Export_Rom_using = LNG_Export_Rom_en;
        LNG_Beautify_using = LNG_Beautify_en;
        LNG_Instruction_list_using = LNG_Instruction_list_en;
        LNG_Normal_Mode_using = LNG_Normal_Mode_en;
        LNG_Developer_Mode_using = LNG_Developer_Mode_en;
        LNG_Language_using = LNG_Language_en;
    }

    public static void swapLNG(){
        if(LNG_active == LNG_EN){
            LNG_active = LNG_IT;
            Italiano();
        }else{
            LNG_active = LNG_EN;
            Inglese();
        }
    }


}
