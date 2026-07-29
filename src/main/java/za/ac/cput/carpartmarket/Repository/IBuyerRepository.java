package za.ac.cput.carpartmarket.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.ac.cput.carpartmarket.Domain.Buyer;

public interface IBuyerRepository extends JpaRepository<Buyer, Long> {
}
