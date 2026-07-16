package za.ac.cput.carpartmarket.Controller;

import org.springframework.web.bind.annotation.*;
import za.ac.cput.carpartmarket.Domain.Address;
import za.ac.cput.carpartmarket.Domain.PaymentMethod;
import za.ac.cput.carpartmarket.Service.AddressService;
import za.ac.cput.carpartmarket.Service.PaymentMethodService;

import java.util.List;

public class PaymentMethodController {

    private PaymentMethodService service;

    public PaymentMethodController(PaymentMethodService service){
        this.service = service;
    }

    @PostMapping("/create")
    public PaymentMethod create(@RequestBody PaymentMethod payment){return service.create(payment);}

    @GetMapping("/read/{employeeId}")
    public PaymentMethod read(@PathVariable String methodId){return service.read(methodId);}

    @PutMapping("/update")
    public PaymentMethod update(@RequestBody PaymentMethod payment){return service.update(payment);}

    @DeleteMapping("/delete/{employeeId}")
    public boolean delete(@PathVariable String methodId){return service.delete(methodId);}

    @GetMapping("/getall")
    public List<PaymentMethod> getall(){return service.getall();}
}
