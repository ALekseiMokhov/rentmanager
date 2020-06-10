import CarFactory.*;

public class Domain {
    public static void main(String[] args) throws InterruptedException {
        CarAssembler assembler = new CarAssembler( new ChassisBuilder(),new CarBodyBuilder(),new MotorBuilder()) ;
        assembler.assembleProduct( new Car ()) ;


    }
}
