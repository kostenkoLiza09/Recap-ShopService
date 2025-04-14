public class ProductNotFoundException extends RuntimeException{

    public ProductNotFoundException(String productId) {
        super("Product with" + productId + "is not found");
    }
}
