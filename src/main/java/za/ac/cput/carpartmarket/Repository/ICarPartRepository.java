package za.ac.cput.carpartmarket.Repository;

import za.ac.cput.carpartmarket.Domain.CarPart;

import java.util.List;

public interface ICarPartRepository {

    CarPart create(CarPart carPart);

    CarPart read(Long carPartId);

    CarPart update(CarPart carPart);

    boolean delete(Long carPartId);

    List<CarPart> getAll();
}
