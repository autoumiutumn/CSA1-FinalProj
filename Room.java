import java.util.ArrayList;

public class Room {
    // Static Attributes & Getters
    private static ArrayList<String[]> roomsL = new ArrayList<String[]>();
    
    private static int currentRoom = 1; 
    public static int getCRID() {return currentRoom;}
    private static ArrayList<Integer> pastRooms = new ArrayList<Integer>();
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
        pastRooms.add(currentRoom);
        currentRoom = rIndex;
    }

    // Constructor (Kind of, not actual object)
    public static void roomMaker() {
        //                  RoomName RID Doors: N1    S1    E1    W1   TestDesc     
        //   isT|TD 1-3     isE|ED1-3  isLooted
        String[] r1Maker = {"room1", "1", /**/ "-1", "-1", "3", "2", "EntryWay",        
    /**/ "0", "0", /**/ "0", "0", /**/ "0"};
        roomsL.add(r1Maker);
        String[] r2Maker = {"room2", "2", /**/ "-1", "-1", "1", "-1", "Trap Test",
        /**/ "1", "3", /**/ "0", "0", /**/ "0"};
        roomsL.add(r2Maker);
        String[] r3Maker = {"room3", "3", /**/ "-1", "-1", "-1", "1", "Encounter Test",
        /**/ "0", "0", /**/ "1", "1", /**/ "0"};
        roomsL.add(r3Maker);
        
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

    public static int getisEnco(int index){return Integer.parseInt(roomsL.get(index - 1)[9]);}
    public static int getisEnco(){return Integer.parseInt(roomsL.get(currentRoom - 1)[9]);}
    public static void setisEnco(int index, int val){roomsL.get(index - 1)[9] = Integer.toString(val);}
    public static void setisEnco(int val){roomsL.get(currentRoom - 1)[9] = Integer.toString(val);}

    public static int getEncoDif(int index){return Integer.parseInt(roomsL.get(index - 1)[10]);}
    public static int getEncoDif(){return Integer.parseInt(roomsL.get(currentRoom - 1)[10]);}

    public static int getisLooted(int index){return Integer.parseInt(roomsL.get(index - 1)[10]);}
    public static int getisLooted(){return Integer.parseInt(roomsL.get(currentRoom - 1)[10]);}
    public static void setisLooted(int index, int val){roomsL.get(index - 1)[10] = Integer.toString(val);}
    public static void setisLooted(int val){roomsL.get(currentRoom - 1)[10] = Integer.toString(val);}


}
