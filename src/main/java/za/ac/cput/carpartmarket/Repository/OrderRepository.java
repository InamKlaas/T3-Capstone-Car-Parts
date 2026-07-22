package za.ac.cput.carpartmarket.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.ac.cput.carpartmarket.Domain.Order;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
