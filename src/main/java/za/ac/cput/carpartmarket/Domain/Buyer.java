package za.ac.cput.carpartmarket.Domain;

import jakarta.persistence.*;

@Entity
@Table(name = "buyers")
public class Buyer {

    @Id

    private Long buyerId;

    @Embedded
    private User user;


    private Buyer(Builder builder) {
        this.buyerId = builder.buyerId;
        this.user = builder.user;

    }

    protected Buyer() {
    }

    private Buyer(Builder builder) {
        this.buyerId = builder.buyerId;
        this.user = builder.user;
    }

    public Long getBuyerId() {
        return buyerId;
    }

    public User getUser() {
        return user;
    }

    @Override
    public String toString() {
        return "Buyer{" +
                "buyerId=" + buyerId +
                ", user=" + '}';

    }
        public static class Builder {
            private Long buyerId;
            private User user;

            public Builder setBuyerId(Long buyerId) {
                this.buyerId = buyerId;
                return this;
            }

            public Builder setUser(User user) {
                this.user = user;
                return this;
            }

            public Builder copy() {
                return new Builder()
                        .setBuyerId(this.buyerId)
                        .setUser(this.user)

            }

            public Buyer build() {
                return new Buyer(this);
            }
        }
    }
}