import java.time.Instant;
import java.util.*;

public class ShopService {
    private ProductRepo productRepo = new ProductRepo();
    private OrderRepo orderRepo = new OrderMapRepo();

    public Order addOrder (List<String> productIds) {
        List<Product> products = new ArrayList<>();

        for (String productId : productIds) {
            Product productToOrder = productRepo.getProductById(productId).orElseThrow(() -> new RuntimeException("Product not found"));
            if (productToOrder == null) {
                System.out.println("Product mit der Id: " + productId + " konnte nicht bestellt wenden! ");
                return null;
            }
            products.add(productToOrder);
        }
        Order newOrder = new Order(UUID.randomUUID().toString(), products, OrderStatus.PROCESSING, Instant.now());
        return orderRepo.addOrder(newOrder);
}



    public List<Order> getOrderWithStatus(OrderStatus orderStatus){
        return orderRepo.getOrders()
                .stream()
                .filter(o -> o.orderStatus().equals(orderStatus))
                .toList();

    }

    public OrderStatus updateStatus(String id, OrderStatus status) {
        Order order = orderRepo.getOrderById(id).withOrderStatus(status);
        orderRepo.removeOrder(id);
        orderRepo.addOrder(order);
        return order.orderStatus();
    }
}