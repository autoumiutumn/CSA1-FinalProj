import java.util.ArrayList;
public class Items {
    private static ArrayList<String[]> weapons = new ArrayList<String[]>();
    private static ArrayList<String[]> disp = new ArrayList<String[]>();
    public static String getClas(String[] targItem){
        return targItem[0];
    }
    public static String getName(String[] targItem){
        return targItem[1];
    }
    public static String getDName(String[] targItem){
        return targItem[2];
    }
    public static int getAtMod(String[] targItem){
        if (targItem[0].equals("w")) {
            return Integer.parseInt(targItem[3]);
        } else {
            System.out.println("ERROR, INCORRECT CLAS");
            return -1;
        }
    }
    public static int getStock(String[] targItem){
        if (targItem[0].equals("d")) {
            return Integer.parseInt(targItem[3]);
        } else {
            System.out.println("ERROR, INCORRECT CLAS");
            return -1;
        }
    }
    public static int getIRollAmo(String[] targItem){
        return Integer.parseInt(targItem[4]);
    }
    public static int getIRollType(String[] targItem){
        return Integer.parseInt(targItem[5]);
    }
    public static int getIRollMod(String[] targItem){
        return Integer.parseInt(targItem[6]);
    }
    public static String getType(String[] targItem){
        return targItem[7];
    }
    public static String getDescType(String[] targItem){
        return targItem[8];
    }



    public static void weaponSetup() {
        //                 0     1     2                                        3       4       5       6           7      8
        //               clas, name, dispName,                              || atMod, dRollN, dRollT, dRollMod || type, descType
      //String[] wnMaker = {"w", "", "",                                 /* */ "", "", "", "",              /* */ "", ""};
        String[] w1Maker = {"w", "playerSword1", "Stun Sword MK1",       /* */ "6", "4", "6", "0",          /* */ "melee", "melee1"};
        String[] w2Maker = {"w", "playerRanged1", "Sparkbolt",           /* */ "5", "3", "10", "3",          /* */ "ranged", "ranged1"};
        String[] w3Maker = {"w", "playerSword2", "Stun Sword MK2",       /* */ "8", "6", "6", "0",              /* */ "melee", "melee2"};
        String[] w4Maker = {"w", "playerRanged2", "N.E.S.S",             /* */ "7", "4", "12", "0",              /* */ "ranged", "ranged2"};
        String[] w5Maker = {"w", "playerRanged3", "Horseshoe Blaster",   /* */ "3", "10", "4", "10",              /* */ "ranged", "ranged2"};

        String[] w6Maker = {"w", "enemySword", "Energy Pickaxe",  "4", "5", "4", "2",          /* */ "melee", "melee1E"};
        String[] w7Maker = {"w", "enemyRanged", "Broken Crossbow",  "6", "3", "8", "3",          /* */ "ranged", "ranged1E"};


        weapons.add(w1Maker);
        weapons.add(w2Maker);
        weapons.add(w3Maker);
        weapons.add(w4Maker);
        weapons.add(w5Maker);
        weapons.add(w6Maker);
        weapons.add(w7Maker);
    }

    public static void dispSetup() {
        //                 0     1     2         3                               4       5       6           7      8
        //                clas, name, dispName, stock                      ||  hRollT, hRollN, hRollMod || type, descType   
        String[] d1Maker = {"d", "healKit", "Medical Kit", "1",         /* */ "2", "8", "0",         /* */ "healBasic", "lightHeal"};
        String[] d2Maker = {"d", "medPak", "Adv. Medical Pack", "1",    /* */ "6", "6", "0",         /* */ "healAdv", "heavyHeal"};

        disp.add(d1Maker);
        disp.add(d2Maker);
    }

    // Methods

    public static ArrayList<String[]> lootAdd(ArrayList<String[]> target, ArrayList<String[]> loot) {
        ArrayList<String[]> sum = new ArrayList<String[]>();
        for (int i = 0; i < target.size(); i++){
            sum.add(target.get(i));
        }
        for (int i = 0; i < loot.size(); i++) { 		      
            if (loot.get(i)[0].matches("w")) {
                sum.add(loot.get(i));
            } else if (loot.get(i)[0].matches("d")){
                sum.add(loot.get(i));
            }
            
        }
        return sum;  
    }
    
    public static ArrayList<String[]> addItem(ArrayList<String[]> invent, String itemname){
        ArrayList<String[]> nInvent = invent;
        boolean found = false;
        for (int i = 0; i < Items.weapons.size(); i++){
            if (found) break;
            if (weapons.get(i)[1].equals(itemname)){
                nInvent.add(weapons.get(i));
                found = true;
            }
        }
        for (int j = 0; j< Items.disp.size(); j++){
            if (found) break;
            if (disp.get(j)[1].equals(itemname)){
                nInvent.add(disp.get(j));
                found = true;
            }
        }
        if (!found) {
            System.out.println("DEBUG - ITEM \"" + itemname + "\" NOT FOUND");
        }
        return nInvent;
    }

    public static int getWeaponMin(String[] targetItem){
        return getIRollAmo(targetItem) + getIRollMod(targetItem);
    }
    public static int getWeaponMax(String[] targetItem){
        return (getIRollAmo(targetItem) * getIRollType(targetItem))+ getIRollMod(targetItem);
    }
    public static double getWeaponAvg(String[] targetItem){
        return ((((double) getIRollType(targetItem)) + 1.0) / 2.0) * (double) getIRollAmo(targetItem);
    }
    public static double getWeaponDev(String[] targetItem){
        double RollA = (double) getIRollAmo(targetItem);
        double RollT = (double) getIRollType(targetItem);
        return Math.sqrt(RollA*(Math.pow(RollT,2.0)-1.0))/(2.0*Math.sqrt(3.0));
    }
    public static String getWRollDisplay(String[] targetItem){
        return Integer.toString(getIRollAmo(targetItem)) + Integer.toString(getIRollType(targetItem)) + "+" + Integer.toString(getIRollMod(targetItem));
    }
    public static String getWToHitDisplay(String[] targetItem){
        return "+" + Integer.toString(getAtMod(targetItem));
    }

    public static String getInfo(String[] targetItem){
        return "[Accuracy - " + getWToHitDisplay(targetItem) + ", Damage Average - " + getWeaponAvg(targetItem)+", Damage Deviation - " + getWeaponDev(targetItem) + "]";
    }

    public static void listInv(ArrayList<String[]> inv, boolean choosing) {
        for (int i = 0; i < (inv).size(); i++){
            System.out.print("> ");
            if (choosing){System.out.print("[" + i +"] ");}
            System.out.println(Items.getDName(inv.get(i)));
            if (Items.getClas(inv.get(i)).equals("d")){
                System.out.println(" x" + Items.getStock(inv.get(i)));
            }
            if (Items.getClas(inv.get(i)).equals("w")){
                System.out.println("> " + Items.getInfo(inv.get(i)) + "\n");
            }
        }
    }
    
}
