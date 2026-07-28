package za.ac.cput.carpartmarket.Domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class PaymentMethod {
    @Id
    private String methodId;
    @ManyToOne
    @JoinColumn(name = "buyer_name")
    private Buyer buyer;
    private String type;
    private String provider;
    private int cvv;

    public PaymentMethod() {}

    public PaymentMethod(Builder builder){
        this.methodId = builder.methodId;
        this.buyer = builder.buyer;
        this.type = builder.type;
        this.provider = builder.provider;
        this.cvv = builder.cvv;
    }

    public String getMethodId() {
        return methodId;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public String getType() {
        return type;
    }

    public String getProvider() {
        return provider;
    }

    public int getcvv() {
        return cvv;
    }

    @Override
    public String toString() {
        return "PaymentMethod{" +
                "methodId='" + methodId + '\'' +
                ", buyer=" + buyer +
                ", type='" + type + '\'' +
                ", provider='" + provider + '\'' +
                ", cvv=" + cvv +
                '}';
    }

    public static class Builder {
        private String methodId;
        private Buyer buyer;
        private String type;
        private String provider;
        private int cvv;

        public Builder setMethodId(String methodId) {
            this.methodId = methodId;
            return this;
        }

        public Builder setBuyer(Buyer buyer) {
            this.buyer = buyer;
            return this;
        }

        public Builder setType(String type) {
            this.type = type;
            return this;
        }

        public Builder setProvider(String provider) {
            this.provider = provider;
            return this;
        }

        public Builder setcvv(int cvv) {
            this.cvv = cvv;
            return this;
        }

        public Builder copy(PaymentMethod paymentMethod){
            this.methodId = paymentMethod.methodId;
            this.buyer = paymentMethod.buyer;
            this.type = paymentMethod.type;
            this.provider = paymentMethod.provider;
            this.cvv = paymentMethod.cvv;
            return this;
        }

        public PaymentMethod build(){
            return new PaymentMethod(this);
        }
    }
}
