package service;

import repository.PlaceRepository;
import entities.Place;

public class PlaceService {

    PlaceRepository repository = new PlaceRepository();

    public PlaceService() {
        for (int i = 0; i < 10; i++) {
            addPlace(new Place());
        }
    }

    public Place[] getPlaces() {
        return repository.findAll();
    }


    public void removePlace(int id) {
        repository.delete( id );

    }

    public void addPlace( Place place) {
        for (int i = 0; i < 100; i++) {
            if (repository.findAll()[ i ] == null) {
                place.setId( i );
                repository.findAll()[ i ] = place;
                repository.save( place );
                break;
            }

        }
    }
    public Place findFreePlace(){
        for (Place place : repository.findAll()) {
            if(place.isFree()&&place!=null)   return place;
            throw new IllegalStateException( "All places in Garage are booked!" );
        }

        return null;
    }
}
