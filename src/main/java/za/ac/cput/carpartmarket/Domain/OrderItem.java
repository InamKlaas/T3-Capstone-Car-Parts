package za.ac.cput.carpartmarket.Domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class OrderItem {
    @Id
    private String orderItemId;
    private String orderId;
    private String partId;
    private String partName;
    private String quantity;
    private Double unitPrice;
    private Double subTotal;
    private String sellerId;
    private String category;

    protected OrderItem() {
    }

    public OrderItem(Builder builder) {
        this.orderItemId = builder.orderItemId;
        this.orderId = builder.orderId;
        this.partId = builder.partId;
        this.partName = builder.partName;
        this.quantity = builder.quantity;
        this.unitPrice = builder.unitPrice;
        this.subTotal = builder.subTotal;
        this.sellerId = builder.sellerId;
        this.category = builder.category;
    }

    public String getOrderItemId() { return orderItemId; }
    public String getOrderId() { return orderId; }
    public String getPartId() { return partId; }
    public String getPartName() { return partName; }
    public String getQuantity() { return quantity; }
    public Double getUnitPrice() { return unitPrice; }
    public Double getSubTotal() { return subTotal; }
    public String getSellerId() { return sellerId; }
    public String getCategory() { return category; }

    // NEW: helpers for calculations
    public int getQuantityAsInt() {
        try {
            return Integer.parseInt(quantity);
        } catch (Exception e) {
            return 0;
        }
    }

    public double getLineTotal() {
        return (unitPrice == null ? 0 : unitPrice) * getQuantityAsInt();
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "orderItemId=" + orderItemId +
                ", orderId=" + orderId +
                ", partId=" + partId +
                ", partName='" + partName + '\'' +
                ", quantity='" + quantity + '\'' +
                ", unitPrice=" + unitPrice +
                ", subTotal=" + subTotal +
                ", sellerId=" + sellerId +
                ", category=" + category +
                '}';
    }

    public static class Builder {
        private String orderItemId;
        private String orderId;
        private String partId;
        private String partName;
        private String quantity;
        private Double unitPrice;
        private Double subTotal;
        private String sellerId;
        private String category;

        public Builder setOrderItemId(String orderItemId) { this.orderItemId = orderItemId; return this; }
        public Builder setOrderId(String orderId) { this.orderId = orderId; return this; }
        public Builder setPartId(String partId) { this.partId = partId; return this; }
        public Builder setPartName(String partName) { this.partName = partName; return this; }
        public Builder setQuantity(String quantity) { this.quantity = quantity; return this; }
        public Builder setUnitPrice(Double unitPrice) { this.unitPrice = unitPrice; return this; }
        public Builder setSubTotal(Double subTotal) { this.subTotal = subTotal; return this; }
        public Builder setSellerId(String sellerId) { this.sellerId = sellerId; return this; }
        public Builder setCategory(String category) { this.category = category; return this; }

        public Builder copy(OrderItem orderItem) {
            this.orderItemId = orderItem.orderItemId;
            this.orderId = orderItem.orderId;
            this.partId = orderItem.partId;
            this.partName = orderItem.partName;
            this.quantity = orderItem.quantity;
            this.unitPrice = orderItem.unitPrice;
            this.subTotal = orderItem.subTotal;
            this.sellerId = orderItem.sellerId;
            this.category = orderItem.category;
            return this;
        }

        public OrderItem build() {
            return new OrderItem(this);
        }
    }
}