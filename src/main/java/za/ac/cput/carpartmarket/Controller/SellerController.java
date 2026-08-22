package za.ac.cput.carpartmarket.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.carpartmarket.Domain.Seller;
import za.ac.cput.carpartmarket.Service.SellerService;

@RestController
@RequestMapping("/seller")
public class SellerController {

    private final SellerService sellerService;

    @Autowired
    public SellerController(SellerService sellerService) {
        this.sellerService = sellerService;
    }

    @PostMapping("/create")
    public Seller create(@RequestBody Seller seller) {
        return sellerService.create(seller);
    }

    @GetMapping("/read/{sellerId}")
    public Seller read(@PathVariable("sellerId") String sellerId) {
        return sellerService.read(sellerId);
    }

    @PutMapping("/update")
    public Seller update(@RequestBody Seller seller) {
        return sellerService.update(seller);
    }

    @DeleteMapping("/delete/{sellerId}")
    public void delete(@PathVariable("sellerId") String sellerId) {
        sellerService.delete(sellerId);
    }
}
