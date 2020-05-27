package CarFactory;

public class ChassisBuilder implements ILineStep{
    @Override
    public IProductPart buidlProductPart() {
        System.out.println(" Building chassis..");

        return new Chassis();
    }
}
