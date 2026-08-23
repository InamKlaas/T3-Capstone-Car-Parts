package za.ac.cput.carpartmarket.Domain;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;

@Entity
public class Seller extends User {

    @Embedded
    private Name sellerName;
    private String sellingPart;

    protected Seller() {
    }

    public Seller(Builder builder) {
        super(builder.userid, builder.sellerName, builder.email, builder.password, builder.phoneNumber, builder.createdAt);
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
                "userid=" + userid +
                ", sellerName=" + sellerName +
                ", sellingPart='" + sellingPart + '\'' +
                '}';
    }

    public static class Builder {
        private String userid;
        private Name sellerName;
        private String sellingPart;
        private String email;
        private String password;
        private String phoneNumber;
        private String createdAt;

        public Builder setUserid(String userid) {
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
        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }
        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }
        public Builder setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }
        public Builder setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder copy( Seller seller) {
            this.userid = seller.userid;
            this.sellerName = seller.sellerName;
            this.email = seller.email;
            this.password = seller.password;
            this.phoneNumber = seller.phoneNumber;
            this.createdAt = seller.createdAt;
            return this;
        }


        public Seller build() {
            return new Seller(this);
        }
    }
}
