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
        int oppNorth = countProductionOpp(0, -1);
        int oppSouth = countProductionOpp(0, 1);
        int oppEast = countProductionOpp(1, 0);
        int oppWest = countProductionOpp(-1, 0);

        int bestOpp = MathUtils.max(oppNorth, oppSouth, oppEast, oppWest);

        if(oppNorth == bestOpp) return Direction.NORTH;
        if(oppSouth == bestOpp) return Direction.SOUTH;
        if(oppEast == bestOpp) return Direction.EAST;
        if(oppWest == bestOpp) return Direction.WEST;
        return Direction.EAST;
    }

    int countProductionOpp(int dirX, int dirY) {
        int scanLength;
        if(dirX == 0) scanLength = gameMap.height*1/2;
        else scanLength = gameMap.width*1/2;

        int productionOpp = 0;
        for(int di = 0; di < scanLength; di++){
            int newX = (this.x + di*dirX);
            if(newX >= gameMap.width) newX -= gameMap.width;
            if(newX < 0) newX = newX += gameMap.width;
            int newY = (this.y + di*dirY);
            if(newY >= gameMap.height) newY -= gameMap.height;
            if(newY < 0) newX = newY += gameMap.height;
            Location loc = new Location(newX, newY);
            Site site = gameMap.getSite(loc);

            if(site.owner != myID) {
                productionOpp += site.production;
            }
        }
        return productionOpp;
    }

    public Move getMove(){
        return this.move;
    }

}
