package za.ac.cput.carpartmarket.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.ac.cput.carpartmarket.Domain.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle,Long> {
}
