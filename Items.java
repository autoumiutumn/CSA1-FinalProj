import java.util.ArrayList;
public class Items {
    private static ArrayList<String[]> weapons = new ArrayList<String[]>();
    private static ArrayList<String[]> disp = new ArrayList<String[]>();
    private static String getClas(String[] targItem){
        return targItem[0];
    }
    private static String getName(String[] targItem){
        return targItem[1];
    }
    private static String getDName(String[] targItem){
        return targItem[2];
    }
    private static int getAtMod(String[] targItem){
        if (targItem[0].equals("w")) {
            return Integer.parseInt(targItem[3]);
        } else {
            System.out.println("ERROR, INCORRECT CLAS");
            return -1;
        }
    }
    private static int getStock(String[] targItem){
        if (targItem[0].equals("d")) {
            return Integer.parseInt(targItem[3]);
        } else {
            System.out.println("ERROR, INCORRECT CLAS");
            return -1;
        }
    }
    private static int getIRollAmo(String[] targItem){
        return Integer.parseInt(targItem[4]);
    }
    private static int getIRollType(String[] targItem){
        return Integer.parseInt(targItem[5]);
    }
    private static int getIRollMod(String[] targItem){
        return Integer.parseInt(targItem[6]);
    }
    private static String getType(String[] targItem){
        return targItem[7];
    }
    private static String getDescType(String[] targItem){
        return targItem[8];
    }



    public static void weaponSetup() {
        //                 0     1     2                                        3       4       5       6           7      8
        //               clas, name, dispName,                              || atMod, dRollN, dRollT, dRollMod || type, descType
        String[] wnMaker = {"w", "", "",                                 /* */ "", "", "", "",              /* */ "", ""};
        String[] w1Maker = {"w", "playerSword1", "Stun Sword MK1",       /* */ "6", "4", "6", "0",          /* */ "melee", "melee1"};
        String[] w2Maker = {"w", "playerRanged1", "Sparkbolt",           /* */ "5", "3", "10", "3",          /* */ "ranged", "ranged1"};
        String[] w3Maker = {"w", "playerSword2", "Stun Sword MK2",       /* */ "8", "6", "6", "0",              /* */ "", ""};
        String[] w4Maker = {"w", "playerRanged2", "N.E.S.S",             /* */ "7", "4", "12", "0",              /* */ "", ""};
        String[] w5Maker = {"w", "playerRanged3", "Horseshoe Blaster",   /* */ "3", "10", "4", "10",              /* */ "", ""};

        String[] w6Maker = {"w", "baseSword", "Energy Pickaxe",  "4", "2", "6", "3",          /* */ "melee", "melee1"};
        String[] w7Maker = {"w", "baseRanged", "Broken Crossbow",  "4", "2", "6", "3",          /* */ "melee", "melee1"};


        weapons.add(w1Maker);
        weapons.add(w2Maker);
    }

    public static void dispSetup() {
        //                 0     1     2         3                               4       5       6           7      8
        //                clas, name, dispName, stock                      ||  hRollT, hRollN, hRollMod || type, descType   
        String[] d1Maker = {"d", "healKit", "Medical Kit", "1",         /* */ "2", "8", "0",         /* */ "healBasic", "lightHeal"};
        String[] d2Maker = {"d", "medPak", "Adv. Medical Pack", "1",    /* */ "6", "6", "0",         /* */ "healAdv", "heavyHeal"};

        disp.add(d1Maker);
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
                boolean found = false;
                for (int j = 0; j < sum.size(); j++){
                    if (sum.get(j)[1].equals(loot.get(i)[1])) {
                        found = true;
                        sum.get(j)[3] = Integer.toString(Integer.parseInt(sum.get(j)[3]) + Integer.parseInt(loot.get(i)[3]));
                    }
                }
                if (!found){
                    sum.add(loot.get(i));
                }
            }
            
        }
        return sum;  
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
    public static String getWeaponRollDisplay(String[] targetItem){
        return Integer.toString(getIRollAmo(targetItem)) + Integer.toString(getIRollType(targetItem)) + "+" + Integer.toString(getIRollMod(targetItem));
    }
    public static String getWeaponHitDataDisplay(String[] targetItem){
        return "+" + Integer.toString(getAtMod(targetItem));
    }
}
