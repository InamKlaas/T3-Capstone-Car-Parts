package za.ac.cput.carpartmarket.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.ac.cput.carpartmarket.Domain.Login;

public interface ILoginRepository  extends JpaRepository<Login, String> {

}
