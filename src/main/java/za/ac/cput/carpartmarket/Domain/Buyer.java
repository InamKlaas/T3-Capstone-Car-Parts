package za.ac.cput.carpartmarket.Domain;

import jakarta.persistence.*;

@Entity

public class Buyer extends User {


    @Embedded
    private Name buyerName;
    private String buyingPart;

    protected Buyer() {
    }

    public Buyer(Builder builder){
        super(builder.userid, builder.buyerName, builder.email, builder.password, builder.phoneNumber, builder.createdAt);
        this.userid = builder.userid;
        this.buyerName = builder.buyerName;
        this.buyingPart = builder.buyingPart;
    }

    public Name getBuyerName() {
        return buyerName;
    }

    public String getBuyingPart() {
        return buyingPart;
    }

    @Override
    public String toString() {
        return "Buyer{" +
                "buyerName=" + buyerName +
                ", buyingPart='" + buyingPart + '\'' +
                '}';
    }

    public static class Builder{
        private String userid;
        private Name buyerName;
        private String buyingPart;
        private String email;
        private String password;
        private String phoneNumber;
        private String createdAt;

        public Builder setUserid(String userid) {
            this.userid = userid;
            return this;
        }
        public Builder setBuyerName(Name buyerName) {
            this.buyerName = buyerName;
            return this;
        }
        public Builder setBuyingPart(String buyingPart) {
            this.buyingPart = buyingPart;
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

        public Builder copy(Buyer buyer){
            this.userid = buyer.userid;
            this.buyerName = buyer.buyerName;
            this.buyingPart = buyer.buyingPart;
            this.email = buyer.email;
            this.password = buyer.password;
            this.phoneNumber = buyer.phoneNumber;
            return this;
        }
        public Buyer build(){
            return new Buyer(this);
        }
    }
}


