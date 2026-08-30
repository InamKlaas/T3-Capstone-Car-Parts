package za.ac.cput.carpartmarket.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.carpartmarket.Domain.Register;

import java.util.List;

@Repository
public interface RegisterRepository extends JpaRepository<Register, String> {

    List<Register> findByRegistrationId(String registrationId);

}
