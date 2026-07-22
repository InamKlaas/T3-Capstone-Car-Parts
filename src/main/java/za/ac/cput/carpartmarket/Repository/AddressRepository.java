package za.ac.cput.carpartmarket.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.carpartmarket.Domain.Address;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository <Address, String> {

    Address findByStreetNumber(String StreetNumber);

    List<Address> findByProvince(String Province);
}
