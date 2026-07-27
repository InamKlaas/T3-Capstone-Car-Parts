package za.ac.cput.carpartmarket.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.carpartmarket.Domain.Delivery;
import za.ac.cput.carpartmarket.Service.DeliveryService;

import java.util.List;

public class DeliveryController {

    private DeliveryService service;

    public DeliveryController(DeliveryService service){
        this.service = service;
    }

    @PostMapping("/create")
    public Delivery create(@RequestBody Delivery delivery){return service.create(delivery);}

    @GetMapping("/read/{deliveryId}")
    public Delivery read(@PathVariable Long deliveryId){return service.read(deliveryId);}

    @PutMapping("/update")
    public Delivery update(@RequestBody Delivery delivery){return service.update(delivery);}

    @DeleteMapping("/delete/{deliveryId}")
    public boolean delete(@PathVariable Long deliveryId){return service.delete(deliveryId);}

    @GetMapping("/getall")
    public List<Delivery> getall(){return service.getall();}
}