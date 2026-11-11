package za.ac.cput.carpartmarket.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import za.ac.cput.carpartmarket.DTO.OrderResponseDTO;
import za.ac.cput.carpartmarket.DTO.PlaceOrderRequestDTO;
import za.ac.cput.carpartmarket.Domain.*;
import za.ac.cput.carpartmarket.Factory.OrderFactory;
import za.ac.cput.carpartmarket.Repository.CarPartRepository;
import za.ac.cput.carpartmarket.Repository.IUserRepository;
import za.ac.cput.carpartmarket.Repository.OrderRepository;

import java.security.SecureRandom;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

@Service
public class OrderService implements IOrderService {

    public static final double DELIVERY_FEE = 50.0;

    private static final String REF_CHARS = "ABCDEFGHJKLMNPQRSTUVWXYZ23456789";
    private static final DateTimeFormatter DATE_FORMAT =
            DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm", Locale.ENGLISH);

    @Autowired
    private OrderRepository repository;

    @Autowired
    private CarPartRepository carPartRepository;

    @Autowired
    private IUserRepository userRepository;

    private final SecureRandom random = new SecureRandom();

    // ---------- Existing CRUD ----------

    @Override
    public Order create(Order order) {
        return repository.save(order);
    }

    @Override
    public Order read(String str) {
        return repository.findById(str).orElse(null);
    }

    @Override
    public Order update(Order order) {
        return repository.save(order);
    }

    @Override
    public boolean delete(String l) {
        repository.deleteById(l);
        return true;
    }

    // ---------- Placing an order ----------

    @Transactional
    public Order placeOrder(String buyerEmail, PlaceOrderRequestDTO request) {
        User user = userRepository.findByEmail(buyerEmail)
                .orElseThrow(() -> new IllegalArgumentException("Unknown buyer."));
        if (!(user instanceof Buyer buyer)) {
            throw new IllegalArgumentException("Only buyers can place orders.");
        }
        if (request == null || request.items() == null || request.items().isEmpty()) {
            throw new IllegalArgumentException("Your cart is empty.");
        }

        String reference = generateReference();

        // Build the lines from the DATABASE, not from what the browser sent
        List<OrderItem> items = new ArrayList<>();
        for (PlaceOrderRequestDTO.Line line : request.items()) {
            CarPart part = carPartRepository.findById(line.carPartId())
                    .orElseThrow(() -> new IllegalArgumentException("A part in your cart no longer exists."));

            if (!"APPROVED".equalsIgnoreCase(part.getStatus())) {
                throw new IllegalArgumentException(part.getPartName() + " is no longer available.");
            }
            if (line.quantity() <= 0 || line.quantity() > part.getStockQuantity()) {
                throw new IllegalArgumentException(
                        "Only " + part.getStockQuantity() + " of " + part.getPartName() + " left in stock.");
            }

            items.add(new OrderItem.Builder()
                    .setOrderItemId(UUID.randomUUID().toString())
                    .setOrderId(reference)
                    .setPartId(part.getCarPartId())
                    .setPartName(part.getPartName())
                    .setQuantity(String.valueOf(line.quantity()))
                    .setUnitPrice(part.getPrice())
                    .setSubTotal(part.getPrice() * line.quantity())
                    .setSellerId(part.getSellerId())
                    .setCategory(part.getCategoryId() == null ? null : String.valueOf(part.getCategoryId()))
                    .build());
        }

        double subtotal = items.stream().mapToDouble(OrderItem::getLineTotal).sum();
        double discount = couponDiscount(request.couponCode(), subtotal, DELIVERY_FEE);
        String couponCode = discount > 0 ? request.couponCode().trim().toUpperCase() : null;

        Order order = OrderFactory.createOrder(
                reference, buyer, items, DELIVERY_FEE, discount, couponCode, request.paymentMethod());
        if (order == null) {
            throw new IllegalArgumentException("Invalid order details.");
        }

        // Reduce stock; a part that runs out is marked SOLD and leaves the marketplace
        for (OrderItem item : items) {
            CarPart part = carPartRepository.findById(item.getPartId()).orElseThrow();
            int left = part.getStockQuantity() - item.getQuantityAsInt();
            CarPart updated = new CarPart.Builder()
                    .copy(part)
                    .setStockQuantity(left)
                    .setStatus(left <= 0 ? "SOLD" : part.getStatus())
                    .build();
            carPartRepository.save(updated);
        }

        return repository.save(order);
    }

    // Same rules as the frontend's coupons.ts, checked on the server
    private double couponDiscount(String code, double subtotal, double delivery) {
        if (code == null || code.isBlank()) return 0;
        switch (code.trim().toUpperCase()) {
            case "WELCOME10":
                return Math.round(subtotal * 10) / 100.0;   // 10%
            case "SAVE50":
                return subtotal >= 500 ? Math.min(50, subtotal) : 0;
            case "FREESHIP":
                return delivery;
            default:
                return 0;
        }
    }

    private String generateReference() {
        String ref;
        do {
            StringBuilder sb = new StringBuilder("FR-");
            for (int i = 0; i < 8; i++) {
                sb.append(REF_CHARS.charAt(random.nextInt(REF_CHARS.length())));
            }
            ref = sb.toString();
        } while (repository.existsById(ref));
        return ref;
    }

    // ---------- Reading orders ----------

    public List<Order> findForBuyer(String buyerId) {
        return repository.findByBuyer_UseridOrderByOrderDateDesc(buyerId);
    }

    public List<Order> findForSeller(String sellerId) {
        return repository.findBySellerId(sellerId);
    }

    public List<Order> findAll() {
        return repository.findAllByOrderByOrderDateDesc();
    }

    // Buyer of the order, a seller with a part in it, or an admin
    public boolean canView(Order order, String userId, boolean admin) {
        if (admin) return true;
        if (userId == null) return false;
        if (order.getBuyer() != null && userId.equals(order.getBuyer().getUserid())) return true;
        return order.getItems().stream().anyMatch(i -> userId.equals(i.getSellerId()));
    }

    // Only a seller with a part in the order, or an admin
    public boolean canShip(Order order, String userId, boolean admin) {
        if (admin) return true;
        return userId != null && order.getItems().stream().anyMatch(i -> userId.equals(i.getSellerId()));
    }

    public Order markShipped(Order order) {
        order.setStatus("SHIPPED");
        return repository.save(order);
    }

    // ---------- Response mapping ----------

    public OrderResponseDTO toResponse(Order order) {
        List<OrderResponseDTO.Item> items = order.getItems().stream()
                .map(i -> new OrderResponseDTO.Item(
                        i.getPartId(),
                        i.getPartName(),
                        i.getUnitPrice() == null ? 0 : i.getUnitPrice(),
                        i.getQuantityAsInt(),
                        i.getSellerId(),
                        i.getCategory(),
                        carPartRepository.findById(i.getPartId()).map(CarPart::getImage).orElse(null)
                ))
                .toList();

        return new OrderResponseDTO(
                order.getOrderId(),
                order.getOrderDate() == null ? "" : order.getOrderDate().format(DATE_FORMAT),
                order.getStatus(),
                order.getBuyer() == null ? null : order.getBuyer().getEmail(),
                items,
                order.getSubtotal(),
                order.getDeliveryFee(),
                order.getDiscount(),
                order.getCouponCode(),
                order.getTotalAmount(),
                order.getPaymentMethod()
        );
    }
}