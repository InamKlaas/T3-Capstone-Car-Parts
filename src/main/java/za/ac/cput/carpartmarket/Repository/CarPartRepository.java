package za.ac.cput.carpartmarket.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.carpartmarket.Domain.CarPart;

import java.util.List;

@Repository
public interface CarPartRepository extends JpaRepository<CarPart, String> {

//    CarPart findByPartName(String partName);
//    List<CarPart> findByStatus(String status);
//    List<CarPart> findBySellerEmail(String sellerEmail);
}