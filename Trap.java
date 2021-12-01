public class Trap {
    // trapEnc(Charas player, int dif, boolean defuseAttempt)
    public static void trapEnc(Charas player, int dif, boolean defuseAttempt) {
        if (defuseAttempt){
            if (Calcu.roll(1, 20) >= dif * 5){
                // Desc.trap_def_Success
            } else {
                // Desc.trap_def_Fail
                player.ChangeHP(Calcu.roll(dif * 2, 10), true);
            }

        } else {
            System.out.println("<<TRAP TRIGGERED>>");
            // Desc.trapHit
            player.ChangeHP(Calcu.roll(dif * 2, 8), true);
            
        }
        Room.setIsTrap(3);
    }
}
