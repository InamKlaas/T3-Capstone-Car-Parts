package za.ac.cput.carpartmarket.Repository;

import za.ac.cput.carpartmarket.Domain.Admin;

import java.util.List;

public interface IAdminRepository {

    Admin create(Admin admin);

    Admin read(String adminId);

    Admin update(Admin admin);

    boolean delete(String adminId);

    List<Admin> getAll();
}