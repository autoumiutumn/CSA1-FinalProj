public class Calcu {
    private static String lastRoll = ""; 
    public static int roll(int A, int X) //AdX
    {
        lastRoll = A + "d" + X + " ( ";
        int rollSum = 0;
        for (int i = 0; i < A; i++){
            int temp1 = (int) Math.floor((Math.random() * X) + 1);
            lastRoll = lastRoll + temp1 + " | ";
            rollSum += temp1;
        }
        lastRoll = lastRoll + ") => " + rollSum;
        return rollSum;
    }

    public static String getDebug(){
        return lastRoll;
    }


    // Common Rolls
    public static int rollToHit(String[] usedWeapon) {
        return Calcu.roll(1, 20) + Items.getAtMod(usedWeapon);
    }

    public static int TripRoll(String[] usedWeapon) {
        return Calcu.roll(Items.getIRollAmo(usedWeapon), Items.getIRollMod(usedWeapon)) + Items.getIRollMod(usedWeapon);
    }
}
