import java.util.ArrayList;

/**
 * Created by enet on 11/12/16.
 */
public class DirectionAndStayDecision implements MovesStrategy {
    GameMap gameMap;
    int myID;

    Direction[][] directionDecisionMap;
    Boolean[][] stayDecisionMap;

    ArrayList<Move> moves;

    public DirectionAndStayDecision(GameMap gameMap, int myID, DirectionStrategy directionStrategy, StayStrategy stayStrategy) {
        this.gameMap = gameMap;
        this.myID = myID;

        this.moves = new ArrayList<Move>();

        directionDecisionMap = directionStrategy.getDirections();
        stayDecisionMap = stayStrategy.getStayDecisions();

        makeMoves();
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
