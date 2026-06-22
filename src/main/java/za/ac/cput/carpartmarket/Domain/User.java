package za.ac.cput.carpartmarket.Domain;


import jakarta.persistence.Embedded;
import jakarta.persistence.Id;

public class User {
    @Id
    private Long userid;

    @Embedded
    private Name name;

    private String email;
    private String password;
    private double phoneNumber;
    private String createdAt;

    protected User() {

    }

    public User(Builder builder) {
        this.userid = builder.userid;
        this.name = builder.name;
        this.email = builder.email;
        this.password = builder.password;
        this.phoneNumber = builder.phoneNumber;
        this.createdAt = builder.createdAt;
    }

    public Long getUserid() {
        return userid;
    }

    public Name getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public double getPhoneNumber() {
        return phoneNumber;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString() {
        return "User{" +
                "userid=" + userid +
                ", name=" + name +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", createdAt='" + createdAt + '\'' +
                '}';
    }

    public static class Builder {
        private Long userid;
        private Name name;
        private String email;
        private String password;
        private double phoneNumber;
        private String createdAt;

        public Builder setUserid(Long userid) {
            this.userid = userid;
            return this;
        }

        public Builder setName(Name name) {
            this.name = name;
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

        public Builder setPhoneNumber(double phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder copy(User user) {
            this.userid = user.userid;
            this.name = user.name;
            this.email = user.email;
            this.password = user.password;
            this.phoneNumber = user.phoneNumber;
            this.createdAt = user.createdAt;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}