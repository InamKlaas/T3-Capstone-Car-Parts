package za.ac.cput.carpartmarket.Domain;


import jakarta.persistence.*;

@Entity
@Table(name = "seller")
public class Seller {

    @Id
    private Long sellerId;

    @Embedded
    private Name sellerName;

    @Embedded
    private User user;

    private String permissions;

    protected Seller() {
    }

    public Seller(Builder builder) {
        this.sellerId = builder.sellerId;
        this.sellerName = builder.sellerName;
        this.user = builder.user;
        this.permissions = builder.permissions;
    }

    public Long getSellerId() {
        return sellerId;
    }
    
    public Name getSellerName(){
        return sellerName;
    }
    
    
    public User getUser() {
        return user;
    }

    public String getPermissions() {
        return permissions;
    }

    @Override
    public String toString() {
        return "Seller{" +
                "sellerId=" + sellerId +
                ", sellerName=" + sellerName +
                ", user=" + user +
                ", permissions=" + permissions + '\'' +
                '}';
    }

    public static class Builder {
        private Long sellerId;
        private User user;
        private Name sellerName;
        private String permissions;

        public Builder setSellerId(Long sellerId) {
            this.sellerId = sellerId;
            return this;
        }
        
        public Builder setSellerName(Name sellerName) {
            this.sellerName = sellerName;
            return this;
        }
        
        public Builder setUser(User user) {
            this.user = user;
            return this;
        }

        public Builder setPermissions(String permissions) {
            this.permissions = permissions;
            return this;
        }

        public Builder copy() {
            return new Builder()
                    .setSellerId(this.sellerId)
                    .setSellerName(this.sellerName)
                    .setUser(this.user)
                    .setPermissions(this.permissions);
        }

        public Seller build() {
            return new Seller(this);
        }
    }
}
