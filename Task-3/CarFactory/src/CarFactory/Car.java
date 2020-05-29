package CarFactory;

public class Car implements IProduct{
    private Chassis chassis;
    private CarBody carBody;
    private Motor motor;

    public Car() {
        System.out.println("Car is being assembled..");
    }

    @Override
    public void installFirstPart(IProductPart productPart) {
        this.chassis = (Chassis) productPart;

    }

    @Override
    public void installSecondPart(IProductPart productPart) {
         this.carBody = (CarBody) productPart;
    }

    @Override
    public void installThirdPart(IProductPart productPart) {
        this.motor = (Motor) productPart;
    }

}
