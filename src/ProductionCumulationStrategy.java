import java.util.ArrayList;

/**
 * Created by enet on 11/12/16.
 */
public class ProductionCumulationStrategy implements MovesStrategy {
    GameMap gameMap;
    int myID;

    DirectionAndStayDecision decision;

    public ProductionCumulationStrategy(GameMap gameMap, int myID, int scanDistance) {
        this.gameMap = gameMap;
        this.myID = myID;

        DirectionStrategy dirStrat = new BestProductionDirectionStrategy(gameMap, myID, scanDistance);
        Direction[][] directionDecisionMap = dirStrat.getDirections();
        StayStrategy stayStrat = new FirstTerritoryStayStrategy(gameMap, myID, directionDecisionMap);

        decision = new DirectionAndStayDecision(gameMap, myID, dirStrat, stayStrat);
    }

    public ProductionCumulationStrategy(GameMap gameMap, int myID) {
        this.gameMap = gameMap;
        this.myID = myID;
    }

    public ArrayList<Move> getMoves(){
        return decision.getMoves();
    }

}
