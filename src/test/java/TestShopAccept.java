import cyclechronicles.Order;
import cyclechronicles.Shop;
import cyclechronicles.Type;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestShopAccept {

    private Shop shop;
    private Order order;

    @BeforeEach
    public void setup(){
        shop = new Shop();
        order = mock(Order.class);

    }

    @Test
    public void testNotAcceptEBike(){
        when(order.getBicycleType()).thenReturn(Type.EBIKE);
        when(order.getCustomer()).thenReturn("");
        assertFalse(shop.accept(order));
    }

    @Test
    public void testNotAcceptGravel(){
        when(order.getBicycleType()).thenReturn(Type.GRAVEL);
        when(order.getCustomer()).thenReturn("");
        assertFalse(shop.accept(order));
    }

    @Test
    public void testNotAcceptCustomerOrderCountGreaterZero(){
        when(order.getBicycleType()).thenReturn(Type.RACE);
        when(order.getCustomer()).thenReturn(anyString());
        assertFalse(shop.accept(order));
    }

}
