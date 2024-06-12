import cyclechronicles.Order;
import cyclechronicles.Shop;
import cyclechronicles.Type;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestShopDeliver {

    private Shop shop;
    private Order order;

    @BeforeEach
    public void setUp() {
        shop = mock(Shop.class);
        order = mock(Order.class);
    }

    @Test
    public void testShopDeliverOnEmptyCompletedOrders() {
        when(shop.deliver("")).thenReturn(Optional.empty());
        assertEquals(Optional.empty(), shop.deliver(""));
    }

    @Test
    public void testShopDeliverOnOneCompletedOrders() {
        when(order.getBicycleType()).thenReturn(Type.RACE);
        when(order.getCustomer()).thenReturn("Harald");
        when(shop.repair()).thenReturn(Optional.of(order));
        when(shop.deliver("Harald")).thenReturn(Optional.of(order));
        shop.accept(order);
        shop.repair();
        assertEquals(Optional.of(order), shop.deliver("Harald"));
    }

    @Test
    public void testShopDeliverOnThreeCompletedOrders() {

        when(order.getBicycleType()).thenReturn(Type.RACE);
        when(order.getCustomer()).thenReturn("Harald");
        when(shop.repair()).thenReturn(Optional.of(order));
        when(shop.deliver("Harald")).thenReturn(Optional.of(order));
        shop.accept(order);
        shop.repair();
        Order order2 = mock(Order.class);
        when(order2.getBicycleType()).thenReturn(Type.RACE);
        when(order2.getCustomer()).thenReturn("Harry");
        when(shop.repair()).thenReturn(Optional.of(order2));
        shop.accept(order2);
        shop.repair();
        Order order3 = mock(Order.class);
        when(order3.getBicycleType()).thenReturn(Type.RACE);
        when(order3.getCustomer()).thenReturn("Tim");
        when(shop.repair()).thenReturn(Optional.of(order3));
        shop.accept(order3);
        shop.repair();

        assertEquals(Optional.of(order), shop.deliver("Harald"));
    }
}
