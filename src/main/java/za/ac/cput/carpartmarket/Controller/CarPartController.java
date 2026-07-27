package za.ac.cput.carpartmarket.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.carpartmarket.Domain.CarPart;
import za.ac.cput.carpartmarket.Service.CarPartService;

import java.util.List;

public class CarPartController {

    private CarPartService service;

    public CarPartController(CarPartService service){
        this.service = service;
    }

    @PostMapping("/create")
    public CarPart create(@RequestBody CarPart carPart){return service.create(carPart);}

    @GetMapping("/read/{carPartId}")
    public CarPart read(@PathVariable Long carPartId){return service.read(carPartId);}

    @PutMapping("/update")
    public CarPart update(@RequestBody CarPart carPart){return service.update(carPart);}

    @DeleteMapping("/delete/{carPartId}")
    public boolean delete(@PathVariable Long carPartId){return service.delete(carPartId);}

    @GetMapping("/getall")
    public List<CarPart> getall(){return service.getall();}
}