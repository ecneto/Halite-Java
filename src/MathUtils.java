/**
 * Created by enet on 11/12/16.
 */
public class MathUtils {
    public static Integer max(Integer... vals) {
        Integer ret = null;
        for (Integer val : vals) {
            if (ret == null || (val != null && val > ret)) {
                ret = val;
            }
        }
        return ret;
    }
}
