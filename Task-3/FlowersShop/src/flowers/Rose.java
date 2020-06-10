package flowers;

import exceptions.WrongColorException;

public class Rose implements Flower {
    private double price = 7.2;
    private FlowerColor flowerColor;
    private int id = 0;


    public Rose(FlowerColor flowerColor) throws WrongColorException {
        this.flowerColor = flowerColor;
        if (flowerColor == FlowerColor.BLUE) {
            throw new WrongColorException( "There is no blue roses in Nature!" );
        }

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
