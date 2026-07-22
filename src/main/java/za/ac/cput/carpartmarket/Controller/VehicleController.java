package za.ac.cput.carpartmarket.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.carpartmarket.Domain.Vehicle;
import za.ac.cput.carpartmarket.Service.VehicleService;

@RestController
@RequestMapping("/vehicles")

public class VehicleController {
    private VehicleService vehicleService;

    @Autowired
    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @PostMapping("/create")
    public Vehicle create(@RequestBody Vehicle vehicle){
        return vehicleService.create(vehicle);
    }

    @GetMapping("/read/{vehicleId}")
    public Vehicle read(@PathVariable("vehicleId") Long vehicleId){
        return vehicleService.read(vehicleId);
    }

    @PutMapping("/update")
    public Vehicle update(@RequestBody Vehicle vehicle){
        return vehicleService.update(vehicle);
    }

    @DeleteMapping("/delete/{vehicleId}")
    public void deleteById(@PathVariable("vehicleId") Long vehicleId){
        vehicleService.delete(vehicleId);
    }
}
