import java.util.ArrayList;

public class Room {
    // Static Attributes & Getters
    private static ArrayList<String[]> roomsL = new ArrayList<String[]>();
    
    private static int currentRoom = 1; 
    public static int getCRID() {return currentRoom;}
    private static ArrayList<Integer> pastRooms = new ArrayList<Integer>();
    public static ArrayList<Integer> getPastRooms() {
        return pastRooms;
    }
    public static boolean searchPastRooms(int iRID) {
        for (int i : pastRooms){
            if (i == iRID){
                return true;
            }
        }
        return false;
    }


    // Methods
    public static void goTo(int rIndex){
        boolean roomFound = false;
        for (int i = 0; i < pastRooms.size(); i++){
            if (i == currentRoom) roomFound = true;
        }
        if (!roomFound) {pastRooms.add(currentRoom);}
        currentRoom = rIndex;
    }

    public static void TrEnSetup(int trapGoal, int encGoal){
        ArrayList<Integer> debugTrapSet = new ArrayList<Integer>();
        ArrayList<Integer> debugEncSet = new ArrayList<Integer>();
        ArrayList<Integer> trapExclude = new ArrayList<Integer>();
        trapExclude.add(1);
        ArrayList<Integer> EncExclude = new ArrayList<Integer>();
        EncExclude.add(1);
        EncExclude.add(4);
        EncExclude.add(7);
        EncExclude.add(11);
        EncExclude.add(14);
        EncExclude.add(18);
        EncExclude.add(20);
        // From 2 - 24 (22)
        if (trapGoal <= 22){
            while (trapGoal > 0){
                int targ = Calcu.randIntBo(2, 25);
                boolean addIt = true;
                for (int i = 0; i < trapExclude.size(); i++){
                    if (targ == trapExclude.get(i)){
                        addIt = false;
                        break;
                    }
                }
                if (addIt){
                    setIsTrap(targ,1);
                    setTrapDif(targ, 1);
                    trapExclude.add(targ);
                    debugTrapSet.add(targ);
                    trapGoal--;
                }
            }
        }
        if (encGoal <= 17){
            while (encGoal > 0){
                int targ = Calcu.randIntBo(2, 25);
                boolean addIt = true;
                for (int i = 0; i < EncExclude.size(); i++){
                    if (targ == EncExclude.get(i)){
                        addIt = false;
                        break;
                    }
                }
                if (addIt){
                    setisEnco(targ,1);
                    setEncoDif(targ, 1);
                    EncExclude.add(targ);
                    debugEncSet.add(targ);
                    encGoal--;
                }
            }
        }
        int test = 1;
    }

    // Constructor (Kind of, not actual object)
    public static void roomMaker() {
        //                  RoomName RID Doors: N1    S1    E1    W1   TestDesc     
        //   isT|TD 1-3     isE|ED1-3  isLooted
//     String[] r1Maker = {"room1", "1", /**/ "-1", "-1", "3", "2", "EntryWay",        
// /**/ "0", "0", /**/ "0", "0", /**/ "0"};
//     roomsL.add(r1Maker);
//     String[] r2Maker = {"room2", "2", /**/ "-1", "-1", "1", "-1", "Trap Test",
//     /**/ "1", "3", /**/ "0", "0", /**/ "0"};
//     roomsL.add(r2Maker);
//     String[] r3Maker = {"room3", "3", /**/ "-1", "-1", "-1", "1", "Encounter Test",
//     /**/ "0", "0", /**/ "1", "1", /**/ "0"};
//     roomsL.add(r3Maker);
        String[] r1Maker = {"room1", "1", /**/ "2", "-1", "-1", "-1", "CENTER HALLWAY 01. There is a door to the north.",
        /**/ "0", "0", /**/ "0", "0", /**/ "0"};
        roomsL.add(r1Maker);
        String[] r2Maker = {"room2", "2", /**/ "17", "1", "10", "3", "CENTER HALLWAY 02. This room is a X intersection.",
        /**/ "0", "0", /**/ "0", "0", /**/ "0"};
        roomsL.add(r2Maker);
        String[] r3Maker = {"room3", "3", /**/ "4", "-1", "2", "-1", "LEFT WING HALLWAY 01. There is a door to the north and east.",
        /**/ "0", "0", /**/ "0", "0", /**/ "0"};
        roomsL.add(r3Maker);
        String[] r4Maker = {"room4", "4", /**/ "-1", "3", "-1", "5", "LEFT WING HALLWAY 02. There is a door to the west and south.",
        /**/ "0", "0", /**/ "1", "1", /**/ "0"};
        roomsL.add(r4Maker);
        String[] r5Maker = {"room5", "5", /**/ "7", "-1", "4", "6", "LEFT WING HALLWAY 03. There is a door to the north, west, and east.",
        /**/ "0", "0", /**/ "0", "0", /**/ "0"};
        roomsL.add(r5Maker);
        String[] r6Maker = {"room6", "6", /**/ "-1", "-1", "5", "-1", "LEFT WING ROOM ALPH. There is a door to the east.",
        /**/ "0", "0", /**/ "0", "0", /**/ "0"};
        roomsL.add(r6Maker);
        String[] r7Maker = {"room7", "7", /**/ "8", "5", "26", "-1", "LEFT WING HALLWAY 04. There is a door to the north and south.",
        /**/ "0", "0", /**/ "1", "2", /**/ "0"};
        roomsL.add(r7Maker);
        String[] r8Maker = {"room8", "8", /**/ "-1", "7", "-1", "9", "LEFT WING HALLWAY 05. There is a door to the west and south.",
        /**/ "0", "0", /**/ "0", "0", /**/ "0"};
        roomsL.add(r8Maker);
        String[] r9Maker = {"room9", "9", /**/ "-1", "-1", "8", "-1", "LEFT WING ROOM BETA. There is a door to the east.",
        /**/ "0", "0", /**/ "0", "0", /**/ "0"};
        roomsL.add(r9Maker);
        String[] r10Maker = {"room10", "10", /**/ "11", "-1", "-1", "2", "RIGHT WING HALLWAY 01. There is a door to the north and west.",
        /**/ "0", "0", /**/ "0", "0", /**/ "0"};
        roomsL.add(r10Maker);
        String[] r11Maker = {"room11", "11", /**/ "-1", "10", "12", "-1", "RIGHT WING HALLWAY 02. There is a door to the south and east.",
        /**/ "0", "0", /**/ "1", "1", /**/ "0"};
        roomsL.add(r11Maker);
        String[] r12Maker = {"room12", "12", /**/ "14", "25", "13", "11", "RIGHT WING HALLWAY 03. There is a door to the north, west, and east.",
        /**/ "0", "0", /**/ "0", "0", /**/ "0"};
        roomsL.add(r12Maker);
        String[] r13Maker = {"room13", "13", /**/ "-1", "-1", "-1", "12", "RIGHT WING ROOM ALPH. There is a door to the west.",
        /**/ "0", "0", /**/ "0", "0", /**/ "0"};
        roomsL.add(r13Maker);
        String[] r14Maker = {"room14", "14", /**/ "15", "12", "-1", "-1", "RIGHT WING HALLWAY 04. There is a door to the north and south.",
        /**/ "0", "0", /**/ "1", "2", /**/ "0"};
        roomsL.add(r14Maker);
        String[] r15Maker = {"room15", "15", /**/ "-1", "14", "16", "-1", "RIGHT WING HALLWAY 05. There is a door to the south and east.",
        /**/ "0", "0", /**/ "0", "0", /**/ "0"};
        roomsL.add(r15Maker);
        String[] r16Maker = {"room16", "16", /**/ "-1", "-1", "-1", "15", "RIGHT WING ROOM BETA. There is a door to the west.",
        /**/ "0", "0", /**/ "0", "0", /**/ "0"};
        roomsL.add(r16Maker);
        String[] r17Maker = {"room17", "17", /**/ "18", "2", "-1", "-1", "CENTER HALLWAY 03. There is a door to the north and south.",
        /**/ "0", "0", /**/ "0", "0", /**/ "0"};
        roomsL.add(r17Maker);
        String[] r18Maker = {"room18", "18", /**/ "19", "17", "-1", "-1", "CENTRAL CHAMBER. There is a door to the north and south.",
        /**/ "0", "0", /**/ "1", "3", /**/ "0"};
        roomsL.add(r18Maker);
        String[] r19Maker = {"room19", "19", /**/ "20", "18", "23", "21", "NORTHERN ROOM ALPH. There is a door to the north, south, east, and west.",
        /**/ "0", "0", /**/ "0", "0", /**/ "0"};
        roomsL.add(r19Maker);
        String[] r20Maker = {"room20", "20", /**/ "-1", "19", "-1", "-1", "NORTHERN ROOM BETA. There is a door to the south.",
        /**/ "0", "0", /**/ "0", "0", /**/ "0"};
        roomsL.add(r20Maker);
        String[] r21Maker = {"room21", "21", /**/ "-1", "-1", "19", "22", "NORTHERN HALLWAY WEST. There is a door to the west and east.",
        /**/ "0", "0", /**/ "1", "2", /**/ "0"};
        roomsL.add(r21Maker);
        String[] r22Maker = {"room22", "22", /**/ "-1", "-1", "21", "-1", "NORTHERN ROOM GAMMA. There is a door to the east.",
        /**/ "0", "0", /**/ "0", "0", /**/ "0"};
        roomsL.add(r22Maker);
        String[] r23Maker = {"room23", "23", /**/ "-1", "-1", "24", "19", "NORTHERN HALLWAY WEST. There is a door to the west and east.",
        /**/ "0", "0", /**/ "0", "0", /**/ "0"};
        roomsL.add(r23Maker);
        String[] r24Maker = {"room24", "24", /**/ "-1", "-1", "-1", "23", "NORTHERN ROOM DELTA. There is a door to the west.",
        /**/ "0", "0", /**/ "0", "0", /**/ "0"};
        roomsL.add(r24Maker);
        String[] r25Maker = {"room25", "25", /**/ "12", "-1", "-1", "-1", "Hi! If you're reading this, you're either reading the code, or ya found my secret! Thank you so much for playing my game! \n-Lots of love, Jenna L.",
        /**/ "0", "0", /**/ "0", "0", /**/ "0"};
        roomsL.add(r25Maker);
        String[] r26Maker = {"room26", "26", /**/ "-1", "-1", "27", "-1", "lesbiab room. for les biens.\n\n\n\nalso you're stuck here.\n:)",
        /**/ "0", "0", /**/ "0", "0", /**/ "0"};
        roomsL.add(r26Maker);
        String[] r27Maker = {"room27", "27", /**/ "-1", "-1", "18", "-1", "SECRET ROOM. You get the sense you shouldn't be here. The east wall is glowing.",
        /**/ "0", "0", /**/ "1", "3", /**/ "3"};
        roomsL.add(r27Maker);
    }


    // Room Info Getters  |  Note: Input will be target RID
    //     public static int get(int index){return Integer.parseInt(Room.roomsL.get(index - 1)[]);}
    public static String getName(int index){return roomsL.get(index - 1)[0];}
    public static int getRID(int index){return Integer.parseInt(roomsL.get(index - 1)[1]);}
    
    public static int getDN1(int index){return Integer.parseInt(roomsL.get(index - 1)[2]);}
    public static int getDN1(){return Integer.parseInt(roomsL.get(currentRoom - 1)[2]);}
    
    public static int getDS1(int index){return Integer.parseInt(roomsL.get(index - 1)[3]);}
    public static int getDS1(){return Integer.parseInt(roomsL.get(currentRoom - 1)[3]);}
    
    public static int getDE1(int index){return Integer.parseInt(roomsL.get(index - 1)[4]);}
    public static int getDE1(){return Integer.parseInt(roomsL.get(currentRoom - 1)[4]);}
    
    public static int getDW1(int index){return Integer.parseInt(roomsL.get(index - 1)[5]);}
    public static int getDW1(){return Integer.parseInt(roomsL.get(currentRoom - 1)[5]);}
    
    public static String getTDesc(int index){return roomsL.get(index - 1)[6];}
    public static String getTDesc(){return roomsL.get(currentRoom - 1)[6];}

    public static int getIsTrap(int index){return Integer.parseInt(roomsL.get(index - 1)[7]);}
    public static int getIsTrap(){return Integer.parseInt(roomsL.get(currentRoom - 1)[7]);}
    public static void setIsTrap(int index, int val){roomsL.get(index - 1)[7] = Integer.toString(val);}
    public static void setIsTrap(int val){roomsL.get(currentRoom - 1)[7] = Integer.toString(val);}

    public static int getTrapDif(int index){return Integer.parseInt(roomsL.get(index - 1)[8]);}
    public static int getTrapDif(){return Integer.parseInt(roomsL.get(currentRoom - 1)[8]);}
    public static void setTrapDif(int index, int val){roomsL.get(index - 1)[8] = Integer.toString(val);}

    public static int getisEnco(int index){return Integer.parseInt(roomsL.get(index - 1)[9]);}
    public static int getisEnco(){return Integer.parseInt(roomsL.get(currentRoom - 1)[9]);}
    public static void setisEnco(int index, int val){roomsL.get(index - 1)[9] = Integer.toString(val);}
    public static void setisEnco(int val){roomsL.get(currentRoom - 1)[9] = Integer.toString(val);}

    public static int getEncoDif(int index){return Integer.parseInt(roomsL.get(index - 1)[10]);}
    public static int getEncoDif(){return Integer.parseInt(roomsL.get(currentRoom - 1)[10]);}
    public static void setEncoDif(int index, int val){roomsL.get(index - 1)[8] = Integer.toString(val);}

    public static int getisLooted(int index){return Integer.parseInt(roomsL.get(index - 1)[10]);}
    public static int getisLooted(){return Integer.parseInt(roomsL.get(currentRoom - 1)[10]);}
    public static void setisLooted(int index, int val){roomsL.get(index - 1)[10] = Integer.toString(val);}
    public static void setisLooted(int val){roomsL.get(currentRoom - 1)[10] = Integer.toString(val);}


}
