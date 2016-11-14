import java.util.ArrayList;
import java.util.Random;

/**
 * Created by enet on 11/12/16.
 */
public class ProductionCumulationStrategy implements MovesStrategy {
    GameMap gameMap;
    int x;
    int y;
    int myID;

    Direction[][] directionDecisionMap;
    Boolean[][] stayDecisionMap;

    ArrayList<Move> moves;

    public ProductionCumulationStrategy(GameMap gameMap, int myID) {
        this.gameMap = gameMap;
        this.myID = myID;

        this.moves = new ArrayList<Move>();

        decideDirections();
        decideToMove();

        makeMoves();
    }

    void decideDirections(){
        DirectionStrategy dirStrat = new BestProductionDirectionStrategy(gameMap, myID);
        directionDecisionMap = dirStrat.getDirections();
    }

    void decideToMove() {
        StayStrategy stayStrat = new FirstTerritoryStayStrategy(gameMap, myID, directionDecisionMap);
        stayDecisionMap = stayStrat.getStayDecisions();
    }

    void makeMoves(){
        for(int y = 0; y < gameMap.height; y++) {
            for (int x = 0; x < gameMap.width; x++) {
                Site site = gameMap.getSite(new Location(x, y));
                if (site.owner == myID) {
                    Direction dir = directionDecisionMap[x][y];
                    Boolean stay = stayDecisionMap[x][y];

                    Direction deciciveDirection;
                    if (stay) {
                        deciciveDirection = Direction.STILL;
                    } else {
                        deciciveDirection = dir;
                    }
                    moves.add(new Move(new Location(x, y), deciciveDirection));
                }
            }
        }
    }

    public ArrayList<Move> getMoves(){
        return this.moves;
    }

}
