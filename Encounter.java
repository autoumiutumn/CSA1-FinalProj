import java.util.Scanner;
import java.util.ArrayList;

public class Encounter {
    // Static Attributes
    private static int initCount;
    public static int getInitCount() {return initCount;}
    public static void setInitCount(int value) {initCount = value;}

    private static int roundCount;
    public static int getroundCount() {return roundCount;}
    public static void setroundCount(int value) {roundCount = value;}

    // // private static ArrayList<String[]> battleMatrix = new ArrayList<String[]>();
    
    // // Battle Setup
    // public static void battleSetup() {
    //     // int Init, String enType, int maxHP, int HP
    //     String[] pEMaker  = {"-1", "player", "-1", "-1"};
    //     String[] e1EMaker = {"-1", "", "-1", "-1"};
    //     String[] e2EMaker = {"-1", "", "-1", "-1"};
    //     String[] e3EMaker = {"-1", "", "-1", "-1"};

    // }


    public static void runEncounter(Charas player, Charas enemy1 /*, Charas enemy2 */){
        Room.setisEnco(2);
        // Set initiative
        player.setInit(Calcu.roll(1, 20));
        enemy1.setInit(Calcu.roll(1, 20));
        initCount = 1;
        roundCount = 1;

        // DESC Desc.encounter_start
        System.out.println("> BATTLE START! \n > -----------");
        while ((player.getCHP() > 0 && enemy1.getCHP() > 0) || (roundCount >= 20)){
            if (initCount == player.getInit()){
                // Player Turn

                // Print Stuff
                System.out.println("> It's your turn!");
                System.out.println("> Items Available Are: ");
                Items.listInv(player.getInv(), true);
                System.out.println("> Type item's number to use");
                Scanner battleInput = new Scanner(System.in);
                // Using Item
                int itemUsedInt = Integer.parseInt(battleInput.nextLine());
                String[] itemUsed = player.getInv().get(itemUsedInt);
                if (Items.getClas(itemUsed).equals("w")){
                    // Use desc Items.getDescType(itemUsed)
                    System.out.println("> Using weapon " + Items.getDName(itemUsed)); // DESC
                    if (Calcu.rollToHit(itemUsed) >= enemy1.getArmor()){
                        System.out.println("> Hit!");
                        enemy1.ChangeHP(Calcu.TripRoll(itemUsed), false);
                    } else {
                        System.out.println("> Missed!");
                    }
                } else if (Items.getClas(itemUsed).equals("d")){
                    // Use desc Items.getDescType(itemUsed)
                    System.out.println("> Using item " + Items.getDName(itemUsed)); // DESC
                    player.ChangeHP(Calcu.TripRoll(itemUsed), true);
                }
                battleInput.close();
            }
            if (initCount == enemy1.getInit()){
                // Enemy Turn

                System.out.println("> It's the enemy's turn!");
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
                        player.ChangeHP(Calcu.TripRoll(itemUsed), false);
                    } else {
                        System.out.println("> Missed!");
                    }
                }
            }
            initCount++;
            if (initCount > 20){
                initCount = 1;
            }
        }
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
}
