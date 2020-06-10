package repository;

import entities.Master;
import entities.Place;

public class PlaceRepository implements Repository<Place>{
    private Place[] places = new Place[ 100 ];

    @Override
    public Place findById(int id) {
        for (Place place : places) {
          if(place!=null && place.getId()==id)     {
              return place;
          }
        }
        return null;
    }

    @Override
    public Place[] findAll() {
        return places;
    }

    @Override
    public void delete(int id) {
        for (Place place : places) {
            if (place.getId() == id) {
                place = null;
            }
        }
    }

    @Override
    public void save(Place place) {
        if(places[place.getId()]==null) {
            places[place.getId()] = place;
        }
    }


}
