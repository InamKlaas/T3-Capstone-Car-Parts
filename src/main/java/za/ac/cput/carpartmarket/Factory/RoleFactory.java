package za.ac.cput.carpartmarket.Factory;

import za.ac.cput.carpartmarket.Domain.Role;
import za.ac.cput.carpartmarket.Util.Helper;

public class RoleFactory {
    public static Role createRole(String roleId, String roleName, String description) {
        if (Helper.isEmptyOrNull(roleId)) {
            return null;
        }
        if (Helper.isEmptyOrNull(roleName)) {
            return null;
        }
        if (Helper.isEmptyOrNull(description)) {
            return null;
        }
        return new Role.Builder()
                .setRoleId(roleId)
                .setRoleName(roleName)
                .setDescription(description)
                .build();
    }
}
