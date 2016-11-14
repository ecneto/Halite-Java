import java.util.Random;

/**
 * Created by enet on 11/11/16.
 */
public class ProductionLineStrategy implements MoveStrategy {
    GameMap gameMap;
    Move move;
    int x;
    int y;
    int myID;

    public ProductionLineStrategy(int x, int y, GameMap gameMap, int myID) {
        this.gameMap = gameMap;
        this.x = x;
        this.y = y;
        this.myID = myID;

        Random generator = new Random();
        int i = generator.nextInt(2);
        Direction dir;
        dir = decideDirection();
        this.move = new Move(new Location(x, y), dir);
    }

    Direction decideDirection() {
        float oppNorth = countProductionOpp(0, -1);
        float oppSouth = countProductionOpp(0, 1);
        float oppEast = countProductionOpp(1, 0);
        float oppWest = countProductionOpp(-1, 0);

        float bestOpp = MathUtils.max(oppNorth, oppSouth, oppEast, oppWest);

        if(MathUtils.floatEquals(oppNorth, bestOpp)) return Direction.NORTH;
        if(MathUtils.floatEquals(oppSouth, bestOpp)) return Direction.SOUTH;
        if(MathUtils.floatEquals(oppEast, bestOpp)) return Direction.EAST;
        if(MathUtils.floatEquals(oppWest, bestOpp)) return Direction.WEST;
        return Direction.EAST;
    }

    float countProductionOpp(int dirX, int dirY) {
        int scanLength;
        if(dirX == 0) scanLength = gameMap.height*1/3;
        else scanLength = gameMap.width*1/3;

        float productionOpp = 0.0f;
        for(int di = 0; di < scanLength; di++){
            int newX = (this.x + di*dirX);
            if(newX >= gameMap.width) newX -= gameMap.width;
            if(newX < 0) newX += gameMap.width;
            int newY = (this.y + di*dirY);
            if(newY >= gameMap.height) newY -= gameMap.height;
            if(newY < 0) newY += gameMap.height;
            Location loc = new Location(newX, newY);
            Site site = gameMap.getSite(loc);

            if(site.owner != myID) {
                float distanceWeight = ((float) scanLength - (float) di) / ((float) scanLength);
                productionOpp += site.production * distanceWeight;
            }
        }
        return productionOpp;
    }

    public Move getMove(){
        return this.move;
    }

}
