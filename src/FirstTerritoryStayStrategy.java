/**
 * Created by enet on 11/13/16.
 */
public class FirstTerritoryStayStrategy implements StayStrategy {
    GameMap gameMap;
    int myID;

    Direction[][] directionDecisionMap;
    Boolean[][] stayDecisionMap;

    public FirstTerritoryStayStrategy(GameMap gameMap, int myID, Direction[][] directionDecisionMap){
        this.gameMap = gameMap;
        this.myID = myID;
        this.directionDecisionMap = directionDecisionMap;

        decideToMove();
    }

    public void decideToMove(){
        stayDecisionMap = new Boolean [gameMap.width][gameMap.height];
        for(int y = 0; y < gameMap.height; y++) {
            for (int x = 0; x < gameMap.width; x++) {
                Site site = gameMap.getSite(new Location(x, y));
                if (site.owner == myID) {
                    Direction dir = directionDecisionMap[x][y];
                    int startStrength = site.strength;
                    Boolean isEnough = decideIfStrongEnough(x, y, dir, startStrength);
                    stayDecisionMap[x][y] = !isEnough;
                }
            }
        }
    }

    boolean decideIfStrongEnough(int x, int y, Direction dir, int startStrength){
        int dx = MathUtils.getDXByDirection(dir);
        int dy = MathUtils.getDYByDirection(dir);

        int scanLength;
        if(dx == 0) scanLength = gameMap.height*1/2;
        else scanLength = gameMap.width*1/2;

        int totalStrength = startStrength;
        int targetStrength = 255;
        for(int di = 1; di < scanLength; di++){
            int newX = GameMapUtils.addX(x, di*dx, gameMap);
            int newY = GameMapUtils.addY(y, di*dy, gameMap);
            Location loc = new Location(newX, newY);
            Site scanSite = gameMap.getSite(loc);

            if(scanSite.owner == myID && dir == directionDecisionMap[newX][newY]) {
                int allyStrength = scanSite.strength;
                totalStrength += allyStrength;
            } else if(scanSite.owner != myID) {
                targetStrength = scanSite.strength;
                break;
            }
        }
        if(totalStrength >= targetStrength) {
            return true;
        } else {
            return false;
        }
    }

    public Boolean[][] getStayDecisions() {
        return stayDecisionMap;
    }
}
