package za.ac.cput.carpartmarket.Controller;

import org.springframework.web.bind.annotation.*;
import za.ac.cput.carpartmarket.Domain.Register;
import za.ac.cput.carpartmarket.Service.RegisterService;

import java.util.List;

@RestController
@RequestMapping("/registers")
public class RegisterController {

    private RegisterService service;

    public RegisterController(RegisterService service){
        this.service = service;
    }

    @PostMapping("/create")
    public Register create(@RequestBody Register register){return service.create(register);}

    @GetMapping("/read/{registrationId}")
    public Register read(@PathVariable String registrationId){return service.read(registrationId);}

    @PutMapping("/update")
    public Register update(@RequestBody Register register){return service.update(register);}

    @DeleteMapping("/delete/{registrationId}")
    public boolean delete(@PathVariable String registrationId){return service.delete(registrationId);}

    @GetMapping("/getall")
    public List<Register> getall(){return service.getall();}
}
