package CarFactory;

public class CarAssembler implements IAssembleLine{
   private ChassisBuilder firstStep;
   private CarBodyBuilder secondStep;
   private MotorBuilder thirdStep;

    public CarAssembler() {
        this.firstStep = new ChassisBuilder();
        this.secondStep = new CarBodyBuilder();
        this.thirdStep = new MotorBuilder();
    }

    @Override
    public IProduct assembleProduct(IProduct product) throws InterruptedException {

        product.installFirstPart( firstStep.buidlProductPart() );
        System.out.println( "Installing first part.." );
        Thread.sleep( 1000 );

        product.installSecondPart( secondStep.buidlProductPart() );
        System.out.println( "Installing second part.." );
        Thread.sleep( 1000 );

        product.installThirdPart( thirdStep.buidlProductPart() );
        System.out.println( "installing third part.." );
        Thread.sleep( 1000 );

        System.out.println("The car has been produced!");

        return product;
    }
}
