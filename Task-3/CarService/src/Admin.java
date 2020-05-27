import java.time.LocalDate;

public class Admin {
    private static final Garage GARAGE = new Garage();
    private Master[] masters = new Master[100];
    private Order[] orders = new Order[100];


    public void shiftOrderDate(Order order, LocalDate newDate) {
        order.setDateBooked( newDate);
    }

    public void setNewMaster(Order order, Master master) {
        order.setMaster( master );

    }
    public Master findByName(String name){
        for (Master master : masters) {
            if(master.getFullName()==name)   return master;
        }
        if(true) throw new IllegalArgumentException( "Master" + name + "doesn't exist");
        return null;
    }

    public void addOrder(Master master, Place place, LocalDate date) {
        for (int i = 0; i < orders.length; i++) {
            if(orders[i]!=null && orders[i].getId()==orders.length-1){clearOrders();}
            if(orders[i]==null){
                Order order = new Order( date,master,place );
                order.setId( i );
                orders[i]=order;
                break;
            }
        }

    }
    public void removeOrder(long id){
        for (int i = 0; i < orders.length ; i++) {
            if(orders[i]!=null && orders[i].getId()==id) {
                orders[i]=null;
            }
        }
    }public Order findOrderById(long id){
        for (int i = 0; i < orders.length ; i++) {
            if(orders[i]!=null && orders[i].getId()==id) {
                return orders[i];

            }
        }
        if(true)throw new IllegalArgumentException( "No such Order in order list!" ) ;
        return null;
    }


    public void closeOrder(long id) {
        for (Order order : orders) {
            if (order.getId() == id && order!=null) order.setDone(  );
        }

    }

    public void addMaster(String name, Speciality speciality) {
        Master master = new Master( speciality, name );
        for (int i = 0; i < masters.length; i++) {
            if (masters[ i ] == null) {
                masters[ i ] = master;
                master.setId( i );
            }
        }
    }

    public void removeMaster(long id) {
        for (int i = 0; i < masters.length; i++) {
            if (masters[ i ].getId() == id) {
                masters[ i ] = null;
            }
        }
    }

    public void addPlace() {
        GARAGE.addPlace();
    }

    public void removePlace(long id) {
        GARAGE.removePlace( id );
    }
    public Place findPlace() {
        for (Place place : GARAGE.getPlaces()) {
           if(place.isFree()&&place!=null)   return place;
           break;
        }
        if(true) throw new IllegalStateException( "All places in Garage are booked!" );
        return null;
    }

    public Place[] getPlaces(){
        return GARAGE.getPlaces();
    }
    public Order [] getOrders(){
        return  orders;
    }

    private void clearOrders() {
        for (int i = 0; i < orders.length; i++) {
            orders[i] = null;
        }
    }
}
