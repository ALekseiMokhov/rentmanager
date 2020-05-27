import CarFactory.*;

public class Domain {
    public static void main(String[] args) throws InterruptedException {
        CarBodyBuilder carBodyBuilder = new CarBodyBuilder();
        ChassisBuilder chassisBuilder = new ChassisBuilder();
        MotorBuilder motorBuilder = new MotorBuilder();

        CarAssembler assembler = new CarAssembler(chassisBuilder,carBodyBuilder,motorBuilder ) ;
        assembler.assembleProduct( new Car ()) ;


    }
}
