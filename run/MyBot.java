import java.util.ArrayList;

public class MyBot {
    static int myID;
    static ArrayList<Move> moves;
    static GameMap gameMap;

    static int numMySites;

    public static void main(String[] args) throws java.io.IOException {
        InitPackage iPackage = Networking.getInit();
        myID = iPackage.myID;
        gameMap = iPackage.map;

        Networking.sendInit("NetoBot");

        while(true) {
            moves = new ArrayList<Move>();
            gameMap = Networking.getFrame();

            numMySites = countMySites();

            for(int y = 0; y < gameMap.height; y++) {
                for(int x = 0; x < gameMap.width; x++) {
                    Site site = gameMap.getSite(new Location(x, y));
                    if(site.owner == myID) {
                        MoveStrategy strategy;

                        int growthSize;
                        if(numMySites < 5) {
                            growthSize = 15;
                        } else {
                            growthSize = 20;
                        }

                        int myStrength = site.strength;
                        if (myStrength < growthSize) {
                            strategy = new GrowStrategy(x, y, growthSize);
                        } else {
                            strategy = new ProductionLineStrategy(x, y, gameMap, myID);
                        }
                        moves.add(strategy.getMove());
                    }
                }
            }
            Networking.sendFrame(moves);
        }
    }

    static int countMySites() {
        int count = 0;
        for (int y = 0; y < gameMap.height; y++) {
            for (int x = 0; x < gameMap.width; x++) {
                Site site = gameMap.getSite(new Location(x, y));
                if (site.owner == myID) {
                    count ++;
                }
            }
        }
        return count;
    }
}
