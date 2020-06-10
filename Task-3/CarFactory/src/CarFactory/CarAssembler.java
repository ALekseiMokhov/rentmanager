package CarFactory;

public class CarAssembler implements IAssembleLine{

    private final ChassisBuilder firstStep;
    private final CarBodyBuilder secondStep;
    private final MotorBuilder thirdStep;

    public CarAssembler(ChassisBuilder firstStep, CarBodyBuilder secondStep, MotorBuilder thirdStep) {

        this.firstStep = firstStep;
        this.secondStep = secondStep;
        this.thirdStep = thirdStep;
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
