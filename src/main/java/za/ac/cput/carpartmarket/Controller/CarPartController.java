package za.ac.cput.carpartmarket.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.carpartmarket.Domain.CarPart;
import za.ac.cput.carpartmarket.Domain.User;
import za.ac.cput.carpartmarket.Repository.IUserRepository;
import za.ac.cput.carpartmarket.Service.CarPartService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/carparts")
public class CarPartController {

    private final CarPartService service;
    private final IUserRepository userRepository;

    @Autowired
    public CarPartController(CarPartService service, IUserRepository userRepository) {
        this.service = service;
        this.userRepository = userRepository;
    }

    // ---------- Who is calling (from the JWT, never from the request body) ----------

    private String currentUserId(Authentication auth) {
        return userRepository.findByEmail(auth.getName())
                .map(User::getUserid)
                .orElse(null);
    }

    private boolean isAdmin(Authentication auth) {
        return auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
    }

    private boolean ownsPart(Authentication auth, CarPart part) {
        String userId = currentUserId(auth);
        return userId != null && userId.equals(part.getSellerId());
    }

    // ---------- CREATE (always PENDING, always the logged-in seller) ----------
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody CarPart incoming, Authentication auth) {
        String sellerId = currentUserId(auth);
        if (sellerId == null) {
            return ResponseEntity.status(401).body("Unknown user");
        }

        CarPart part = new CarPart.Builder()
                .setCarPartId(UUID.randomUUID().toString())
                .setPartName(incoming.getPartName())
                .setDescription(incoming.getDescription())
                .setPrice(incoming.getPrice())
                .setStockQuantity(incoming.getStockQuantity())
                .setModel(incoming.getModel())
                .setCategoryId(incoming.getCategoryId())
                .setSellerId(sellerId)
                .setPartCondition(incoming.getPartCondition())
                .setImage(incoming.getImage())
                .setStatus("PENDING")
                .build();

        return ResponseEntity.ok(service.create(part));
    }

    // ---------- READ ----------
    @GetMapping("/read/{carPartId}")
    public CarPart read(@PathVariable String carPartId) {
        return service.read(carPartId);
    }

    // ---------- UPDATE (owner or admin; sellers can't approve themselves) ----------
    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody CarPart incoming, Authentication auth) {
        CarPart existing = service.read(incoming.getCarPartId());
        if (existing == null) {
            return ResponseEntity.status(404).body("Car part not found");
        }

        boolean admin = isAdmin(auth);
        if (!admin && !ownsPart(auth, existing)) {
            return ResponseEntity.status(403).body("You can only edit your own listings");
        }

        // A seller may only mark their part SOLD; every other status change is the admin's job
        String status = existing.getStatus();
        if (admin || "SOLD".equalsIgnoreCase(incoming.getStatus())) {
            status = incoming.getStatus() != null ? incoming.getStatus() : status;
        }

        CarPart updated = new CarPart.Builder()
                .copy(incoming)
                .setSellerId(existing.getSellerId())   // ownership can't be changed
                .setStatus(status)
                .build();

        return ResponseEntity.ok(service.update(updated));
    }

    // ---------- DELETE (owner or admin) ----------
    @DeleteMapping("/delete/{carPartId}")
    public ResponseEntity<?> delete(@PathVariable String carPartId, Authentication auth) {
        CarPart existing = service.read(carPartId);
        if (existing == null) {
            return ResponseEntity.status(404).body("Car part not found");
        }
        if (!isAdmin(auth) && !ownsPart(auth, existing)) {
            return ResponseEntity.status(403).body("You can only delete your own listings");
        }
        return ResponseEntity.ok(service.delete(carPartId));
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

    // Sellers only see their own listings; admins can look up anyone
    @GetMapping("/seller/{sellerId}")
    public ResponseEntity<?> bySeller(@PathVariable String sellerId, Authentication auth) {
        if (!isAdmin(auth) && !sellerId.equals(currentUserId(auth))) {
            return ResponseEntity.status(403).body("You can only view your own listings");
        }
        return ResponseEntity.ok(service.getall().stream()
                .filter(p -> sellerId.equals(p.getSellerId()))
                .collect(Collectors.toList()));
    }

    @PutMapping("/{carPartId}/approve")
    public ResponseEntity<?> approve(@PathVariable String carPartId) {
        CarPart part = service.read(carPartId);
        if (part == null) return ResponseEntity.status(404).body("Car part not found");
        part.setStatus("APPROVED");
        return ResponseEntity.ok(service.update(part));
    }

    @PutMapping("/{carPartId}/decline")
    public ResponseEntity<?> decline(@PathVariable String carPartId) {
        CarPart part = service.read(carPartId);
        if (part == null) return ResponseEntity.status(404).body("Car part not found");
        part.setStatus("DECLINED");
        return ResponseEntity.ok(service.update(part));
    }
}