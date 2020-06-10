package CarFactory;

public interface IAssembleLine {
    IProduct assembleProduct(IProduct product) throws InterruptedException;
}
