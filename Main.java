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
        Items.addItem(plBuild, "healKit");
        Items.addItem(plBuild, "healKit");
        
        Charas player = new Charas("player", "Player", 100, 15, plBuild, 0);
        for (int i = 0; i < player.getInv().size(); i++){
            System.out.println(Items.getDName(player.getInv().get(i)));
        }
        
        Charas enemy1 = new Charas("npc");
        // Charas enemy2 = new Charas("npc"); 
        // Unused for sake of time / balance
        
        //  Game Start Up
        Scanner input = new Scanner(System.in);
        System.out.println("> The roar of your boosters fades as the ship touches down. " + 
        "Running EVA protocols without any telecomms to keep you company is a lonely job, but you're out of the cramped cockpit soon enough. \n" + 
        "> Standing before you, the large blast doors of the mine are cracked open. MISSION BEGIN!");
        Room.goTo(1);
        int loopTestCounter = 0;
        while (true){
            loopTestCounter++;
            if (loopTestCounter > 1001) break;
            
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
                Room.setisEnco(2);
                // Set initiative
                player.setInit(Calcu.roll(1, 20));
                enemy1.setInit(Calcu.roll(1, 20));
                Encounter.setInitCount(1);
                Encounter.setRoundCount(1);

                // DESC Desc.encounter_start
                System.out.println("> BATTLE START! \n > -----------");
                waitFor(2500);
                while ((player.getCHP() > 0 && enemy1.getCHP() > 0) || (Encounter.getRoundCount() >= 20)){
                    if (Encounter.getInitCount() == player.getInit()){
                        // Player Turn

                        // Print Stuff
                        System.out.println("> It's your turn!");
                        System.out.println("> Your hp is : " + player.getCHP());
                        System.out.println("> Items Available Are: ");
                        Items.listInv(player.getInv(), true);
                        System.out.println("> Type item's number to use");
                        // Using Item
                        // String rawInput = "";
                        // input.nextLine();
                        // rawInput = input.nextLine();
                        // int itemUsedInt = Integer.parseInt(rawInput);
                        int itemUsedInt = input.nextInt();
                        input.nextLine();
                        String[] itemUsed = player.getInv().get(itemUsedInt);
                        if (Items.getClas(itemUsed).equals("w")){
                            // Use desc Items.getDescType(itemUsed)
                            System.out.println("> Using weapon " + Items.getDName(itemUsed)); // DESC
                            if (Calcu.rollToHit(itemUsed) >= enemy1.getArmor()){
                                System.out.println("> Hit!");
                                enemy1.ChangeHP(Calcu.TripRoll(itemUsed), true);
                            } else {
                                System.out.println("> Missed!");
                            }
                        } else if (Items.getClas(itemUsed).equals("d")){
                            // Use desc Items.getDescType(itemUsed)
                            System.out.println("> Using item " + Items.getDName(itemUsed)); // DESC
                            player.ChangeHP(Calcu.TripRoll(itemUsed), false);
                        }
                        System.out.println("-----------");
                        waitFor(2500);
                    }
                    if (Encounter.getInitCount() == enemy1.getInit()){
                        // Enemy Turn
                        System.out.println("> It's the enemy's turn!");
                        waitFor(2500);
                        int biIndex = -1;
                        double biAvg = 0 - Double.MAX_VALUE;
                        for (int i = 0; i < enemy1.getInv().size(); i++){
                            if (Items.getClas(enemy1.getInv().get(i)).equals("w")){
                                String[] targetItem = enemy1.getInv().get(i);
                                if (Items.getWeaponAvg(targetItem) > biAvg){
                                    biIndex = i;
                                    biAvg = Items.getWeaponAvg(targetItem);
                                }
                            }
                        }
                        if (biIndex != -1){
                            String[] itemUsed = enemy1.getInv().get(biIndex);
                            // Use desc Items.getDescType(itemUsed)
                            System.out.println("> Using weapon " + Items.getDName(itemUsed)); // DESC
                            if (Calcu.rollToHit(itemUsed) >= player.getArmor()){
                                System.out.println("> Hit!");
                                player.ChangeHP(Calcu.TripRoll(itemUsed), true);
                            } else {
                                System.out.println("> Missed!");
                            }
                        }
                        waitFor(2500);
                        System.out.println("-----------");
                    }
                    Encounter.setInitCount(Encounter.getInitCount() + 1);
                    if (Encounter.getInitCount() > 20){
                        Encounter.setInitCount(1);
                    }
                }
                waitFor(2500);
                if (player.getCHP() <= 0){
                    // Loss
                    // DESC battle_loss
                    System.exit(0);
                } else if (enemy1.getCHP() <= 0){
                    // Win
                    // DESC battle_win
                    System.out.println("> You got: ");
                    Items.listInv(enemy1.getInv(), false);
                    player.setInv(Items.lootAdd(player.getInv(), enemy1.getInv()));
                    System.out.println("> You got " + enemy1.getCoin() + " space dollars!");
                    player.modCoin(enemy1.getCoin());
                } else {
                    // Draw
                    // DESC battle_draw
                }
            }
            // If encounter == 1, run encounter code
            System.out.println("> " + Room.getTDesc(Room.getCRID()));
            System.out.println("> COMMANDS");
            System.out.println("> Type SEARCH to search for traps; Type LOOT to interact with loot;" + 
            " Type DOOR (NORTH, SOUTH, EAST, or WEST) to use that door.");
           // Explore Room Loop
            while (true /*PLAYER HEALTH > 0*/) {
                int RLCurrentRoom = Room.getCRID();
                String inputS = (input.nextLine()).toUpperCase();
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
                        if (Room.getisLooted() == 0){
                            double key = (double) Math.random() * 100;
                            ArrayList<String[]> roomInv = new ArrayList<String[]>();
                            if (key < 50.0){
                                // nothing
                                System.out.println("> You found nothing of value.");
                            } else if (key < 75.0){
                                // low heal
                                Items.addItem(roomInv, "healKit");
                            } else if (key < 87.5){
                                // high heal
                                Items.addItem(roomInv, "medPak");
                            } else {
                                // weapon
                                double key2 = (double) Math.random() * 100;
                                if (key2 < (1/3)){
                                    Items.addItem(roomInv, "playerSword2");
                                } else if (key2 < (2/3)){
                                    Items.addItem(roomInv, "playerRanged2");
                                } else {
                                    Items.addItem(roomInv, "playerRanged3");
                                }
                            }
                            System.out.println("You got: ");
                            Items.listInv(roomInv, false);
                            player.setInv(Items.lootAdd(player.getInv(), roomInv));
                        }

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
    public static void waitFor(int milli) {
        try {
            Thread.sleep(milli);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}