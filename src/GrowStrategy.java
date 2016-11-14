/**
 * Created by enet on 11/11/16.
 */
public class GrowStrategy implements MovesStrategy {
    int size;
    Move move;

    public GrowStrategy(int x, int y, int size) {
        this.size = size;
        this.move = new Move(new Location(x, y), Direction.STILL);
    }

    public Move getMove(){
        return this.move;
    }
}
