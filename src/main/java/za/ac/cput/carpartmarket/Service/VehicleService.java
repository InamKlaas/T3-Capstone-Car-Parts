package za.ac.cput.carpartmarket.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.carpartmarket.Domain.Vehicle;
import za.ac.cput.carpartmarket.Repository.VehicleRepository;
@Service
public class VehicleService implements IVehicleService{

    @Autowired
    private VehicleRepository repository;

    @Override
    public Vehicle create(Vehicle vehicle) {
        return repository.save(vehicle);
    }

    @Override
    public Vehicle read(Long aLong) {
        return repository.findById(aLong).orElse(null);
    }

    @Override
    public Vehicle update(Vehicle vehicle) {
        return repository.save(vehicle);
    }

    @Override
    public void delete(Long vehicleId) {
        repository.deleteById(vehicleId);

    }
}
