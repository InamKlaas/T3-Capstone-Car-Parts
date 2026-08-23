package za.ac.cput.carpartmarket.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.ac.cput.carpartmarket.DTO.*;
import za.ac.cput.carpartmarket.Domain.*;
import za.ac.cput.carpartmarket.Repository.IUserRepository;
import java.util.UUID;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")

public class AuthController {

    @Autowired
    private IUserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO request){
        Optional<User> userOpt = userRepository.findByEmail(request.getEmail());
        if(userOpt.isEmpty()|| !userOpt.get().getPassword().equals(request.getPassword())){
            return ResponseEntity.status(401).body("Wrong email or password");
        }
        User user = userOpt.get();
        String role = getRole(user);

        UserResponseDTO response = new UserResponseDTO(
                user.getUserid(),
                user.getName().getFirstName(),
                user.getName().getLastName(),
                user.getEmail(),
                role
        );
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register/buyer")
    public ResponseEntity<?> registerBuyer(@RequestBody RegisterBuyerDTO request){
        if (userRepository.findByEmail(request.getEmail()).isPresent()){
            return ResponseEntity.status(409).body("Email already in use");
        }

        Name buyerName = new Name.Builder()
                .setFirstName(request.getFirstName())
                .setLastName(request.getLastName())
                .build();

        Buyer buyer = new Buyer.Builder()
                .setUserid(java.util.UUID.randomUUID().toString())
                .setBuyerName(buyerName)
                .setEmail(request.getEmail())
                .setPassword(request.getPassword())
                .setBuyingPart(request.getBuyingPart())
                .build();

        Buyer saved=  userRepository.save(buyer);

        UserResponseDTO response = new UserResponseDTO(
                saved.getUserid(), saved.getBuyerName().getFirstName(),saved.getBuyerName().getLastName(),
                saved.getEmail(),"buyer"
        );
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register/seller")
    public ResponseEntity<?> registerSeller(@RequestBody RegisterSellerDTO request){
        if (userRepository.findByEmail(request.getEmail()).isPresent()){
            return ResponseEntity.status(409).body("Email already in use");
        }

        Name sellerName = new Name.Builder()
                .setFirstName(request.getFirstName())
                .setLastName(request.getLastName())
                .build();

        Seller seller = new Seller.Builder()
                .setUserid(java.util.UUID.randomUUID().toString())
                .setSellerName(sellerName)
                .setEmail(request.getEmail())
                .setPassword(request.getPassword())
                .setSellingPart(request.getSellingPart())
                .build();

        Seller saved =  userRepository.save(seller);

        UserResponseDTO response = new UserResponseDTO(
                saved.getUserid(), saved.getSellerName().getFirstName(),saved.getSellerName().getLastName(),
                saved.getEmail(),"seller"
        );
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register/admin")
    public ResponseEntity<?> registerAdmin(@RequestBody RegisterAdminDTO request){
        if (userRepository.findByEmail(request.getEmail()).isPresent()){
            return ResponseEntity.status(409).body("Email already in use");
        }

        Name adminName = new Name.Builder()
                .setFirstName(request.getFirstName())
                .setLastName(request.getLastName())
                .build();

        Admin admin = new Admin.Builder()
                .setUserid(java.util.UUID.randomUUID().toString())
                .setName(adminName)
                .setEmail(request.getEmail())
                .setPassword(request.getPassword())
                .setPhoneNumber(request.getPhoneNumber())
                .setRole(request.getRole())
                .setPermissions(request.getPermissions())
                .build();

        Admin saved =  userRepository.save(admin);

        UserResponseDTO response = new UserResponseDTO(
                saved.getUserid(), saved.getName().getFirstName(),saved.getName().getLastName(),
                saved.getEmail(),"admin"
        );
        return ResponseEntity.ok(response);
    }

    private String getRole(User user){
        if(user instanceof Admin){
            return "admin";
        }
        if(user instanceof Seller){
            return "seller";
        }
        if(user instanceof Buyer){
            return "buyer";
        }
        return "unknown";
    }
}
