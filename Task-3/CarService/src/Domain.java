import java.time.LocalDate;


public class Domain {
    public static void main(String[] args) {
        Admin admin = new Admin();
        admin.addMaster( "Vasiliy", Speciality.MECHANIC);
        System.out.println(admin.findByName( "Vasiliy" ));
        admin.addPlace();
        admin.addPlace();
        admin.addPlace();
        for (int i = 0; i <admin.getPlaces().length ; i++) {
            if(admin.getPlaces()[i]!=null)
            System.out.println(admin.getPlaces()[i].getId());
        }
        admin.addOrder(admin.findByName( "Vasiliy" ),admin.findPlace(),LocalDate.of( 2020,5,27 )  );
     
        System.out.println(admin.findOrderById( 0 ).toString());
        admin.shiftOrderDate( admin.findOrderById( 0 ),LocalDate.of( 2020,5 ,28) );
        System.out.println(admin.findOrderById( 0 ).toString());
        /**/
        Order [] orders = admin.getOrders();
        for (Order order : orders) {
           if(order!=null) System.out.println(order.getId());
        }
        /**/

        System.out.println(admin.findOrderById( 0 ).isDone());
        admin.removeOrder( 0 );

    }
}
