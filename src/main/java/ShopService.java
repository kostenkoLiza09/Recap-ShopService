import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class ShopService {
    private ProductRepo productRepo = new ProductRepo();
    private OrderRepo orderRepo = new OrderMapRepo();

    public Order addOrder(List<String> productIds, OrderStatus orderStatus) {
        List<Product> products = new ArrayList<>();
        for (String productId : productIds) {
            Product productToOrder = productRepo.getProductById(productId);
            if (productToOrder == null) {
                System.out.println("Product mit der Id: " + productId + " konnte nicht bestellt werden!");
                return null;
            }
            products.add(productToOrder);
        }

        Order newOrder = new Order(UUID.randomUUID().toString(), products, orderStatus);

        return orderRepo.addOrder(newOrder);
    }

    public List<Order> getOrderWithStatus(OrderStatus orderStatus){

        List<Order> orders = orderRepo.getOrders();

        List<Order> ordersByStatus = orders.stream()
                .filter(o -> o.orderStatus() == orderStatus)
                .collect(Collectors.toList());

        return ordersByStatus;

    }
}
