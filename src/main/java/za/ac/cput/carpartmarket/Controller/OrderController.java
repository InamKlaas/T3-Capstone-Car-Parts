package za.ac.cput.carpartmarket.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.carpartmarket.Domain.Order;
import za.ac.cput.carpartmarket.Service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    @PostMapping
    public Order create(@RequestBody Order order){
        return orderService.create(order);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> read(@PathVariable("id") String orderId){
        Order order = orderService.read(orderId);
        if (order == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(order);
    }

    @PutMapping
    public Order update(@RequestBody Order order){
        return orderService.update(order);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") String orderId){
        orderService.delete(orderId);
    }
}
