import java.util.ArrayList;

/**
 * Created by enet on 11/12/16.
 */
public class ProductionCumulationWithoutResistanceStrategy implements MovesStrategy {
    GameMap gameMap;
    int myID;

    DirectionAndStayDecision decision;

    public ProductionCumulationWithoutResistanceStrategy(GameMap gameMap, int myID, int scanDistance) {
        this.gameMap = gameMap;
        this.myID = myID;

        DirectionStrategy dirStrat = new BestProductionLeastResistanceDirectionStrategy(gameMap, myID, scanDistance);
        Direction[][] directionDecisionMap = dirStrat.getDirections();
        StayStrategy stayStrat = new FirstTerritoryStayStrategy(gameMap, myID, directionDecisionMap);

        decision = new DirectionAndStayDecision(gameMap, myID, dirStrat, stayStrat);
    }

    public ArrayList<Move> getMoves(){
        return decision.getMoves();
    }

}
