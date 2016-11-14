/**
 * Created by enet on 11/13/16.
 */
public class BestProductionLeastResistanceDirectionStrategy extends BestProductionDirectionStrategy {

    public BestProductionLeastResistanceDirectionStrategy(GameMap gameMap, int myID, int scanDistance) {
        super(gameMap, myID, scanDistance);
    }

    float countProductionOpp(int x, int y, int dirX, int dirY) {
        float productionOpp = 0.0f;
        float opponentsStrength = 0.1f;
        for(int di = 0; di < scanDistance; di++){
            int newX = GameMapUtils.addX(x, di*dirX, gameMap);
            int newY = GameMapUtils.addY(y, di*dirY, gameMap);
            Location loc = new Location(newX, newY);
            Site site = gameMap.getSite(loc);

            if(site.owner != myID) {
                float distanceWeight = ((float) scanDistance - (float) di) / ((float) scanDistance);
                productionOpp += site.production * distanceWeight;
                opponentsStrength  += site.strength * distanceWeight;
            }
        }
        return (productionOpp / opponentsStrength);
    }

    public Direction[][] getDirections() {
        return this.directions;
    }
}
