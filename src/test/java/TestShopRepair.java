import cyclechronicles.Order;
import cyclechronicles.Shop;
import cyclechronicles.Type;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestShopRepair {

    private Shop shop;
    private Order order;

    @BeforeEach
    public void setUp() {
        shop = mock(Shop.class);
        order = mock(Order.class);
    }

    @Test
    public void testShopRepairOnEmptyPendingOrders() {
        when(shop.repair()).thenReturn(Optional.empty());
        assertEquals(Optional.empty(), shop.repair());
    }

    @Test
    public void testShopRepairOnOnePendingOrders() {
        when(order.getBicycleType()).thenReturn(Type.RACE);
        when(order.getCustomer()).thenReturn("Harald");
        assumeTrue(shop.accept(order));
        when(shop.repair()).thenReturn(Optional.of(order));
        assertEquals(Optional.of(order), shop.repair());
    }

    @Test
    public void testShopRepairOnThreePendingOrders() {

        when(order.getBicycleType()).thenReturn(Type.RACE);
        when(order.getCustomer()).thenReturn("Harald");
        when(shop.repair()).thenReturn(Optional.of(order));
        shop.accept(order);
        Order order2 = mock(Order.class);
        when(order2.getBicycleType()).thenReturn(Type.RACE);
        when(order2.getCustomer()).thenReturn("Harry");
        shop.accept(order2);
        Order order3 = mock(Order.class);
        when(order3.getBicycleType()).thenReturn(Type.RACE);
        when(order3.getCustomer()).thenReturn("Tim");
        shop.accept(order3);

        assertEquals("Harald", shop.repair().get().getCustomer());
    }
}
