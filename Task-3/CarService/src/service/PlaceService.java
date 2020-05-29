package service;

import dao.PlaceRepository;
import entities.Place;

public class PlaceService {

    PlaceRepository repository = new PlaceRepository();
    Place [] places = (Place[]) repository.findAll();

    public PlaceService() {
        for (int i = 0; i < 10; i++) {
            addPlace(new Place());
        }
    }

    public Place[] getPlaces() {
        return places;
    }


    public void removePlace(int id) {
        repository.delete( id );

    }

    public void addPlace( Place place) {
        for (int i = 0; i < 100; i++) {
            if (places[ i ] == null) {
                place.setId( i );
                places[ i ] = place;
                repository.save( place );
                break;
            }

        }
    }
    public Place findFreePlace(){
        for (Place place : places) {
            if(place.isFree()&&place!=null)   return place;
            throw new IllegalStateException( "All places in Garage are booked!" );
        }

        return null;
    }
}
