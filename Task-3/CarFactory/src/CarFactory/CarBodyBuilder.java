package CarFactory;

public class CarBodyBuilder implements ILineStep {
    @Override
    public IProductPart buidlProductPart() {
        System.out.println(" Building car body..");

        return new CarBody();
    }
}
