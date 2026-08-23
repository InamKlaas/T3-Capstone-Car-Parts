package za.ac.cput.carpartmarket.Factory;

import za.ac.cput.carpartmarket.Domain.Login;
import za.ac.cput.carpartmarket.Domain.User;
import za.ac.cput.carpartmarket.Util.Helper;

import java.time.LocalDateTime;

public class LoginFactory {

    public static Login createLogin(
            Long loginId,
            User user,
            String email,
            String password,
            LocalDateTime loginDate,
            String status) {

        if (loginId == null) {
            return null;
        }

        if (user == null) {
            return null;
        }

        if (Helper.isNullOrEmpty(email)) {
            return null;
        }

        if (Helper.isNullOrEmpty(password)) {
            return null;
        }

        if (loginDate == null) {
            return null;
        }

        if (Helper.isNullOrEmpty(status)) {
            return null;
        }

        return new Login.Builder()
                .setLoginId(loginId)
                .setUser(user)
                .setEmail(email)
                .setPassword(password)
                .setLoginDate(loginDate)
                .setStatus(status)
                .build();
    }
}
