package za.ac.cput.carpartmarket.Service;

import za.ac.cput.carpartmarket.Domain.Admin;

import java.util.List;

public interface IAdminService {
    Admin create(Admin admin);

    Admin read(String id);

    Admin update(Admin admin);

    boolean delete(String id);

    List<Admin> getAll();
}
