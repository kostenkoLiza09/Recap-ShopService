import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ShopServiceTest {

    @Test
    void addOrderTest() {
        //GIVEN
        ShopService shopService = new ShopService();
        List<String> productsIds = List.of("1");

        //WHEN
        Order actual = shopService.addOrder(productsIds,OrderStatus.PROCESSING);

        //THEN
        Order expected = new Order("-1", List.of(new Product("1", "Apfel")),OrderStatus.PROCESSING);
        assertEquals(expected.products(), actual.products());
        assertNotNull(expected.id());
        assertEquals(OrderStatus.PROCESSING, actual.orderStatus());
    }

    @Test
    void addOrderTest_whenInvalidProductId_expectNull() {
        //GIVEN
        ShopService shopService = new ShopService();
        List<String> productsIds = List.of("1", "2");

        //WHEN
        Order actual = shopService.addOrder(productsIds, OrderStatus.PROCESSING );

        //THEN
        assertNull(actual);
    }

    @Test
    void getTestOrderWithStatus (){
        ShopService shopService = new ShopService();

        shopService.addOrder(List.of("1", "Apple"), OrderStatus.COMPLETED );
        shopService.addOrder(List.of("2", "Banana"), OrderStatus.PROCESSING);
        shopService.addOrder(List.of("3", "Peach"), OrderStatus.PROCESSING);

        List<Order> orderList = shopService.getOrderWithStatus(OrderStatus.PROCESSING);

        for (Order order : orderList) {
            assertEquals(OrderStatus.PROCESSING, order.orderStatus());
        }
    }
}
