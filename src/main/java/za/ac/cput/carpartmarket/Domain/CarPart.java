package za.ac.cput.carpartmarket.Domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

@Entity
public class CarPart {
    @Id
    private String carPartId;
    private String partName;
    private String description;
    private Double price;
    private int stockQuantity;
    private String model;
    private Long categoryId;
    private String sellerId;
    private String status;

    @Lob
//    @Column(columnDefinition = "TEXT")
    private String image;

    protected CarPart(){

    }

    public CarPart(Builder builder){
        this.carPartId = builder.carPartId;
        this.partName = builder.partName;
        this.description = builder.description;
        this.price = builder.price;
        this.stockQuantity = builder.stockQuantity;
        this.model = builder.model;
        this.categoryId = builder.categoryId;
        this.sellerId = builder.sellerId;
        this.status = builder.status;
        this.image = builder.image;
    }

    public String getCarPartId() {
        return carPartId;
    }

    public String getPartName() {
        return partName;
    }

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public String getModel() {
        return model;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public String getSellerId() {
        return sellerId;
    }

    public String getStatus() { return status; }

    public String getImage() { return image; }

    public void setStatus(String status) { this.status = status; }

//    public void setImage(String image) { this.image = image; }

    @Override
    public String toString() {
        return "CarPart{" +
                "carPartId=" + carPartId +
                ", partName='" + partName + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", stockQuantity=" + stockQuantity +
                ", model='" + model + '\'' +
                ", categoryId=" + categoryId +
                ", sellerId=" + sellerId +
                ", status=" + status +
                ", image=" + image +
                '}';
    }

    public static class Builder{
        private String carPartId;
        private String partName;
        private String description;
        private Double price;
        private int stockQuantity;
        private String model;
        private Long categoryId;
        private String sellerId;
        private String status;
        private String image;

        public Builder setCarPartId(String carPartId) {
            this.carPartId = carPartId;
            return this;
        }

        public Builder setPartName(String partName) {
            this.partName = partName;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setPrice(Double price) {
            this.price = price;
            return this;
        }

        public Builder setStockQuantity(int stockQuantity) {
            this.stockQuantity = stockQuantity;
            return this;
        }

        public Builder setModel(String model) {
            this.model = model;
            return this;
        }

        public Builder setCategoryId(Long categoryId) {
            this.categoryId = categoryId;
            return this;
        }

        public Builder setSellerId(String sellerId) {
            this.sellerId = sellerId;
            return this;
        }

        public Builder setStatus(String status) {
            this.status = status;
            return this;
        }

        public Builder setImage(String image){
            this.image = image;
            return this;
        }

        public Builder copy(CarPart carPart){
            this.carPartId = carPart.carPartId;
            this.partName = carPart.partName;
            this.description = carPart.description;
            this.price = carPart.price;
            this.stockQuantity = carPart.stockQuantity;
            this.model = carPart.model;
            this.categoryId = carPart.categoryId;
            this.sellerId = carPart.sellerId;
            this.status = carPart.status;
            this.image = carPart.image;
            return this;
        }

        public CarPart build(){return new CarPart(this);
        }
    }
}