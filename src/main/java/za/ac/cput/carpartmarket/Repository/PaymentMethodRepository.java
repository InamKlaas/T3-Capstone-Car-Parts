package za.ac.cput.carpartmarket.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.carpartmarket.Domain.Address;
import za.ac.cput.carpartmarket.Domain.PaymentMethod;

import java.util.List;

@Repository
public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, String> {
    PaymentMethod findByCvv(int Cvv);

    List<PaymentMethod> findByProvider(String Provider);
}
