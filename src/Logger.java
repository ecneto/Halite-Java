import java.io.*;

/**
 * Created by enet on 11/14/16.
 */
public class Logger {
    final static Boolean debug = true;
    final static String LOG_NAME = "_MyLog.txt";
    static Logger instance = null;

    public static void init() {
        if(!debug) return;
        if(instance != null) init();
        instance = new Logger();
    }

    public static void write(String msg) {
        if(!debug) return;
        if(instance == null) init();
        instance.print(msg);
    }

    public static void finish(){
        if(!debug) return;
        if(instance == null) init();
        instance.close();
    }

    Writer writer;
    Logger() {
        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(LOG_NAME), "utf-8"));
            print("Initialized Logger");
        } catch (Exception e) {
            throw new RuntimeException("Log Init Error: " + e.getMessage());
        }
    }

    void print(String msg) {
        try {
            writer.write(msg + "\n");
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
