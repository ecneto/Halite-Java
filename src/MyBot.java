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

        Logger.init();
        Networking.sendInit("NetoBot");
        Logger.write("Initialized");

        try {
            while (true) {
                moves = new ArrayList<Move>();
                gameMap = Networking.getFrame();

                numMySites = countMySites();

                MovesStrategy strategy = new ProductionCumulationStrategy(gameMap, myID);
                moves.addAll(strategy.getMoves());

                Networking.sendFrame(moves);
            }
        } catch(Exception e){
        } finally {
            Logger.write("Ended");
            Logger.finish();
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
