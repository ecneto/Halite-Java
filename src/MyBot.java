import java.util.ArrayList;

public class MyBot {
    static int myID;
    static GameMap gameMap;

    static int territorySize;

    public static void main(String[] args) throws java.io.IOException {
        InitPackage iPackage = Networking.getInit();
        myID = iPackage.myID;
        gameMap = iPackage.map;

        Networking.sendInit("NetoBot");
        Logger.init();

        try {
            while (true) {
                gameMap = Networking.getFrame();

                ArrayList<Move> moves = new ArrayList<Move>();

                territorySize = countMySites();

                MovesStrategy strategy;
                if(territorySize < 5) {
                    strategy = new ProductionCumulationWithoutResistanceStrategy(gameMap, myID, gameMap.width/5);
                } else if (territorySize < 15) {
                    strategy = new ProductionCumulationWithoutResistanceStrategy(gameMap, myID, gameMap.width/3);
                } else {
                    strategy = new ProductionCumulationStrategy(gameMap, myID, gameMap.width/3);
                }
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
