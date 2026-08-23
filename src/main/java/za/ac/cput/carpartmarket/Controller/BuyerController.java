package za.ac.cput.carpartmarket.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.carpartmarket.Domain.Buyer;
import za.ac.cput.carpartmarket.Service.BuyerService;

@RestController
@RequestMapping("/buyer")
public class BuyerController {

    private final BuyerService buyerService;

    @Autowired
    public BuyerController(BuyerService buyerService) {
        this.buyerService = buyerService;
    }

    @PostMapping("/create")
    public Buyer create(@RequestBody Buyer buyer) {
        return buyerService.create(buyer);
    }

    @GetMapping("/read/{userid}")
    public Buyer read(@PathVariable("userid") Long userid) {
        return buyerService.read(userid);
    }

    @PutMapping("/update")
    public Buyer update(@RequestBody Buyer buyer) {
        return buyerService.update(buyer);
    }

    @DeleteMapping("/delete/{userid}")
    public void delete(@PathVariable("userid") String userid) {
        buyerService.delete(userid);
    }
}