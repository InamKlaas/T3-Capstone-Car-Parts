package za.ac.cput.carpartmarket.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.ac.cput.carpartmarket.Domain.Seller;

public interface ISellerRepository extends JpaRepository<Seller, String> {
}