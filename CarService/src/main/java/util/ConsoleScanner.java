package util;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ConsoleScanner {
    private static BufferedReader reader = new BufferedReader( new InputStreamReader( System.in ) );

    private ConsoleScanner() {

    }

    private static ConsoleScanner INSTANCE;

    public static ConsoleScanner getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ConsoleScanner();
        }
        return INSTANCE;
    }

    public BufferedReader getReader() {
        return reader;
    }
}
