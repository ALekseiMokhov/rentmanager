import exceptions.EvenNumberException;
import exceptions.WrongColorException;
import flowers.*;

import java.util.Random;

public class Domain {


    public static void main(String[] args) throws EvenNumberException, WrongColorException {
        BouquetBuilder builder =new BouquetBuilder();
        Bouquet bouquet = builder.buildMixedBouquet();
        System.out.println( "The total price of mixed bouquet is " + bouquet.getTotalPrice() );
    }



}
