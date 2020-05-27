public class Garage {

    private Place[] places = new Place[ 100 ];



    public Garage() {
        for (int i = 0; i < 10; i++) {
            addPlace();
        }
    }

    public Place[] getPlaces() {
        return places;
    }

    public void setPlaces(Place[] places) {
        this.places = places;
    }

    public void removePlace(long id) {
        for (Place place : places) {
            if (place.getId() == id) place = null;
        }

    }

    public void addPlace() {
        Place place = new Place();
        for (int i = 0; i < 100; i++) {
            if (places[ i ] == null) {
                place.setId( i );
                places[ i ] = place;
                break;
            }

        }
    }
}
