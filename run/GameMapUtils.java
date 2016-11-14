/**
 * Created by enet on 11/13/16.
 */
public class GameMapUtils {
    public static int addX(int x, int dx, GameMap gameMap) {
        int newX = x + dx;
        if(newX >= gameMap.width) newX -= gameMap.width;
        if(newX < 0) newX += gameMap.width;
        return newX;
    }

    public static int addY(int y, int dy, GameMap gameMap) {
        int newY = y + dy;
        if(newY >= gameMap.height) newY -= gameMap.height;
        if(newY < 0) newY += gameMap.height;
        return newY;
    }
}
