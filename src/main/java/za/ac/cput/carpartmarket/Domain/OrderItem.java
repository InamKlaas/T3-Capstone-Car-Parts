package za.ac.cput.carpartmarket.Domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity

public class OrderItem {
    @Id
    private String orderItemId;
    private String orderId;
    private String partId;
    private String quantity;
    private Double unitPrice;
    private Double subTotal;

    protected OrderItem() {

    }

    public OrderItem(Builder builder) {
        this.orderItemId = builder.orderItemId;
        this.orderId = builder.orderId;
        this.partId = builder.partId;
        this.quantity = builder.quantity;
        this.unitPrice = builder.unitPrice;
        this.subTotal = builder.subTotal;
    }

    public String getOrderItemId() {
        return orderItemId;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getPartId() {
        return partId;
    }

    public String getQuantity() {
        return quantity;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public Double getSubTotal() {
        return subTotal;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "orderItemId=" + orderItemId +
                ", orderId=" + orderId +
                ", partId=" + partId +
                ", quantity='" + quantity + '\'' +
                ", unitPrice=" + unitPrice +
                ", subTotal=" + subTotal +
                '}';
    }

    public static class Builder {
        private String orderItemId;
        private String orderId;
        private String partId;
        private String quantity;
        private Double unitPrice;
        private Double subTotal;

        public Builder setOrderItemId(String orderItemId) {
            this.orderItemId = orderItemId;
            return this;
        }

        public Builder setOrderId(String orderId) {
            this.orderId = orderId;
            return this;
        }

        public Builder setPartId(String partId) {
            this.partId = partId;
            return this;
        }

        public Builder setQuantity(String quantity) {
            this.quantity = quantity;
            return this;
        }

        public Builder setUnitPrice(Double unitPrice) {
            this.unitPrice = unitPrice;
            return this;
        }

        public Builder setSubTotal(Double subTotal) {
            this.subTotal = subTotal;
            return this;
        }

        public Builder copy(OrderItem orderItem) {
            this.orderItemId = orderItem.orderItemId;
            this.orderId = orderItem.orderId;
            this.partId = orderItem.partId;
            this.quantity = orderItem.quantity;
            this.unitPrice = orderItem.unitPrice;
            this.subTotal = orderItem.subTotal;
            return this;
        }

        public OrderItem build() {
            return new OrderItem(this);
        }
    }
}