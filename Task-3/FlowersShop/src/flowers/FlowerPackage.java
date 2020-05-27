package flowers;

public enum FlowerPackage implements Goods {PAPER(0.0),HANDCRAFT(2.1),BUCKET(4.3);

    private double price;

    FlowerPackage(double price) {
        this.price = price;
    }

    @Override
    public double getPrice() {
        return this.price;
    }

}
