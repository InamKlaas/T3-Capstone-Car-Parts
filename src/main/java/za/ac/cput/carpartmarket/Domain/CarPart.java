package za.ac.cput.carpartmarket.Domain;

public class CarPart {
    private Long carPartId;
    private String partName;
    private String description;
    private Double price;
    private int stockQuantity;
    private String model;
    private Long categoryId;
    private Long sellerId;

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
    }

    public Long getCarPartId() {
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

    public Long getSellerId() {
        return sellerId;
    }

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
                '}';
    }

    public static class Builder{
        private Long carPartId;
        private String partName;
        private String description;
        private Double price;
        private int stockQuantity;
        private String model;
        private Long categoryId;
        private Long sellerId;

        public Builder setCarPartId(Long carPartId) {
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

        public Builder setSellerId(Long sellerId) {
            this.sellerId = sellerId;
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
            return this;
        }

        public CarPart build(){return new CarPart(this);
        }
    }
}


