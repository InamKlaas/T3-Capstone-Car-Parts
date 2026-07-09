package za.ac.cput.carpartmarket.Factory;

import za.ac.cput.carpartmarket.Domain.Admin;
import za.ac.cput.carpartmarket.Util.Helper;


public class AdminFactory {

    public static Admin createAdmin(String user, String role, String permissions){

        if(Helper.isNullOrEmpty(user) ||
                Helper.isNullOrEmpty(role) ||
                Helper.isNullOrEmpty(permissions)){
            return null;
        }

        String adminId = Helper.generateId();

        return new Admin.Builder()
                .setAdminId(adminId)
                .setUser(user)
                .setRole(role)
                .setPermissions(permissions)
                .build();
    }
}