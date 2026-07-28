package za.ac.cput.carpartmarket.Service;

import za.ac.cput.carpartmarket.Domain.User;

public interface IUserService {

    User create(User user);

    User read(Long id);

    User update(User user);

    boolean delete(Long id);
}