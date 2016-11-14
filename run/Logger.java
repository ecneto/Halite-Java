import java.io.PrintWriter;

/**
 * Created by enet on 11/14/16.
 */
public class Logger {
    final static String LOG_NAME = "_MyLog.txt";
    static Logger instance = null;

    public static void init() {
        if(instance != null) init();
        instance = new Logger();
    }

    public static void write(String msg) {
        if(instance == null) init();
        instance.print(msg);
    }

    public static void finish(){
        if(instance == null) init();
        instance.close();
    }

    PrintWriter writer;
    Logger() {
        try {
            writer = new PrintWriter(LOG_NAME, "UTF-8");
            print("Initialized Logger");
        } catch (Exception e) {
            throw new RuntimeException("Log Init Error: " + e.getMessage());
        }
    }

    void print(String msg) {
        try {
            writer.println(msg);
        } catch(Exception e) {
            throw new RuntimeException("Log WriteError: " + e.getMessage());
        }
    }

    void close() {
        try {
            writer.close();
        } catch(Exception e) {
            throw new RuntimeException("Log Finish Error: " + e.getMessage());
        }
    }
}
