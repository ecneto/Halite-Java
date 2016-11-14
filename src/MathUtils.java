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

    public static boolean floatEquals(float a, float b) {
        float epsilon = 0.00001f;
        return (Math.abs(a - b) < epsilon);
    }
}
