package za.ac.cput.carpartmarket.Factory;

import za.ac.cput.carpartmarket.Domain.Buyer;
import za.ac.cput.carpartmarket.Domain.Order;
import za.ac.cput.carpartmarket.Domain.OrderItem;
import za.ac.cput.carpartmarket.Util.Helper;

import java.time.LocalDateTime;
import java.util.List;

public class OrderFactory {

    public static final List<String> PAYMENT_METHODS = List.of("payfast", "card", "eft", "snapscan", "cod");

    // ---------- Original factory method (kept for existing tests) ----------
    public static Order createOrder(String orderId, Buyer buyer, String status, LocalDateTime orderDate,
                                    double totalAmount, String deliveryId) {

        if (Helper.isEmptyOrNull(orderId)) return null;
        if (buyer == null) return null;
        if (Helper.isEmptyOrNull(status)) return null;
        if (orderDate == null) return null;
        if (totalAmount <= 0) return null;
        if (Helper.isEmptyOrNull(deliveryId)) return null;

        return new Order.Builder()
                .setOrderId(orderId)
                .setOrderDate(orderDate)
                .setStatus(status)
                .setDeliveryId(deliveryId)
                .setBuyer(buyer)
                .setTotalAmount(totalAmount)
                .build();
    }

    // ---------- Full order with items: totals are always calculated here ----------
    public static Order createOrder(String orderId, Buyer buyer, List<OrderItem> items,
                                    double deliveryFee, double discount, String couponCode,
                                    String paymentMethod) {

        if (Helper.isEmptyOrNull(orderId)) return null;
        if (buyer == null) return null;
        if (items == null || items.isEmpty()) return null;
        if (paymentMethod == null || !PAYMENT_METHODS.contains(paymentMethod)) return null;
        if (deliveryFee < 0 || discount < 0) return null;

        for (OrderItem item : items) {
            if (item == null
                    || Helper.isEmptyOrNull(item.getPartId())
                    || item.getQuantityAsInt() <= 0
                    || item.getUnitPrice() == null
                    || item.getUnitPrice() <= 0) {
                return null;
            }
        }

        double subtotal = round2(items.stream().mapToDouble(OrderItem::getLineTotal).sum());
        double safeDiscount = round2(Math.min(discount, subtotal + deliveryFee)); // never below R0
        double total = round2(subtotal + deliveryFee - safeDiscount);


        String status = (paymentMethod.equals("eft") || paymentMethod.equals("cod")) ? "PENDING" : "PAID";

        return new Order.Builder()
                .setOrderId(orderId)
                .setOrderDate(LocalDateTime.now())
                .setBuyer(buyer)
                .setItems(items)
                .setSubtotal(subtotal)
                .setDeliveryFee(deliveryFee)
                .setDiscount(safeDiscount)
                .setCouponCode(couponCode)
                .setTotalAmount(total)
                .setPaymentMethod(paymentMethod)
                .setStatus(status)
                .build();
    }

    private static double round2(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
}