package CarFactory;

public class MotorBuilder implements  ILineStep{
    @Override
    public IProductPart buidlProductPart() {
        System.out.println(" Building motor..");
        return new Motor();
    }
}
