package za.ac.cput.carpartmarket.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.carpartmarket.Domain.CarPart;
import za.ac.cput.carpartmarket.Service.CarPartService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/carparts")
public class CarPartController {

    private final CarPartService service;

    @Autowired
    public CarPartController(CarPartService service) {
        this.service = service;
    }

    // ---------- CREATE (always PENDING) ----------
    @PostMapping("/create")
    public CarPart create(@RequestBody CarPart incoming) {
        String id = incoming.getCarPartId();
        if (id == null || id.isBlank()) {
            id = UUID.randomUUID().toString();
        }

        CarPart part = new CarPart.Builder()
                .setCarPartId(id)
                .setPartName(incoming.getPartName())
                .setDescription(incoming.getDescription())
                .setPrice(incoming.getPrice())
                .setStockQuantity(incoming.getStockQuantity())
                .setModel(incoming.getModel())
                .setCategoryId(incoming.getCategoryId())
                .setSellerId(incoming.getSellerId())
                .setImage(incoming.getImage())
                .setStatus("PENDING")
                .build();

        return service.create(part);
    }

    // ---------- READ ----------
    @GetMapping("/read/{carPartId}")
    public CarPart read(@PathVariable String carPartId) {
        return service.read(carPartId);
    }

    // ---------- UPDATE (CRUD) ----------
    @PutMapping("/update")
    public CarPart update(@RequestBody CarPart carPart) {
        return service.update(carPart);
    }

    // ---------- DELETE ----------
    @DeleteMapping("/delete/{carPartId}")
    public boolean delete(@PathVariable String carPartId) {
        return service.delete(carPartId);
    }

    @GetMapping("/getall")
    public List<CarPart> getall() {
        return service.getall();
    }

    // ---------- APPROVAL WORKFLOW ----------

    @GetMapping("/pending")
    public List<CarPart> pending() {
        return service.getall().stream()
                .filter(p -> "PENDING".equalsIgnoreCase(p.getStatus()))
                .collect(Collectors.toList());
    }

    @GetMapping("/approved")
    public List<CarPart> approved() {
        return service.getall().stream()
                .filter(p -> "APPROVED".equalsIgnoreCase(p.getStatus()))
                .collect(Collectors.toList());
    }

    @GetMapping("/seller/{sellerId}")
    public List<CarPart> bySeller(@PathVariable String sellerId) {
        return service.getall().stream()
                .filter(p -> sellerId.equals(p.getSellerId()))
                .collect(Collectors.toList());
    }

    @PutMapping("/{carPartId}/approve")
    public CarPart approve(@PathVariable String carPartId) {
        CarPart part = service.read(carPartId);
        if (part == null) throw new RuntimeException("Car part not found: " + carPartId);
        part.setStatus("APPROVED");                 // uses the setter you just added
        return service.update(part);
    }

    @PutMapping("/{carPartId}/decline")
    public CarPart decline(@PathVariable String carPartId) {
        CarPart part = service.read(carPartId);
        if (part == null) throw new RuntimeException("Car part not found: " + carPartId);
        part.setStatus("DECLINED");
        return service.update(part);
    }
}