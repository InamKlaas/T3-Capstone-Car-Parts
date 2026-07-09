package za.ac.cput.carpartmarket.Repository;

import za.ac.cput.carpartmarket.Domain.User;

import java.util.ArrayList;
import java.util.List;

public class UserRepository implements IUserRepository {

    private static UserRepository repository = null;

    private final List<User> userList;

    private UserRepository() {
        userList = new ArrayList<>();
    }

    public static UserRepository getRepository() {

        if (repository == null) {
            repository = new UserRepository();
        }

        return repository;
    }

    @Override
    public User create(User user) {

        userList.add(user);

        return user;
    }

    @Override
    public User read(Long userId) {

        for (User user : userList) {

            if (user.getUserid().equals(userId)) {
                return user;
            }

        }

        return null;
    }

    @Override
    public User update(User user) {

        User oldUser = read(user.getUserid());

        if (oldUser != null) {

            userList.remove(oldUser);
            userList.add(user);

            return user;
        }

        return null;
    }

    @Override
    public boolean delete(Long userId) {

        User user = read(userId);

        if (user != null) {

            userList.remove(user);
            return true;
        }

        return false;
    }

    @Override
    public List<User> getAll() {

        return userList;
    }
}