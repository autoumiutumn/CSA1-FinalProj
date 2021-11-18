import java.util.ArrayList;

// As a note, character class is more of a character "slot"
// Battle code will take random enemy from list, plug it into slot

public class Charas {
    // Attributes
    private String type;
    public String getType() {return type;}

    private int cHP;
    public int getCHP() {return cHP;}
    public void setCHP(int nCHP) {cHP = nCHP;}

    private int maxHP;
    public int getMHP() {return maxHP;}

    private ArrayList<String[]> inv = new ArrayList<String[]>();
    public ArrayList<String[]> getInv() {return inv;}

    private int coin;
    public int getCoin(){return coin;}
    public void setCoin(int nCoin) {coin = nCoin;}
    public void modCoin(int delta) {coin += delta;}


    public Charas(String nType, int nMaxHP, ArrayList<String[]> nInv){
        type = nType;
        cHP = nMaxHP;
        maxHP = nMaxHP;
        nInv = inv;
    }

    public Charas(String nType){
        type = nType;
        cHP = 1;
        maxHP = 1;
    }

    public void pChangePHP(int deltaHP, boolean isPos){
        if (isPos){
            System.out.println("> You take " + deltaHP + " damage!");
            this.setCHP(this.getCHP() - deltaHP);
            System.out.println("> Your HP is now " + this.getCHP() + ".");
        } else {
            System.out.println("> You heal " + deltaHP + "HP!");
            this.setCHP(this.getCHP() + deltaHP);
            System.out.println("> Your HP is now " + this.getCHP() + ".");
        }
    }
}
