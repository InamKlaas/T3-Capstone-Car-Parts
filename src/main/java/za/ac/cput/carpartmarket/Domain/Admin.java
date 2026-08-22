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
        private String role;
        private String permissions;

        public Builder setUserid(String userid) {
            this.userid = userid;
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

        public Builder copy(Admin admin) {
            this.userid = admin.userid;
            this.role = admin.role;
            this.permissions = admin.permissions;
            return this;
        }
        public Admin build() {
            return new Admin(this);
        }
    }
}
