package za.ac.cput.carpartmarket.Service;

import za.ac.cput.carpartmarket.Domain.Delivery;

import java.util.List;

public interface IDeliveryService extends IService<Delivery, String>{
    List<Delivery> getall();
}
