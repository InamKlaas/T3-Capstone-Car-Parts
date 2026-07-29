package za.ac.cput.carpartmarket.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.carpartmarket.Domain.CarPart;

@Repository
public interface CarPartRepository extends JpaRepository<CarPart, Long> {

    CarPart findByPartName(String partName);
}