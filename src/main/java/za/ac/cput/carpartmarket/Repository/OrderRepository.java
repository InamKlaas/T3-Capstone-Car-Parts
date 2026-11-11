package za.ac.cput.carpartmarket.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import za.ac.cput.carpartmarket.Domain.Order;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,String> {

    List<Order> findByBuyer_UseridOrderByOrderDateDesc(String buyerId);


    @Query("select distinct o from Order o join o.items i where i.sellerId = :sellerId order by o.orderDate desc")
    List<Order> findBySellerId(@Param("sellerId") String sellerId);

    List<Order> findAllByOrderByOrderDateDesc();
}


