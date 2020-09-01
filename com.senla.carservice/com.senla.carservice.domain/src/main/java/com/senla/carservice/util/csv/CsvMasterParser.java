package com.senla.carservice.util.csv;

import com.senla.carservice.entity.master.*;
import com.senla.carservice.util.calendar.Calendar;

import java.io.*;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class CsvMasterParser {
    private static final File FILE = new File( "./files/master.csv" );

    public static List <IMaster> load() throws IOException {
        return parseMastersFromString( intermediateList( parse( FILE ) ) );

    }

    public static List <IMaster> loadMastersById(List <UUID> input) throws IOException {
        return load().stream()
                .filter( m -> input.contains( m.getId() ) )
                .collect( Collectors.toList() );

    }

    private static List <String> intermediateList(String result) {   /* splits String into chunks equal IMaster constructors params*/
        List <String> list = Arrays.asList( result.split( " " ) );
        for (String s : list) {
            System.out.println( "master in intermediate list : " + s );
        }
        return list;
    }


    private static String parse(File file) throws IOException {
        StringBuilder resultStringBuilder = new StringBuilder();
        try (BufferedReader br
                     = new BufferedReader( new InputStreamReader(
                new FileInputStream( file )
        ) )) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.isEmpty()) {
                    resultStringBuilder.append( line ).append( " " );
                }
            }
        }
        return resultStringBuilder.toString();
    }

    private static List <IMaster> parseMastersFromString(List <String> input) {
        List <IMaster> res = new ArrayList <>();

        List <List <String>> list = input.stream()
                .map( s -> Arrays.asList( s.split( "," ) ) )
                .collect( Collectors.toList() );

        for (List <String> var : list) {
            IMaster master;
            Speciality speciality = Speciality.valueOf( var.get( 2 ) );
            switch (speciality) {
                case RESHAPER -> {
                    master = new Reshaper( var.get( 0 ), Double.parseDouble( var.get( 1 ) ), new Calendar()
                            , Speciality.valueOf( var.get( 2 ) ), UUID.fromString( var.get( 3 ) ) );
                }
                case ELECTRICIAN -> {
                    master = new Electrician( var.get( 0 ), Double.parseDouble( var.get( 1 ) ), new Calendar()
                            , Speciality.valueOf( var.get( 2 ) ), UUID.fromString( var.get( 3 ) ) );
                }
                case PAINTER -> {
                    master = new Painter( var.get( 0 ), Double.parseDouble( var.get( 1 ) ), new Calendar()
                            , Speciality.valueOf( var.get( 2 ) ), UUID.fromString( var.get( 3 ) ) );
                }
                case MECHANIC -> {
                    master = new Mechanic( var.get( 0 ), Double.parseDouble( var.get( 1 ) ), new Calendar()
                            , Speciality.valueOf( var.get( 2 ) ), UUID.fromString( var.get( 3 ) ) );
                }
                default -> {
                    throw new IllegalStateException( "there is no suitable speciality!" );
                }
            }
            if (var.size() > 4) {
                master.getCalendar().setBookedDates( parseCalendar( var.subList( 4, var.size() ) ) );
            }

            res.add( master );
        }
        System.out.println( res.size() + " masters were loaded from file!" );
        for (IMaster master : res) {
            System.out.println( master );
        }

        return res;

    }

    private static HashMap <LocalDate, Boolean> parseCalendar(List <String> input) {
        HashMap <LocalDate, Boolean> datesBooked = new HashMap <>();
        for (String s : input) {
            datesBooked.put( LocalDate.parse( s ), true );
        }
        return datesBooked;

    }
}

