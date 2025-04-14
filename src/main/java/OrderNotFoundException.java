public class OrderNotFoundException extends Exception{

    public OrderNotFoundException(String id) {
        super("Order with " + id + " is not found");
    }
}
