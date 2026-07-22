package za.ac.cput.carpartmarket.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.carpartmarket.Domain.Address;
import za.ac.cput.carpartmarket.Service.AddressService;

import java.util.List;

public class AddressController {

    private AddressService service;

    public AddressController(AddressService service){
        this.service = service;
    }

    @PostMapping("/create")
    public Address create(@RequestBody Address address){return service.create(address);}

    @GetMapping("/read/{employeeId}")
    public Address read(@PathVariable String streetNumber){return service.read(streetNumber);}

    @PutMapping("/update")
    public Address update(@RequestBody Address streetNumber){return service.update(streetNumber);}

    @DeleteMapping("/delete/{employeeId}")
    public boolean delete(@PathVariable String streetNumber){return service.delete(streetNumber);}

    @GetMapping("/getall")
    public List<Address> getall(){return service.getall();}
}
