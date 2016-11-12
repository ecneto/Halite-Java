import java.util.Random;

/**
 * Created by enet on 11/11/16.
 */
public class LineStrategy implements MoveStrategy {
    GameMap gameMap;
    Move move;
    enum Axis {VERTICAL, HORIZONTAL}

    public LineStrategy(int x, int y, GameMap gameMap) {
        this.gameMap = gameMap;

        Random generator = new Random();
        int i = generator.nextInt(2);
        Axis axis = i == 0 ? Axis.VERTICAL : Axis.HORIZONTAL;
        Direction dir;
        if(axis == Axis.VERTICAL) {
            dir = (x%2 == 0) ? Direction.NORTH : Direction.SOUTH;
        } else {
            dir = (y%2 == 0) ? Direction.WEST : Direction.EAST;
        }
        this.move = new Move(new Location(x, y), dir);
    }

    public Move getMove(){
        return this.move;
    }

}
