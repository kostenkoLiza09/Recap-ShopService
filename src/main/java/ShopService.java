import java.util.*;
import java.util.stream.Collectors;

public class ShopService {
    private ProductRepo productRepo = new ProductRepo();
    private OrderRepo orderRepo = new OrderMapRepo();

    public Order addOrder(List<String> productIds, OrderStatus orderStatus) {
        List<Product> products = new ArrayList<>();
        for (String productId : productIds) {
           Optional <Product> productToOrder = productRepo.getProductById(productId);
            if (productToOrder.isEmpty()) {
                throw new ProductNotFoundException(productId);
            }
            products.add(productToOrder.get());
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

    public Order updateOrder(String orderId,OrderStatus newStatus) throws OrderNotFoundException {
        Order order  = orderRepo.getOrderById(orderId);
        if (order ==  null) {
            throw new OrderNotFoundException (orderId);
        }
        Order updatedOrder =  order.withOrderStatus(newStatus );
        return orderRepo.addOrder(updatedOrder);
    }
}
