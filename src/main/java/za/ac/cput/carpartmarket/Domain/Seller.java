package za.ac.cput.carpartmarket.Domain;


import jakarta.persistence.*;
@Entity

public class Seller extends User {

    @Embedded
    private Name sellerName;
    private String sellingPart;


    protected Seller() {
    }

    public Seller(Builder builder){
        this.userid = builder.userid;
        this.sellerName = builder.sellerName;
        this.sellingPart = builder.sellingPart;
    }

    public Name getSellerName() {
        return sellerName;
    }

    public String getSellingPart() {
        return sellingPart;
    }

    @Override
    public String toString() {
        return "Seller{" +
                "sellerName=" + sellerName +
                ", sellingPart='" + sellingPart + '\'' +
                '}';
    }

    public static class Builder{
        private Long userid;
        private Name sellerName;
        private String sellingPart;

        public Builder setUserid(Long userid) {
            this.userid = userid;
            return this;
        }

        public Builder setSellerName(Name sellerName) {
            this.sellerName = sellerName;
            return this;
        }

        public Builder setSellingPart(String sellingPart) {
            this.sellingPart = sellingPart;
            return this;
        }

        public Builder copy(Seller seller){
            this.sellerName = seller.sellerName;
            this.sellingPart = seller.sellingPart;
            return this;
        }
         public Seller build(){
            return new Seller(this);
         }
    }

}

