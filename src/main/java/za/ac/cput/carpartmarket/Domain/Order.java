package za.ac.cput.carpartmarket.Domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    private String orderId;
    private LocalDateTime orderDate;

    @ManyToOne
    @JoinColumn(name = "buyer_id")
    private Buyer buyer;


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "orderId", insertable = false, updatable = false)
    private List<OrderItem> items = new ArrayList<>();

    private double subtotal;
    private double deliveryFee;
    private double discount;
    private String couponCode;
    private String paymentMethod;
    private double totalAmount;
    private String status;
    private String deliveryId;

    protected Order() {
    }

    public Order(Builder builder) {
        this.orderId = builder.orderId;
        this.orderDate = builder.orderDate;
        this.buyer = builder.buyer;
        this.items = builder.items;
        this.subtotal = builder.subtotal;
        this.deliveryFee = builder.deliveryFee;
        this.discount = builder.discount;
        this.couponCode = builder.couponCode;
        this.paymentMethod = builder.paymentMethod;
        this.totalAmount = builder.totalAmount;
        this.status = builder.status;
        this.deliveryId = builder.deliveryId;
    }

    public String getOrderId() { return orderId; }
    public LocalDateTime getOrderDate() { return orderDate; }
    public Buyer getBuyer() { return buyer; }
    public List<OrderItem> getItems() { return items; }
    public double getSubtotal() { return subtotal; }
    public double getDeliveryFee() { return deliveryFee; }
    public double getDiscount() { return discount; }
    public String getCouponCode() { return couponCode; }
    public String getPaymentMethod() { return paymentMethod; }
    public double getTotalAmount() { return totalAmount; }
    public String getStatus() { return status; }
    public String getDeliveryId() { return deliveryId; }

    // Status changes (e.g. seller marks as shipped)
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", orderDate=" + orderDate +
                ", buyer=" + (buyer != null ? buyer.getUserid() : null) +
                ", items=" + items +
                ", subtotal=" + subtotal +
                ", deliveryFee=" + deliveryFee +
                ", discount=" + discount +
                ", couponCode='" + couponCode + '\'' +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", totalAmount=" + totalAmount +
                ", status='" + status + '\'' +
                ", deliveryId='" + deliveryId + '\'' +
                '}';
    }

    public static class Builder {
        private String orderId;
        private LocalDateTime orderDate;
        private Buyer buyer;
        private List<OrderItem> items = new ArrayList<>();
        private double subtotal;
        private double deliveryFee;
        private double discount;
        private String couponCode;
        private String paymentMethod;
        private double totalAmount;
        private String status;
        private String deliveryId;

        public Builder setOrderId(String orderId) { this.orderId = orderId; return this; }
        public Builder setOrderDate(LocalDateTime orderDate) { this.orderDate = orderDate; return this; }
        public Builder setBuyer(Buyer buyer) { this.buyer = buyer; return this; }
        public Builder setItems(List<OrderItem> items) {
            this.items = items == null ? new ArrayList<>() : new ArrayList<>(items);
            return this;
        }
        public Builder setSubtotal(double subtotal) { this.subtotal = subtotal; return this; }
        public Builder setDeliveryFee(double deliveryFee) { this.deliveryFee = deliveryFee; return this; }
        public Builder setDiscount(double discount) { this.discount = discount; return this; }
        public Builder setCouponCode(String couponCode) { this.couponCode = couponCode; return this; }
        public Builder setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; return this; }
        public Builder setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; return this; }
        public Builder setStatus(String status) { this.status = status; return this; }
        public Builder setDeliveryId(String deliveryId) { this.deliveryId = deliveryId; return this; }

        public Builder copy(Order order) {
            this.orderId = order.orderId;
            this.orderDate = order.orderDate;
            this.buyer = order.buyer;
            this.items = new ArrayList<>(order.items);
            this.subtotal = order.subtotal;
            this.deliveryFee = order.deliveryFee;
            this.discount = order.discount;
            this.couponCode = order.couponCode;
            this.paymentMethod = order.paymentMethod;
            this.totalAmount = order.totalAmount;
            this.status = order.status;
            this.deliveryId = order.deliveryId;
            return this;
        }

        public Order build() {
            return new Order(this);
        }
    }
}