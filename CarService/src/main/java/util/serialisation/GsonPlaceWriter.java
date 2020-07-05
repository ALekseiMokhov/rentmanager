package util.serialisation;

import com.google.gson.Gson;
import com.senla.carservice.domain.entities.garage.Place;

import java.io.*;
import java.util.List;

public class GsonPlaceWriter {
    private final static Gson GSON = new Gson();
    public static <T> void serializeEntity(T t) throws IOException {
        String jsonFromEntity = GSON.toJson( t );
        try (BufferedWriter writer =
                     new BufferedWriter( new FileWriter( "./files/Json/PlacesJson.txt" ,true) )) {
            writer.append( "\n" );
            writer.append( jsonFromEntity );
        }

    }

    /*public static List <Place> deserializePlaces (){

        
    }*/
}
