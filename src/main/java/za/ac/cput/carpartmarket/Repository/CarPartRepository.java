package za.ac.cput.carpartmarket.Repository;

import za.ac.cput.carpartmarket.Domain.CarPart;

import java.util.ArrayList;
import java.util.List;

public class CarPartRepository implements ICarPartRepository {

    private static CarPartRepository repository = null;

    private final List<CarPart> carPartList;

    private CarPartRepository() {
        carPartList = new ArrayList<>();
    }

    public static CarPartRepository getRepository() {

        if (repository == null) {
            repository = new CarPartRepository();
        }

        return repository;
    }

    @Override
    public CarPart create(CarPart carPart) {

        carPartList.add(carPart);

        return carPart;
    }

    @Override
    public CarPart read(Long carPartId) {

        for (CarPart carPart : carPartList) {

            if (carPart.getCarPartId().equals(carPartId)) {
                return carPart;
            }

        }

        return null;
    }

    @Override
    public CarPart update(CarPart carPart) {

        CarPart oldCarPart = read(carPart.getCarPartId());

        if (oldCarPart != null) {

            carPartList.remove(oldCarPart);
            carPartList.add(carPart);

            return carPart;
        }

        return null;
    }

    @Override
    public boolean delete(Long carPartId) {

        CarPart carPart = read(carPartId);

        if (carPart != null) {

            carPartList.remove(carPart);
            return true;
        }

        return false;
    }

    @Override
    public List<CarPart> getAll() {

        return carPartList;
    }
}
