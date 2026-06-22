package za.ac.cput.carpartmarket.Domain;

import jakarta.persistence.*;

@Entity
@Table(name = "buyer")
public class Buyer {

    @Id

    private Long buyerId;

    @Embedded
    private Name buyerName;

    @Embedded
    private User user;


    protected Buyer() {
    }

    public Buyer(Builder builder) {
        this.buyerId = builder.buyerId;
        this.buyerName = builder.buyerName;
        this.user = builder.user;
    }

    public Long getBuyerId() {
        return buyerId;
    }

    public Name getBuyerName() {
        return buyerName;
    }

    public User getUser() {
        return user;
    }

    @Override
    public String toString() {
        return "Buyer{" +
                "buyerid=" + buyerId +
                ", buyerName=" + buyerName +
                ", user=" + user +
                '}';

    }
        public static class Builder {
            private Long buyerId;
            private Name buyerName;
            private User user;

            public Builder setBuyerId(Long buyerId) {
                this.buyerId = buyerId;
                return this;
            }
            public Builder setBuyerName(Name buyerName) {
                this.buyerName= buyerName;
                return this;
            }


            public Builder setUser(User user) {
                this.user = user;
                return this;
            }

            public Builder copy() {
                return new Builder()
                        .setBuyerId(this.buyerId)
                        .setBuyerName(this.buyerName)
                        .setUser(this.user);

            }

            public Buyer build() {
                return new Buyer(this);
            }
        }
    }
