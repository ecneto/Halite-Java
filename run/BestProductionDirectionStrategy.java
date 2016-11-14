/**
 * Created by enet on 11/13/16.
 */
public class BestProductionDirectionStrategy implements DirectionStrategy {
    GameMap gameMap;
    int myID;
    Direction[][] directions;
    int scanDistance;

    public BestProductionDirectionStrategy(GameMap gameMap, int myID, int scanDistance) {
        this.gameMap = gameMap;
        this.myID = myID;
        this.scanDistance = scanDistance;

        directions = decideDirections();
    }

    public Direction[][] decideDirections() {
        Direction[][] directions = new Direction[gameMap.width][gameMap.height];
        Logger.write("Best Production Scanning distance " + this.scanDistance);
        for(int y = 0; y < gameMap.height; y++) {
            for (int x = 0; x < gameMap.width; x++) {
                Site site = gameMap.getSite(new Location(x, y));
                if (site.owner == myID) {
                    Logger.write("Decide direction [" + x + ", " + y + "]");
                    Direction dir = decideDirection(x, y);
                    directions[x][y] = dir;
                }
            }
        }
        return directions;
    }

    Direction decideDirection(int x, int y) {
        float oppNorth = countProductionOpp(x, y, 0, -1);
        float oppSouth = countProductionOpp(x, y, 0, 1);
        float oppEast = countProductionOpp(x, y, 1, 0);
        float oppWest = countProductionOpp(x, y, -1, 0);

        float bestOpp = MathUtils.max(oppNorth, oppSouth, oppEast, oppWest);
        Logger.write("Opp Value " + bestOpp + " best of [" +
                oppNorth + ", " +
                oppSouth + ", " +
                oppEast + ", " +
                oppWest + ", " + "]");

        if(MathUtils.floatEquals(oppNorth, bestOpp)) return Direction.NORTH;
        if(MathUtils.floatEquals(oppSouth, bestOpp)) return Direction.SOUTH;
        if(MathUtils.floatEquals(oppEast, bestOpp)) return Direction.EAST;
        if(MathUtils.floatEquals(oppWest, bestOpp)) return Direction.WEST;
        return Direction.EAST;
    }

    float countProductionOpp(int x, int y, int dirX, int dirY) {
        float productionOpp = 0.0f;
        for(int di = 0; di < scanDistance; di++){
            int newX = GameMapUtils.addX(x, di*dirX, gameMap);
            int newY = GameMapUtils.addY(y, di*dirY, gameMap);
            Location loc = new Location(newX, newY);
            Site site = gameMap.getSite(loc);

            if(site.owner != myID) {
                float distanceWeight = ((float) scanDistance - (float) di) / ((float) scanDistance);
                productionOpp += site.production * distanceWeight;
            }
        }
        return productionOpp;
    }

    public Direction[][] getDirections() {
        return this.directions;
    }
}
