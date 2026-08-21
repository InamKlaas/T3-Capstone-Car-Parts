package za.ac.cput.carpartmarket.Controller;

import org.springframework.beans.factory.annotation.Autowired;
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
    public Order read(@PathVariable("id") Long orderId){
        return orderService.read(orderId);
    }

    @PutMapping
    public Order update(@RequestBody Order order){
        return orderService.update(order);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long orderId){
        orderService.delete(orderId);
    }
}
