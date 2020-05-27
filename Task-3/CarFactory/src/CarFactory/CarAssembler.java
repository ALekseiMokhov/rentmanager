package CarFactory;

public class CarAssembler implements IAssembleLine{
   private ILineStep firstStep;
   private ILineStep secondStep;
   private ILineStep thirdStep;

    public CarAssembler(ILineStep firstStep, ILineStep secondStep, ILineStep thirdStep) {
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
