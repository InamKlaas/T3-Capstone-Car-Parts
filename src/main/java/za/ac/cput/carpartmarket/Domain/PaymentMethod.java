package za.ac.cput.carpartmarket.Domain;

public class PaymentMethod {
    private String methodId;
    private Buyer buyer;
    private String type;
    private String provider;
    private int lastDigits;

    public PaymentMethod(Builder builder){
        this.methodId = builder.methodId;
        this.buyer = builder.buyer;
        this.type = builder.type;
        this.provider = builder.provider;
        this.lastDigits = builder.lastDigits;
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

    public int getLastDigits() {
        return lastDigits;
    }

    @Override
    public String toString() {
        return "PaymentMethod{" +
                "methodId='" + methodId + '\'' +
                ", buyer=" + buyer +
                ", type='" + type + '\'' +
                ", provider='" + provider + '\'' +
                ", lastDigits=" + lastDigits +
                '}';
    }

    public static class Builder {
        private String methodId;
        private Buyer buyer;
        private String type;
        private String provider;
        private int lastDigits;

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

        public Builder setLastDigits(int lastDigits) {
            this.lastDigits = lastDigits;
            return this;
        }

        public Builder copy(PaymentMethod paymentMethod){
            this.methodId = paymentMethod.methodId;
            this.buyer = paymentMethod.buyer;
            this.type = paymentMethod.type;
            this.provider = paymentMethod.provider;
            this.lastDigits = paymentMethod.lastDigits;
            return this;
        }

        public PaymentMethod build(){
            return new PaymentMethod(this);
        }
    }
}
