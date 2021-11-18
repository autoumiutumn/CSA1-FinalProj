import java.util.ArrayList;
public class InvAddTest {
    public static void main(String[] args) {
        
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
                        sum.get(j)[4] = Integer.toString(Integer.parseInt(sum.get(j)[4]) + Integer.parseInt(loot.get(i)[4]));
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
