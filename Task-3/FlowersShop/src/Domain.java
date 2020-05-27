import exceptions.EvenNumberException;
import exceptions.WrongColorException;
import flowers.*;

import java.util.Random;

public class Domain {
    Random random = new Random();

    public static void main(String[] args) throws EvenNumberException, WrongColorException {
        Domain domain = new Domain();
        Bouquet bouquet = domain.buildMixedBouquet();
        System.out.println( "The total price of mixed bouquet is " + bouquet.getTotalPrice() );
    }

    public Bouquet buildMixedBouquet() throws WrongColorException, EvenNumberException {
        Bouquet bouquet;
        int size = pickBouquetSize();

        Flower[] flowers = new Flower[ size ];
        for (int i = 0; i < size; i++) {
            flowers[ i ] = pickFlower();

        }

        FlowerPackage flowerPackage = pickPackage();
        return new Bouquet( size, flowers, flowerPackage );

    }

    private FlowerPackage pickPackage() {
        FlowerPackage flowerPackage;
        FlowerPackage[] values = FlowerPackage.values();
        int typeofPackage = random.nextInt( 3 );
        flowerPackage = values[ typeofPackage ];
        return flowerPackage;

    }

    private Flower pickFlower() throws WrongColorException {
        Flower flower;
        int typeOfFlower = random.nextInt( 11 );
        switch (typeOfFlower) {
            case 0:
                flower = new Rose( FlowerColor.RED );
            case 1:
                flower = new Rose( FlowerColor.WHITE );
            case 2:
                flower = new Rose( FlowerColor.YELLOW );
            case 3:
                flower = new Chrysanthemium( FlowerColor.RED );
            case 4:
                flower = new Chrysanthemium( FlowerColor.YELLOW );
            case 5:
                flower = new Chrysanthemium( FlowerColor.WHITE );
            case 6:
                flower = new Tulip( FlowerColor.RED );
            case 7:
                flower = new Tulip( FlowerColor.YELLOW );
            default:
                flower = new Tulip( FlowerColor.BLUE );

        }
        return flower;
    }

    private int pickBouquetSize() {
        int bouquetSize = random.nextInt( 30 );
        bouquetSize += bouquetSize % 2 == 0 ? 1 : 0;
        return bouquetSize;

    }


}
