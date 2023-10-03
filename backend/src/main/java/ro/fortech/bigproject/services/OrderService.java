package ro.fortech.bigproject.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ro.fortech.bigproject.entities.Order;
import ro.fortech.bigproject.entities.OrderItem;
import ro.fortech.bigproject.entities.Product;
import ro.fortech.bigproject.payload.request.AddOrderRequest;
import ro.fortech.bigproject.payload.request.AddOrderedProductRequest;
import ro.fortech.bigproject.payload.response.OrderedProducts;
import ro.fortech.bigproject.repository.OrderItemRepository;
import ro.fortech.bigproject.repository.OrderRepository;
import ro.fortech.bigproject.repository.ProductRepository;
import ro.fortech.bigproject.repository.RoomRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;

    public OrderService(OrderRepository orderRepository, RoomRepository roomRepository) {
        this.orderRepository = orderRepository;
        this.roomRepository = roomRepository;
    }

    public Order addOrder(AddOrderRequest addOrderRequest) {
        roomRepository.findById(addOrderRequest.getRoomNumber())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Room with number " + addOrderRequest.getRoomNumber() + " not found"));
        Order order = new Order(addOrderRequest.getRoomNumber());
        for (AddOrderedProductRequest op : addOrderRequest.getProductList()) {
            Product product = productRepository.findById(op.getId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product with id " + op.getId() + " not found"));
            order.addItem(product, op.getQuantity());
        }
        orderRepository.save(order);
        return order;
    }

    public Order markOrderDone(Long id) {
        Order foundOrder = orderRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order with id " + id + " not found"));
        foundOrder.setDone(true);
        orderRepository.save(foundOrder);
        return foundOrder;
    }

    public List<OrderedProducts> getOrderedItems(Long id) {
        List<OrderItem> orderItems= orderItemRepository.findAll();
        List<OrderedProducts> orderedProducts= new ArrayList<>();
        for (OrderItem orderItem: orderItems) {
            if (Objects.equals(orderItem.getOrder().getId(), id)) {
                OrderedProducts orderedProduct = new OrderedProducts(orderItem.getProduct().getName(), (long) orderItem.getQuantity());
                orderedProducts.add(orderedProduct);
            }
        }
        return orderedProducts;

    }
    public List<Order> findAllOrders() {
        return orderRepository.findAll();
    }


}
