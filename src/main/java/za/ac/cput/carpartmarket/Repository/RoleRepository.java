package za.ac.cput.carpartmarket.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.ac.cput.carpartmarket.Domain.Role;

public interface RoleRepository extends JpaRepository<Role,String> {
}
