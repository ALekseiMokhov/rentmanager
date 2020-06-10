package flowers;

import exceptions.WrongColorException;

public class Chrysanthemium implements Flower {
    private double price = 4.7;
    private FlowerColor flowerColor;
    private int id =2;

    public Chrysanthemium( FlowerColor flowerColor) throws WrongColorException {
        this.flowerColor = flowerColor;
        if(flowerColor==FlowerColor.BLUE)throw new WrongColorException( "There is no blue Chrysanthemium in Nature!" );

    }

    public void setPrice(double price) {
        this.price = price;
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
