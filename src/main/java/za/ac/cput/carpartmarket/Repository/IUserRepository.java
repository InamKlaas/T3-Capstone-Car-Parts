package za.ac.cput.carpartmarket.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.ac.cput.carpartmarket.Domain.User;

import java.util.List;
import java.util.Optional;

public interface IUserRepository extends JpaRepository<User,String> {
    Optional<User> findByEmail(String email);

}