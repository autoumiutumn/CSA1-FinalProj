public class Trap {
    // trapEnc(Charas player, int dif, boolean defuseAttempt)
    public static void trapEnc(Charas player, int dif, boolean defuseAttempt) {
        if (defuseAttempt){
            if (Calcu.roll(1, 20) >= dif * 5){
                System.out.println("> After a tense moment, the trap was disabled.");
            } else {
                System.out.println("> During disarming, the trap went off!");
                player.ChangeHP(Calcu.roll(dif * 2, 10), true);
            }

        } else {
            System.out.println("> Suddenly, a trap went off!");
            // Desc.trapHit
            player.ChangeHP(Calcu.roll(dif * 2, 8), true);
            
        }
        Room.setIsTrap(3);
    }
}
