package za.ac.cput.carpartmarket.Repository;

import java.util.List;

public interface IDeliveryRepository {

    Delivery create(Delivery delivery);

    Delivery read(String deliveryId);

    Delivery update(Delivery delivery);

    boolean delete(String deliveryId);

    List<Delivery> getAll();
}
