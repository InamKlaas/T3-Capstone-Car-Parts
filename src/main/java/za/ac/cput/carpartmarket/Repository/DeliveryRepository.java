package za.ac.cput.carpartmarket.Repository;

import java.util.ArrayList;
import java.util.List;

public class DeliveryRepository implements IDeliveryRepository {

    private static DeliveryRepository repository = null;

    private final List<Delivery> deliveryList;

    private DeliveryRepository() {
        deliveryList = new ArrayList<>();
    }

    public static DeliveryRepository getRepository() {

        if (repository == null) {
            repository = new DeliveryRepository();
        }

        return repository;
    }

    @Override
    public Delivery create(Delivery delivery) {

        deliveryList.add(delivery);

        return delivery;
    }

    @Override
    public Delivery read(String deliveryId) {

        for (Delivery delivery : deliveryList) {

            if (delivery.getDeliveryId().equals(deliveryId)) {
                return delivery;
            }

        }

        return null;
    }

    @Override
    public Delivery update(Delivery delivery) {

        Delivery oldDelivery = read(delivery.getDeliveryId());

        if (oldDelivery != null) {

            deliveryList.remove(oldDelivery);
            deliveryList.add(delivery);

            return delivery;
        }

        return null;
    }

    @Override
    public boolean delete(String deliveryId) {

        Delivery delivery = read(deliveryId);

        if (delivery != null) {

            deliveryList.remove(delivery);
            return true;
        }

        return false;
    }

    @Override
    public List<Delivery> getAll() {

        return deliveryList;
    }
}

