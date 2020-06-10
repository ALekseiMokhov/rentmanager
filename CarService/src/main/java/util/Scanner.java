package main.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Scanner {
    private static BufferedReader reader = new BufferedReader( new InputStreamReader( System.in  ) );
    private Scanner(){

    }
    private static Scanner INSTANCE;

    public static Scanner getInstance(){
        if(INSTANCE==null)  {
            INSTANCE = new Scanner();
        }
        return INSTANCE;
    }

    public BufferedReader getReader() {
        return reader;
    }
}
