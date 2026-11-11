package za.ac.cput.carpartmarket.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.carpartmarket.DTO.PlaceOrderRequestDTO;
import za.ac.cput.carpartmarket.Domain.Order;
import za.ac.cput.carpartmarket.Domain.User;
import za.ac.cput.carpartmarket.Repository.IUserRepository;
import za.ac.cput.carpartmarket.Service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;
    private final IUserRepository userRepository;

    public OrderController(OrderService orderService, IUserRepository userRepository) {
        this.orderService = orderService;
        this.userRepository = userRepository;
    }

    private String currentUserId(Authentication auth) {
        return userRepository.findByEmail(auth.getName()).map(User::getUserid).orElse(null);
    }

    private boolean isAdmin(Authentication auth) {
        return auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
    }

    // Buyer places an order
    @PostMapping
    public ResponseEntity<?> place(@RequestBody PlaceOrderRequestDTO request, Authentication auth) {
        try {
            Order order = orderService.placeOrder(auth.getName(), request);
            return ResponseEntity.ok(orderService.toResponse(order));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Buyer: my orders
    @GetMapping("/mine")
    public ResponseEntity<?> mine(Authentication auth) {
        List<Order> orders = orderService.findForBuyer(currentUserId(auth));
        return ResponseEntity.ok(orders.stream().map(orderService::toResponse).toList());
    }

    // Seller: orders containing my parts
    @GetMapping("/seller")
    public ResponseEntity<?> forSeller(Authentication auth) {
        List<Order> orders = orderService.findForSeller(currentUserId(auth));
        return ResponseEntity.ok(orders.stream().map(orderService::toResponse).toList());
    }

    // Admin: all orders
    @GetMapping
    public ResponseEntity<?> all(Authentication auth) {
        if (!isAdmin(auth)) return ResponseEntity.status(403).body("Admins only");
        return ResponseEntity.ok(orderService.findAll().stream().map(orderService::toResponse).toList());
    }

    // One order: its buyer, a seller in it, or an admin
    @GetMapping("/{id}")
    public ResponseEntity<?> read(@PathVariable String id, Authentication auth) {
        Order order = orderService.read(id);
        if (order == null) return ResponseEntity.status(404).body("Order not found");
        if (!orderService.canView(order, currentUserId(auth), isAdmin(auth))) {
            return ResponseEntity.status(403).body("You can't view this order");
        }
        return ResponseEntity.ok(orderService.toResponse(order));
    }

    // Seller (with a part in the order) or admin marks it shipped
    @PutMapping("/{id}/ship")
    public ResponseEntity<?> ship(@PathVariable String id, Authentication auth) {
        Order order = orderService.read(id);
        if (order == null) return ResponseEntity.status(404).body("Order not found");
        if (!orderService.canShip(order, currentUserId(auth), isAdmin(auth))) {
            return ResponseEntity.status(403).body("You can only ship orders for your own parts");
        }
        return ResponseEntity.ok(orderService.toResponse(orderService.markShipped(order)));
    }

    // Admin only
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id, Authentication auth) {
        if (!isAdmin(auth)) return ResponseEntity.status(403).body("Admins only");
        return ResponseEntity.ok(orderService.delete(id));
    }
}