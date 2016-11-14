/**
 * Created by enet on 11/12/16.
 */
public class MathUtils {
    public static Float max(Float... vals) {
        Float ret = null;
        for (Float val : vals) {
            if (ret == null || (val != null && val > ret)) {
                ret = val;
            }
        }
        return ret;
    }
    public static Integer max(Integer... vals) {
        Integer ret = null;
        for (Integer val : vals) {
            if (ret == null || (val != null && val > ret)) {
                ret = val;
            }
        }
        return ret;
    }

    public static boolean floatEquals(float a, float b) {
        float epsilon = 0.00001f;
        return (Math.abs(a - b) < epsilon);
    }

    public static int getDXByDirection(Direction dir) {
        if(dir == Direction.EAST) return  1;
        else if(dir == Direction.WEST) return -1;
        else return 0;
    }

    public static int getDYByDirection(Direction dir) {
        if(dir == Direction.SOUTH) return  1;
        else if(dir == Direction.NORTH) return -1;
        else return 0;
    }
}
