import java.util.ArrayList;
public class Items {
    private static ArrayList<String[]> weapons = new ArrayList<String[]>();
    private static ArrayList<String[]> disp = new ArrayList<String[]>();



    public static void weaponSetup() {
        //               clas, name, dispName,                              || atMod, dRollT, dRollN, dRollMod || type, descType
        String[] w1Maker = {"w", "playerSword1", "Electic Sword MK1",    /* */ "8", "3", "6", "0",          /* */ "melee", "melee1"};
        String[] w2Maker = {"w", "playerRanged1", "Sparkbolt",           /* */ "5", "4", "6", "0",          /* */ "ranged", "ranged1"};
        String[] w3Maker = {"w", "baseSword", "Energy Pickaxe", };

        weapons.add(w1Maker);
        weapons.add(w2Maker);
    }

    public static void dispSetup() {
        //                clas, name, dispName, stock                      ||  hRollT, hRollN, hRollMod || type, descType   
        String[] d1Maker = {"d", "healKit", "Medical Kit", "1",         /* */ "2", "8", "0",         /* */ "healBasic", "lightHeal"};
        String[] d2Maker = {"d", "medPak", "Adv. Medical Pack", "1",    /* */ "6", "6", "0",         /* */ "healAdv", "heavyHeal"};

        disp.add(d1Maker);
    }
    
}
