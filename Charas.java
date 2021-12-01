import java.util.ArrayList;

// As a note, character object is more of a character "slot"
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
    public void setInv(ArrayList<String[]> nInv) {inv = nInv;}

    private int coin;
    public int getCoin(){return coin;}
    public void setCoin(int nCoin) {coin = nCoin;}
    public void modCoin(int delta) {coin += delta;}

    private int armor;
    public int getArmor() {return armor;}

    private String displName;
    public String getDisplName() {return displName;}

    private int init;
    public int getInit() {return init;}
    public void setInit(int nInit) {init = nInit;}


    public Charas(String nType, String name, int nMaxHP, int nArmor, ArrayList<String[]> nInv, int nCoin){
        type = nType;
        cHP = nMaxHP;
        maxHP = nMaxHP;
        inv = nInv;
        coin = nCoin;
        armor = nArmor;
        displName = name;
        init = -1;
    }

    public Charas(String nType){
        type = nType;
        cHP = 1;
        maxHP = 1;
    }

    public void ChangeHP(int deltaHP, boolean isPos){
        if (isPos){
            System.out.println("> "+ this.displName + " takes " + deltaHP + " damage!");
            this.setCHP(this.getCHP() - deltaHP);
            System.out.println("> "+ this.displName + "\'s HP is now " + this.getCHP() + ".");
        } else {
            System.out.println("> "+ this.displName + " heals " + deltaHP + " damage!");
            this.setCHP(this.getCHP() + deltaHP);
            System.out.println("> "+ this.displName + "\'s HP is now " + this.getCHP() + ".");
        }
    }

    public void encounterCreateEnemy(int difficulty) {
        this.maxHP = Calcu.roll(3, 8) + ((30*difficulty) - 14);
        if (this.maxHP <= 0){
            this.maxHP = 1;
        }
        this.cHP = maxHP;
        this.armor = 7 + (difficulty * 3);
        int nameID = Calcu.randIntBo(1, 5);
        switch (nameID) {
            case 1:
                this.displName = "Stellar Hound";
                break;
            case 2:
                this.displName = "Astroid Gremlin";
                break;
            case 3:
                this.displName = "Moon Machine";
                break;
            case 4:
                this.displName = "Robotic Scavenger";
                break;
            default:
                this.displName = "TestName";
                break;
        }
        ArrayList<String[]> invBuild = new ArrayList<String[]>();
        if (Math.random() < 0.5) {
            invBuild = Items.addItem(invBuild, "enemySword");
        } else {
            invBuild = Items.addItem(invBuild, "enemyRanged");
        }
        int counter = (int) (Math.random() * (0.4 * difficulty + 1));
        for (int i = 0; i < counter; i++){
            Items.addItem(invBuild, "healKit");
        }
        this.inv = invBuild;
        this.coin = ((int) Math.ceil((Math.random() * (difficulty * 5)))) + 1;
        this.init = -1;
    }

    public void encounterResetEnemy() {
        type = "";
        cHP = 1;
        maxHP = 1;
        inv.clear();
        coin = -1;
        armor = -1;
        displName = "";
        init = -1;
    }

    public void encounterCreateEnemy(String name) {
        if (name.matches("Jenna") && Room.getCRID() == 27){
            this.type = "jenna";
            this.cHP = 69;
            this.maxHP = 69;
            ArrayList<String[]> invBuild = new ArrayList<String[]>();
            invBuild = Items.addItem(invBuild, "jnaRanged");
            // invBuild = Items.addItem(invBuild, "jnaFlannel");
            // invBuild = Items.addItem(invBuild, "jnaSkirt");
            invBuild = Items.addItem(invBuild, "jnaLeg");
            invBuild = Items.addItem(invBuild, "jnaShoes");
            invBuild = Items.addItem(invBuild, "jnaGlass");
            invBuild = Items.addItem(invBuild, "hrt");
            this.inv = invBuild;
            coin = 200;
            armor = 7;
            displName = "Jenna L.A";
            init = -1;
        }
    }
}
