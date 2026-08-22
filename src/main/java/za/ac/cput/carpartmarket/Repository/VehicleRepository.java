package za.ac.cput.carpartmarket.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.carpartmarket.Domain.Vehicle;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle,String> {
}
