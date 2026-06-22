package za.ac.cput.carpartmarket.Domain;

import jakarta.persistence.*;

@Entity
@Table(name = "order_items")
public class OrderItem {

    @Id
    private Long orderItemId;

    private Long orderId;

    private Long partId;

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

    public Long getOrderItemId() {
        return orderItemId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public Long getPartId() {
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

    public Double getTotalPrice() {
        try {
            int qty = Integer.parseInt(quantity);
            return qty * unitPrice;
        } catch (NumberFormatException e) {
            return null;
        }
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
        private Long orderItemId;
        private Long orderId;
        private Long partId;
        private String quantity;
        private Double unitPrice;
        private Double subTotal;

        public Builder setOrderItemId(Long orderItemId) {
            this.orderItemId = orderItemId;
            return this;
        }

        public Builder setOrderId(Long orderId) {
            this.orderId = orderId;
            return this;
        }

        public Builder setPartId(Long partId) {
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

        public Builder copy() {
            return new Builder()
                    .setOrderItemId(this.orderItemId)
                    .setOrderId(this.orderId)
                    .setPartId(this.partId)
                    .setQuantity(this.quantity)
                    .setUnitPrice(this.unitPrice)
                    .setSubTotal(this.subTotal);
        }

        public OrderItem build() {
            return new OrderItem(this);
        }
    }
}
