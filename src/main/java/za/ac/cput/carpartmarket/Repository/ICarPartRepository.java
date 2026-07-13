package za.ac.cput.carpartmarket.Repository;

import java.util.List;

public interface ICarPartRepository {

    CarPart create(CarPart carPart);

    CarPart read(Long carPartId);

    CarPart update(CarPart carPart);

    boolean delete(Long carPartId);

    List<CarPart> getAll();
}
