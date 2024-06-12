import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.mockito.Mockito.*;

import cyclechronicles.Order;
import cyclechronicles.Shop;
import cyclechronicles.Type;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestShopAccept {

    private Shop shop;
    private Order order;

    @BeforeEach
    public void setup() {
        shop = new Shop();
        order = mock(Order.class);
    }

    @Test
    public void testNotAcceptEBike() {
        when(order.getBicycleType()).thenReturn(Type.EBIKE);
        when(order.getCustomer()).thenReturn("");
        assertFalse(shop.accept(order));
    }

    @Test
    public void testNotAcceptGravel() {
        when(order.getBicycleType()).thenReturn(Type.GRAVEL);
        when(order.getCustomer()).thenReturn("Harry");
        assertFalse(shop.accept(order));
    }

    @Test
    public void testNotAcceptCustomerOrderCountGreaterZero() {
        when(order.getBicycleType()).thenReturn(Type.RACE);
        when(order.getCustomer()).thenReturn("Harald");
        assumeTrue(order.getBicycleType() == Type.RACE);
        assumeTrue(order.getCustomer().equals("Harald"));
        shop.accept(order);
        when(order.getBicycleType()).thenReturn(Type.RACE);
        when(order.getCustomer()).thenReturn("Harald");
        assumeTrue(order.getBicycleType() == Type.RACE);
        assumeTrue(order.getCustomer().equals("Harald"));
        assertFalse(shop.accept(order));
    }

    @Test
    public void testAcceptShopOrderCountLessFiveAndNotAcceptShopOrderCountGreaterFour() {
        when(order.getBicycleType()).thenReturn(Type.RACE);
        when(order.getCustomer()).thenReturn("Harald");
        assumeTrue(order.getBicycleType() == Type.RACE);
        assumeTrue(order.getCustomer().equals("Harald"));
        shop.accept(order);
        when(order.getBicycleType()).thenReturn(Type.FIXIE);
        when(order.getCustomer()).thenReturn("Harry");
        assumeTrue(order.getBicycleType() == Type.FIXIE);
        assumeTrue(order.getCustomer().equals("Harry"));
        shop.accept(order);
        when(order.getBicycleType()).thenReturn(Type.RACE);
        when(order.getCustomer()).thenReturn("Tim");
        assumeTrue(order.getBicycleType() == Type.RACE);
        assumeTrue(order.getCustomer().equals("Tim"));
        shop.accept(order);
        when(order.getBicycleType()).thenReturn(Type.FIXIE);
        when(order.getCustomer()).thenReturn("Tom");
        assumeTrue(order.getBicycleType() == Type.FIXIE);
        assumeTrue(order.getCustomer().equals("Tom"));
        shop.accept(order);
        when(order.getBicycleType()).thenReturn(Type.SINGLE_SPEED);
        when(order.getCustomer()).thenReturn("Eva");
        assumeTrue(order.getBicycleType() == Type.SINGLE_SPEED);
        assumeTrue(order.getCustomer().equals("Eva"));
        assertFalse(shop.accept(order));
    }

    @Test
    public void testAcceptCustomerOrderCountEqualsZero() {
        when(order.getBicycleType()).thenReturn(Type.RACE);
        when(order.getCustomer()).thenReturn("Harald");
        assumeTrue(order.getBicycleType() == Type.RACE);
        assumeTrue(order.getCustomer().equals("Harald"));
        assertEquals(true, shop.accept(order));
    }
}
