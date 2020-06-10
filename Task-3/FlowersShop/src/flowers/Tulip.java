package flowers;

import exceptions.WrongColorException;

public class Tulip implements Flower {
    private double price = 2.6;
    private FlowerColor flowerColor;
    private int id =1;


    public Tulip(FlowerColor flowerColor)throws WrongColorException {
        this.price = price;
        this.flowerColor = flowerColor;
        if(flowerColor==FlowerColor.WHITE)throw new WrongColorException( "There is no white Chrysanthemium in Nature!" );
    }


    @Override
    public double getPrice() {
        return this.price;
    }

    @Override
    public int getId() {
        return id;

    }
    @Override
    public FlowerColor getColor() {
        return flowerColor;
    }

}
