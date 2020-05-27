package flowers;

import exceptions.EvenNumberException;

public class Bouquet {
private int numOfFlowers;
private Flower[] flowerInBouquet;
private  FlowerPackage flowerPackage;

    public Bouquet(int numOfFlowers, Flower[] flowerInBouquet, FlowerPackage flowerPackage) throws EvenNumberException {
        this.numOfFlowers = numOfFlowers;
        this.flowerInBouquet = flowerInBouquet;
        this.flowerPackage = flowerPackage;
        if(numOfFlowers%2==0) throw new EvenNumberException( "Even number brings bad luck!" );
    }

    public void setFlowerInBouquet(Flower[] flowerInBouquet) {
        this.flowerInBouquet = flowerInBouquet;
    }

    public Flower[] getFlowerInBouquet() {
        return flowerInBouquet;
    }
    
    public double  getTotalPrice(){
        double totalPrice = 0;
        for (int i = 0; i < numOfFlowers ; i++) {
          totalPrice+= flowerInBouquet[i].getPrice();
        }
        totalPrice+=flowerPackage.getPrice();
        return totalPrice;
    }
}
