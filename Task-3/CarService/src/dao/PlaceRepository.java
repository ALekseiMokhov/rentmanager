package dao;

import entities.Master;
import entities.Place;

public class PlaceRepository implements Dao {
    private Place[] places = new Place[ 100 ];

    @Override
    public Object findById(int id) {
        for (Place place : places) {
          if(place!=null && place.getId()==id)     {
              return place;
          }
        }
        return null;
    }

    @Override
    public Object[] findAll() {
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
    public void save(Object o) {
        if(o instanceof Place ==false)   {
            throw new IllegalArgumentException( "Wrong object type!" )  ;
        }
        if(places[((Place) o).getId()]==null) {
            places[((Place) o).getId()] = (Place) o;
        }
    }


}
