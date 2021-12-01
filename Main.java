import java.util.Scanner;
import java.util.ArrayList;

public class Main{
    public static void main(String[] args) {
        // Startup Creation
        Room.roomMaker();
        Items.weaponSetup();
        Items.dispSetup();
        descriptMaker();

        // Main Game Loop
        ArrayList<String[]> plBuild = new ArrayList<String[]>();
        Items.addItem(plBuild, "playerSword1");
        Items.addItem(plBuild, "playerRanged1");
        Charas player = new Charas("player", "Player", 100, 15, plBuild, 0);
        Charas enemy1 = new Charas("npc");
        // Charas enemy2 = new Charas("npc"); 
        // Unused for sake of time / balance
        
        //  Game Start Up
        System.out.println("> The roar of your boosters fades as the ship touches down. " + 
        "Running EVA protocols without any telecomms to keep you company is a lonely job, but you're out of the cramped cockpit soon enough. \n" + 
        "> Standing before you, the large blast doors of the mine are cracked open. MISSION BEGIN!");
        Room.goTo(1);
        int loopTestCounter = 0;
        while (true){
            loopTestCounter++;
            if (loopTestCounter > 1000) break;
            
            /*
            Order of Ops: 
            If there is an encounter, encounter code will run FIRST
            If none/after encounter, general code will run. 
                If trap, trap code will run unless "Search" command used
            Code disables encounter, trap, and loot code after use 
                (0 for nothing, 1 for untriggered event, 2 for ongoing event (?), 3 for "spent" event)
            If Door Command Used, run Door Code
            */
            if (Room.getisEnco() == 1){
                enemy1.encounterResetEnemy();
                enemy1.encounterCreateEnemy(Room.getEncoDif());
                Encounter.runEncounter(player, enemy1);
                

            }
            
            // If encounter == 1, run encounter code
            System.out.println("> " + Room.getTDesc(Room.getCRID()));
            System.out.println("> COMMANDS");
            System.out.println("> Type SEARCH to search for traps; Type LOOT to interact with loot;" + 
            " Type DOOR (NORTH, SOUTH, EAST, or WEST) to use that door.");
           // Explore Room Loop
            while (true /*PLAYER HEALTH > 0*/) {
                int RLCurrentRoom = Room.getCRID();
                Scanner input = new Scanner(System.in);
                String inputS = (input.nextLine()).toUpperCase();
                input.close();
                if (inputS.matches("<STOP>")) {
                    System.out.println("<<< CLOSING PROGRAM >>>");
                    System.exit(0);
                }

                if (Room.getIsTrap() == 1){
                    if (inputS.contains("SEARCH")){
                        Trap.trapEnc(player, Room.getTrapDif(), true);
                    } else {
                        Trap.trapEnc(player, Room.getTrapDif(), false);
                    }
                } else {
                    if (inputS.contains("SEARCH")){
                        System.out.println("> But there was nothing to find.");
                    }

                    else if (inputS.contains("LOOT")){
                        // LOOT CODE
                    }

                    else if (inputS.contains("DOOR")){
                        String tempInput = (inputS.substring((inputS.indexOf("DOOR ") + 5)));
                        switch (tempInput) {
                            case "NORTH":
                                if (Room.getDN1() != -1){
                                    Room.goTo(Room.getDN1());
                                } else {
                                    System.out.println("> Door not found");
                                }
                                break;
                            case "SOUTH":
                                if (Room.getDS1() != -1){
                                    Room.goTo(Room.getDS1());
                                } else {
                                    System.out.println("> Door not found");
                                }
                                break;
                            case "EAST":
                                if (Room.getDE1() != -1){
                                    Room.goTo(Room.getDE1());
                                } else {
                                    System.out.println("> Door not found");
                                }
                                break;
                            case "WEST":
                                if (Room.getDW1() != -1){
                                    Room.goTo(Room.getDW1());
                                } else {
                                    System.out.println("> Door not found");
                                }
                                break;
                            default:
                                System.out.println("Door not found");
                                break;
                        }
                    }
                }
                // Exit RL Code
                if (RLCurrentRoom != Room.getCRID()) {
                    break;
                }
            }
        }
    }

    private static void descriptMaker(){
        
    }
}