package za.ac.cput.carpartmarket.Service;

import za.ac.cput.carpartmarket.Domain.User;

public interface IUserService extends IService<User, String> {

    User create(User user);

    User read(String id);

    User update(User user);

    boolean delete(String id);
}