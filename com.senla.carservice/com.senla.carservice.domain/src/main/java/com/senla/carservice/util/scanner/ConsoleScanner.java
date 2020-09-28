package com.senla.carservice.util.scanner;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ConsoleScanner {
    private static final BufferedReader READER = new BufferedReader( new InputStreamReader( System.in ) );
    private static ConsoleScanner INSTANCE;

    private ConsoleScanner() {

    }

    public static ConsoleScanner getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ConsoleScanner();
        }
        return INSTANCE;
    }

    public BufferedReader getReader() {
        return READER;
    }
}
