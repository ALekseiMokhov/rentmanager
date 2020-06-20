package util.csv;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class CsvMasterParser {

    public static List <String> load() throws IOException {
        return intermediateList( parse() );

    }

    private static String parse() throws IOException {
        Class clazz = CsvMasterParser.class;
        InputStream inputStream = clazz.getResourceAsStream( "/csv/master.csv" );
        String result = readFromInputStream( inputStream );
        return result;
    }

    private static String readFromInputStream(InputStream inputStream)
            throws IOException {
        StringBuilder resultStringBuilder = new StringBuilder();
        try (BufferedReader br
                     = new BufferedReader( new InputStreamReader( inputStream ) )) {
            String line;
            while ((line = br.readLine()) != null) {
                resultStringBuilder.append( line ).append( " " );
            }
        }
        return resultStringBuilder.toString();
    }

    private static List <String> intermediateList(String result) {
        List <String> intermidiate = Arrays.asList( result.split( " " ) );

        return intermidiate;
    }
}
