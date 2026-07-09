package za.ac.cput.carpartmarket.Repository;

import za.ac.cput.carpartmarket.Domain.Admin;

import java.util.ArrayList;
import java.util.List;

public class AdminRepository implements IAdminRepository {

    private static AdminRepository repository = null;

    private final List<Admin> adminList;

    private AdminRepository() {
        adminList = new ArrayList<>();
    }

    public static AdminRepository getRepository() {

        if (repository == null) {
            repository = new AdminRepository();
        }

        return repository;
    }

    @Override
    public Admin create(Admin admin) {
        adminList.add(admin);
        return admin;
    }

    @Override
    public Admin read(String adminId) {

        for (Admin admin : adminList) {

            if (admin.getAdminId().equals(adminId)) {
                return admin;
            }

        }

        return null;
    }

    @Override
    public Admin update(Admin admin) {

        Admin oldAdmin = read(admin.getAdminId());

        if (oldAdmin != null) {

            adminList.remove(oldAdmin);
            adminList.add(admin);

            return admin;
        }

        return null;
    }

    @Override
    public boolean delete(String adminId) {

        Admin admin = read(adminId);

        if (admin != null) {

            adminList.remove(admin);
            return true;
        }

        return false;
    }

    @Override
    public List<Admin> getAll() {
        return adminList;
    }
}