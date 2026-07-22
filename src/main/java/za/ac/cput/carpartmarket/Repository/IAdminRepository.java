package za.ac.cput.carpartmarket.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.ac.cput.carpartmarket.Domain.Admin;

import java.util.List;

public interface IAdminRepository extends JpaRepository<Admin,String> {


}