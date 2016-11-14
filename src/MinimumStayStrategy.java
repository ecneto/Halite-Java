/**
 * Created by enet on 11/14/16.
 */
public class MinimumStayStrategy implements StayStrategy {
    GameMap gameMap;
    int myID;
    int minSize;

    Boolean[][] stayDecisionMap;

    public MinimumStayStrategy(GameMap gameMap, int myID, int minSize){
        this.gameMap = gameMap;
        this.myID = myID;
        this.minSize = minSize;

        decideToMove();
    }

    public void decideToMove(){
        stayDecisionMap = new Boolean [gameMap.width][gameMap.height];
        for(int y = 0; y < gameMap.height; y++) {
            for (int x = 0; x < gameMap.width; x++) {
                Site site = gameMap.getSite(new Location(x, y));
                if (site.owner == myID) {
                    stayDecisionMap[x][y] = site.strength < minSize;
                }
            }
        }
    }

    public Boolean[][] getStayDecisions() {
        return stayDecisionMap;
    }
}
