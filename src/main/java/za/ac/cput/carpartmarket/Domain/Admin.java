package za.ac.cput.carpartmarket.Domain;

public class Admin {

    private String adminId;
    private String user;
    private String role;
    private String permissions;

    private Admin(Builder builder){
        this.adminId = builder.adminId;
        this.user = builder.user;
        this.role = builder.role;
        this.permissions = builder.permissions;
    }

    public String getAdminId() {
        return adminId;
    }

    public String getUser() {
        return user;
    }

    public String getRole() {
        return role;
    }

    public String getPermissions() {
        return permissions;
    }

    public static class Builder{
        private String adminId;
        private String user;
        private String role;
        private String permissions;

        public Builder setAdminId(String adminId) {
            this.adminId = adminId;
            return this;
        }

        public Builder setUser(String user) {
            this.user = user;
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

        public Builder copy(Admin admin){
            this.adminId = admin.adminId;
            this.user = admin.user;
            this.role = admin.role;
            this.permissions = admin.permissions;
            return this;
        }

        @Override
        public String toString() {
            return "Builder{" +
                    "adminId=" + adminId +
                    ", user='" + user + '\'' +
                    ", role='" + role + '\'' +
                    ", permissions='" + permissions + '\'' +
                    '}';
        }

        public Admin build(){
            return new Admin(this);
        }
    }
}
