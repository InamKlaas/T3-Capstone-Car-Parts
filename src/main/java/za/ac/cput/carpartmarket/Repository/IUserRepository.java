package za.ac.cput.carpartmarket.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.ac.cput.carpartmarket.Domain.User;

import java.util.List;

public interface IUserRepository extends JpaRepository<User,Long> {

}