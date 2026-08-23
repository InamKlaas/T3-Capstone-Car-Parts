package za.ac.cput.carpartmarket.Domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Admin extends User {

    private String role;
    private String permissions;

    protected Admin() {
    }

    private Admin(Builder builder) {
        super(builder.userid, builder.name, builder.email, builder.password, builder.phoneNumber, builder.createdAt);
        this.userid =builder.userid;
        this.role = builder.role;
        this.permissions = builder.permissions;
    }

    public String getRole() {
        return role;
    }

    public String getPermissions() {
        return permissions;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "role='" + role + '\'' +
                ", permissions='" + permissions + '\'' +
                '}';
    }

    public static class Builder {
        private String userid;
        private Name  name;
        private String email;
        private String password;
        private String phoneNumber;
        private String createdAt;
        private String role;
        private String permissions;

        public Builder setUserid(String userid) {
            this.userid = userid;
            return this;
        }
        public Builder setName(Name name) {
            this.name = name;
            return this;
        }

        public Builder setRole(String role) {
            this.role = role;
            return this;
        }

        public Builder setPermissions(String permissions) {
            this.permissions = permissions;
            return this;
        }
        public Builder setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
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

        public Builder copy(Admin admin) {
            this.userid = admin.userid;
            this.name = admin.name;
            this.role = admin.role;
            this.permissions = admin.permissions;
            this.createdAt = admin.createdAt;
            this.phoneNumber = admin.phoneNumber;
            this.email = admin.email;
            this.password = admin.password;
            return this;
        }
        public Admin build() {
            return new Admin(this);
        }
    }
}
