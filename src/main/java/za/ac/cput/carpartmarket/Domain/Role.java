package za.ac.cput.carpartmarket.Domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Role {
    @Id
    private String roleId;
    private String roleName;
    private String description;

    protected Role() {}

    public Role(Builder builder) {
        this.roleId = builder.roleId;
        this.roleName = builder.roleName;
        this.description = builder.description;
    }

    public String getRoleId() {
        return roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleId='" + roleId + '\'' +
                ", roleName='" + roleName + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public static class Builder {
        private String roleId;
        private String roleName;
        private String description;

        public Builder setRoleId(String roleId) {
            this.roleId = roleId;
            return this;
        }
        public Builder setRoleName(String roleName) {
            this.roleName = roleName;
            return this;
        }
        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder copy(Role role) {
            this.roleId = role.roleId;
            this.roleName = role.roleName;
            this.description = role.description;
            return this;
        }
        public Role build() {
            return new Role(this);
        }
    }
}