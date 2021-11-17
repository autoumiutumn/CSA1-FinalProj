import java.util.ArrayList;

public class Encounter {
    // Static Attributes
    private static int initCount;
    public static int getInitCount() {return initCount;}
    public static void setInitCount(int value) {initCount = value;}

    private static int turnCount;
    public static int getTurnCount() {return turnCount;}
    public static void setTurnCount(int value) {turnCount = value;}

    private static ArrayList<String[]> battleMatrix = new ArrayList<String[]>();
    
    // Battle Setup
    public static void battleSetup() {
        // int Init, String enType, int maxHP, int HP
        String[] pEMaker  = {"-1", "player", "-1", "-1"};
        String[] e1EMaker = {"-1", "", "-1", "-1"};
        String[] e2EMaker = {"-1", "", "-1", "-1"};
        String[] e3EMaker = {"-1", "", "-1", "-1"};
    }


    public static void runEncounter(Charas player, Charas enemy1, Charas enemy2, Charas enemy3){
        // Pull data from objects, 
    }
}
