package za.ac.cput.carpartmarket.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.carpartmarket.Domain.CarPart;
import za.ac.cput.carpartmarket.Repository.CarPartRepository;

import java.util.List;

@Service
public class CarPartService implements ICarPartService {

    private CarPartRepository repository;

    @Autowired
    public CarPartService(CarPartRepository repository){this.repository = repository;}

    @Override
    public CarPart create(CarPart carPart){return this.repository.save(carPart);}

    @Override
    public CarPart read(Long id){return this.repository.findById(id).orElse(null);}

    @Override
    public CarPart update(CarPart carPart){return this.repository.save(carPart);}

    @Override
    public boolean delete(Long id){
        this.repository.deleteById(id);
        return true;
    }

    @Override
    public List<CarPart> getall(){
        return this.repository.findAll();
    }
}