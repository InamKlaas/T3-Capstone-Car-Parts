package za.ac.cput.carpartmarket.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.carpartmarket.Domain.Delivery;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery, Long> {

}
