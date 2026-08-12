package za.ac.cput.carpartmarket.Factory;

import za.ac.cput.carpartmarket.Domain.Admin;
import za.ac.cput.carpartmarket.Util.Helper;


public class AdminFactory {

    public static Admin createAdmin(Long userid,String role, String permissions) {
        if (userid ==null ||
        Helper.isNullOrEmpty(role)||
        Helper.isNullOrEmpty(permissions)) {
            return null;
        }
        return new Admin.Builder()
                .setUserid(userid)
                .setRole(role)
                .setPermissions(permissions)
                .build();

    }

}