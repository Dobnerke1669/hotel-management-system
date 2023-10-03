package ro.fortech.bigproject.controllers;


import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ro.fortech.bigproject.entities.Order;
import ro.fortech.bigproject.payload.response.*;
import ro.fortech.bigproject.services.OrderService;
import ro.fortech.bigproject.services.ProductService;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/kitchen")
@PreAuthorize("hasRole('KITCHEN')")
public class KitchenController {


    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    @Operation(summary = "Mark order as done", description = "Mark order as done")
    @PutMapping("/markOrderDone/{id}")
    public ResponseEntity<MessageResponse> markOrderDone(@PathVariable Long id) {
        orderService.markOrderDone(id);
        return ResponseEntity.ok(new MessageResponse("Order is done"));
    }

    @Operation(summary = "Get all orders", description = "Get all orders")
    @GetMapping("/getOrders")
    public List<OrderResponse> getOrders() {
        List<Order> orders = orderService.findAllOrders();
        List<OrderResponse> orderResponses = new ArrayList<>();

        for(Order order : orders){
            OrderResponse orderResponse = new OrderResponse(order.getId(),order.getTotalPrice(),order.getRoomNumber(), order.isDone());
            orderResponses.add(orderResponse);
        }
        return orderResponses;
    }

    @PostMapping("/markUnavailable/{id}")
    public ResponseEntity<?> markProductUnavailable(@PathVariable Long id) {
        productService.markUnavailable(id);
        return ResponseEntity.ok(new MessageResponse("Product marked unavailable"));
    }
    @GetMapping("/getOrderedProducts/{id}")
    public List<OrderedProducts> getOrderedItems(@PathVariable Long id) {
        return orderService.getOrderedItems(id);
    }
}


