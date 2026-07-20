package za.ac.cput.carpartmarket.Domain;

import jakarta.persistence.Id;

public class Address {
    @Id
    private String streetNumber;
    private Long userId;
    private String suburb;
    private String city;
    private String province;
    private int postalCode;
    private String country;

    public Address (Builder builder){
        this.streetNumber = builder.streetNumber;
        this.userId = builder.userId;
        this.suburb = builder.suburb;
        this.city = builder.city;
        this.province = builder.province;
        this.postalCode = builder.postalCode;
        this.country = builder.country;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public Long getUserId() {
        return userId;
    }

    public String getSuburb() {
        return suburb;
    }

    public String getCity() {
        return city;
    }

    public String getProvince() {
        return province;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public String getCountry() {
        return country;
    }

    @Override
    public String toString() {
        return "Address{" +
                "streetNumber='" + streetNumber + '\'' +
                ", userId=" + userId +
                ", suburb='" + suburb + '\'' +
                ", city='" + city + '\'' +
                ", province='" + province + '\'' +
                ", postalCode=" + postalCode +
                ", country='" + country + '\'' +
                '}';
    }

    public static class Builder{
        private String streetNumber;
        private Long userId;
        private String suburb;
        private String city;
        private String province;
        private int postalCode;
        private String country;

        public Builder setStreetNumber(String streetNumber) {
            this.streetNumber = streetNumber;
            return this;
        }

        public Builder setUserId(Long userId) {
            this.userId = userId;
            return this;
        }

        public Builder setSuburb(String suburb) {
            this.suburb = suburb;
            return this;
        }

        public Builder setCity(String city) {
            this.city = city;
            return this;
        }

        public Builder setProvince(String province) {
            this.province = province;
            return this;
        }

        public Builder setPostalCode(int postalCode) {
            this.postalCode = postalCode;
            return this;
        }

        public Builder setCountry(String country) {
            this.country = country;
            return this;
        }

        public Builder copy(Address address){
            this.streetNumber = address.streetNumber;
            this.userId = address.userId;
            this.suburb = address.suburb;
            this.city = address.city;
            this.province = address.province;
            this.postalCode = address.postalCode;
            this.country = address.country;
            return this;
        }
        public Address build(){
            return new Address(this);
        }
    }
}
