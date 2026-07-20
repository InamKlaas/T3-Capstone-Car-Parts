package za.ac.cput.carpartmarket.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.ac.cput.carpartmarket.Domain.CarPart;

import java.util.List;

public interface ICarPartRepository extends JpaRepository<CarPart,Long> {

}
