import java.util.ArrayList;

/**
 * Created by enet on 11/14/16.
 */
public class QuickGrowStrategy implements MovesStrategy {
    GameMap gameMap;
    int myID;

    DirectionAndStayDecision decision;

    ArrayList<Move> moves;

    public QuickGrowStrategy(GameMap gameMap, int myID, int minSize, int scanDistance) {
        this.gameMap = gameMap;
        this.myID = myID;

        this.moves = new ArrayList<Move>();

        DirectionStrategy dirStrat = new BestProductionDirectionStrategy(gameMap, myID, scanDistance);
        StayStrategy stayStrat = new MinimumStayStrategy(gameMap, myID, minSize);

        decision = new DirectionAndStayDecision(gameMap, myID, dirStrat, stayStrat);
    }

    public ArrayList<Move> getMoves(){
        return decision.getMoves();
    }

}
