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

        public Builder copy() {
            return new Builder()
                    .setUserid(this.userid)
                    .setSellerName(this.sellerName)
                    .setSellingPart(this.sellingPart);
        }

        public Seller build() {
            return new Seller(this);
        }
    }
}
