package za.ac.cput.carpartmarket.Domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;
@Entity

@Table(name = "orders")  // Change from "order"
public class Order {
    @Id
    private String orderId;
    private LocalDateTime orderDate;
    @ManyToOne
    @JoinColumn(name = "buyer_id")
    private Buyer buyer;
    private double totalAmount;
    private String status;
    private String deliveryId;

    protected Order() {
    }

    public Order(Builder builder) {
        this.orderId = builder.orderId;
        this.orderDate = builder.orderDate;
        this.buyer = builder.buyer;
        this.totalAmount = builder.totalAmount;
        this.status = builder.status;
        this.deliveryId = builder.deliveryId;
    }

    public String getOrderId() {
        return orderId;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public String getStatus() {
        return status;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public String getDeliveryId() {
        return deliveryId;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", orderDate=" + orderDate +
                ", buyer=" + buyer +
                ", totalAmount=" + totalAmount +
                ", status='" + status + '\'' +
                ", deliveryId=" + deliveryId +
                '}';
    }

    public static class Builder {
        private String orderId;
        private LocalDateTime orderDate;
        private Buyer buyer;
        private double totalAmount;
        private String status;
        private String deliveryId;

        public Builder setOrderId(String orderId) {
            this.orderId = orderId;
            return this;
        }

        public Builder setOrderDate(LocalDateTime orderDate) {
            this.orderDate = orderDate;
            return this;
        }

        public Builder setBuyer(Buyer buyer) {
            this.buyer = buyer;
            return this;
        }

        public Builder setTotalAmount(double totalAmount) {
            this.totalAmount = totalAmount;
            return this;
        }

        public Builder setStatus(String status) {
            this.status = status;
            return this;
        }

        public Builder setDeliveryId(String deliveryId) {
            this.deliveryId = deliveryId;
            return this;
        }

        public Builder copy(Order order) {
            this.orderId = order.orderId;
            this.orderDate = order.orderDate;
            this.buyer = order.buyer;
            this.status = order.status;
            this.totalAmount = order.totalAmount;
            this.deliveryId = order.deliveryId;
            return this;
        }

        public Order build() {
            return new Order(this);
        }
    }
}
