package ro.fortech.bigproject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ro.fortech.project.entities.*;
import ro.fortech.project.repository.OrderRepository;
import ro.fortech.project.repository.ProductRepository;
import ro.fortech.project.repository.RoomRepository;
import ro.fortech.project.services.OrderService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTests {


    @Mock
    private OrderRepository orderRepository;

    @Mock
    private RoomRepository roomRepository;

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private OrderService orderService;
//    @Test
//    public void testAddOrder(){
//
//        ImageData imageData = new ImageData();
//        AddOrderRequest addOrderRequest = new AddOrderRequest();
//        addOrderRequest.setRoomNumber(123L);
//        addOrderRequest.setProductList(List.of(
//                new AddOrderedProductRequest(1, 2L),
//                new AddOrderedProductRequest(2, 3L)
//        ));
//
//
//        when(roomRepository.findById(123L)).thenReturn(Optional.of(new Room(123L,ERoomType.DOUBLE,BigDecimal.valueOf(200.00),new ArrayList<>())));
//        when(productRepository.findById(1L)).thenReturn(Optional.of(new Product(1L, "Product 1","category1",imageData,BigDecimal.valueOf(32.00),"description 1",true)));
//        when(productRepository.findById(2L)).thenReturn(Optional.of(new Product(2L, "Product 2","category2",imageData,BigDecimal.valueOf(12.00),"description 2",true)));
//
//        OrderService orderService = new OrderService( orderRepository,roomRepository);
//
//        Order order = orderService.addOrder(addOrderRequest);
//
//        verify(roomRepository, times(1)).findById(123L);
//        verify(productRepository, times(1)).findById(1L);
//        verify(productRepository, times(1)).findById(2L);
//        verify(orderRepository, times(1)).save(order);
//
//    }


    @Test
    public void testMarkOrderDone(){
        Long orderId = 1L;
        Order existingOrder = new Order();
        existingOrder.setDone(false);
        when(orderRepository.findById(orderId)).thenReturn(Optional.of(existingOrder));
        when(orderRepository.save(any(Order.class))).thenAnswer(invocation -> {
            Order savedOrder = invocation.getArgument(0);
            savedOrder.setDone(true);
            return savedOrder;
        });
        Order resultOrder = orderService.markOrderDone(orderId);
        verify(orderRepository, times(1)).findById(orderId);
        verify(orderRepository, times(1)).save(existingOrder);
        assertTrue(resultOrder.isDone());
    }
    @Test
    public void testFindAllOrders(){
        Order order1 = new Order();
        Order order2 = new Order();
        List<Order> orders = Arrays.asList(order1, order2);
        when(orderRepository.findAll()).thenReturn(orders);
        List<Order> resultOrders = orderService.findAllOrders();
        verify(orderRepository, times(1)).findAll();
        assertEquals(2, resultOrders.size());
        assertTrue(resultOrders.contains(order1));
        assertTrue(resultOrders.contains(order2));
    }

}
