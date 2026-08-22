package za.ac.cput.carpartmarket.Service;

import za.ac.cput.carpartmarket.Domain.CarPart;


import java.util.List;

public interface ICarPartService extends IService<CarPart, String>{
    List<CarPart> getall();
}

