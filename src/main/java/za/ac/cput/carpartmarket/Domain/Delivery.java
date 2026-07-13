package za.ac.cput.carpartmarket.Domain;

public class Delivery {

    private Long deliveryId;
    private Long orderId;
    private Long addressId;
    private String courierName;
    private String trackingNumber;
    private String deliveryDate;
    private String deliveryStatus;
    private Double deliveryCost;

    protected Delivery(){

    }

    public Delivery(Builder builder){
        this.deliveryId = builder.deliveryId;
        this.orderId = builder.orderId;
        this.addressId = builder.addressId;
        this.courierName = builder.courierName;
        this.trackingNumber = builder.trackingNumber;
        this.deliveryDate = builder.deliveryDate;
        this.deliveryStatus = builder.deliveryStatus;
        this.deliveryCost = builder.deliveryCost;
    }

    public Long getDeliveryId() {
        return deliveryId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public Long getAddressId() {
        return addressId;
    }

    public String getCourierName() {
        return courierName;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public Double getDeliveryCost() {
        return deliveryCost;
    }

    @Override
    public String toString() {
        return "Delivery{" +
                "deliveryId=" + deliveryId +
                ", orderId=" + orderId +
                ", addressId=" + addressId +
                ", courierName='" + courierName + '\'' +
                ", trackingNumber='" + trackingNumber + '\'' +
                ", deliveryDate='" + deliveryDate + '\'' +
                ", deliveryStatus='" + deliveryStatus + '\'' +
                ", deliveryCost=" + deliveryCost +
                '}';
    }

    public static class Builder{
        private Long deliveryId;
        private Long orderId;
        private Long addressId;
        private String courierName;
        private String trackingNumber;
        private String deliveryDate;
        private String deliveryStatus;
        private Double deliveryCost;

        public Builder setDeliveryId(Long deliveryId) {
            this.deliveryId = deliveryId;
            return this;
        }

        public Builder setOrderId(Long orderId) {
            this.orderId = orderId;
            return this;
        }

        public Builder setAddressId(Long addressId) {
            this.addressId = addressId;
            return this;
        }

        public Builder setCourierName(String courierName) {
            this.courierName = courierName;
            return this;
        }

        public Builder setTrackingNumber(String trackingNumber) {
            this.trackingNumber = trackingNumber;
            return this;
        }

        public Builder setDeliveryDate(String deliveryDate) {
            this.deliveryDate = deliveryDate;
            return this;
        }

        public Builder setDeliveryStatus(String deliveryStatus) {
            this.deliveryStatus = deliveryStatus;
            return this;
        }

        public Builder setDeliveryCost(Double deliveryCost) {
            this.deliveryCost = deliveryCost;
            return this;
        }

        public Builder copy(Delivery delivery){
            this.deliveryId = delivery.deliveryId;
            this.orderId = delivery.orderId;
            this.addressId = delivery.addressId;
            this.courierName = delivery.courierName;
            this.trackingNumber = delivery.trackingNumber;
            this.deliveryDate = delivery.deliveryDate;
            this.deliveryStatus = delivery.deliveryStatus;
            this.deliveryCost = delivery.deliveryCost;
            return this;
        }

        public Delivery build(){return new Delivery(this);
        }
    }
}