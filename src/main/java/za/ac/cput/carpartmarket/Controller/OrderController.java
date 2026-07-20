package za.ac.cput.carpartmarket.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.carpartmarket.Domain.Order;
import za.ac.cput.carpartmarket.Service.OrderService;

@RestController
@RequestMapping
public class OrderController {
    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    @PostMapping("/create")
    public Order create(@RequestBody Order order){
        return orderService.create(order);
    }

    @GetMapping("/read/{orders}")
    public Order read(@PathVariable("orderId") Long orderId){
        return orderService.read(orderId);
    }

    @PutMapping("/update")
    public Order update(@RequestBody Order order){
        return orderService.update(order);
    }

    @DeleteMapping("/delete/{orderId}")
    public void delete(@PathVariable("orderId") Long orderId){
        orderService.delete(orderId);
    }
}
