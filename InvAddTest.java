import java.util.ArrayList;
public class InvAddTest {
    public static void main(String[] args) {
        ArrayList<String[]> pInv = new ArrayList<String[]>();
        String[] pIBuilder1 = {"w", "playerSword1", "Electic Sword MK1",    /* */ "8", "3", "6", "0",          /* */ "melee", "melee1"};
        String[] pIBuilder2 = {"d", "healKit", "Medical Kit", "2",         /* */ "2", "8", "0",         /* */ "healBasic", "lightHeal"};
        ArrayList<String[]> eInv = new ArrayList<String[]>();
        String[] eIBuilder1 = {"w", "baseSword", "Energy Pickaxe",  "4", "2", "6", "3",          /* */ "melee", "melee1"};
        String[] eIBuilder2 = {"d", "medPak", "Adv. Medical Pack", "5",    /* */ "6", "6", "0",         /* */ "healAdv", "heavyHeal"};
        String[] eIBuilder3 = {"d", "healKit", "Medical Kit", "1",         /* */ "2", "8", "0",         /* */ "healBasic", "lightHeal"};
        pInv.add(pIBuilder1);
        pInv.add(pIBuilder2);
        eInv.add(eIBuilder1);
        eInv.add(eIBuilder2);
        eInv.add(eIBuilder3);
        pInv = lootAdd(pInv, eInv);
        for (int h = 0; h < pInv.size(); h++){
            for (int g = 0; g < pInv.get(h).length; g++){
                System.out.print(pInv.get(h)[g]);
                System.out.print(" | ");
            }
            System.out.print("\n");
        }
    }
    
    public static ArrayList<String[]> lootAdd(ArrayList<String[]> target, ArrayList<String[]> loot) {
        ArrayList<String[]> sum = new ArrayList<String[]>();
        for (int i = 0; i < target.size(); i++){
            sum.add(target.get(i));
        }
        for (int i = 0; i < loot.size(); i++) { 		      
            // if w, add
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
}
