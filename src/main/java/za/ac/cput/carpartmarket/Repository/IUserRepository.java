package za.ac.cput.carpartmarket.Repository;

import za.ac.cput.carpartmarket.Domain.User;

import java.util.List;

public interface IUserRepository {

    User create(User user);

    User read(Long userId);

    User update(User user);

    boolean delete(Long userId);

    List<User> getAll();
}