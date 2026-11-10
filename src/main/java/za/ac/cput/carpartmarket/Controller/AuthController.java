package za.ac.cput.carpartmarket.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.ac.cput.carpartmarket.DTO.*;
import za.ac.cput.carpartmarket.Domain.*;
import za.ac.cput.carpartmarket.Repository.IUserRepository;
import java.util.UUID;

import za.ac.cput.carpartmarket.Service.JwtService;
import za.ac.cput.carpartmarket.Service.LoginService; //new
import za.ac.cput.carpartmarket.Service.PasswordResetService;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")

public class AuthController {

    @Autowired
    private PasswordResetService passwordResetService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO request){
        Optional<User> userOpt = userRepository.findByEmail(request.getEmail());

        if(userOpt.isEmpty()|| !passwordEncoder.matches(request.getPassword(), userOpt.get().getPassword())){
            return ResponseEntity.status(401).body("Wrong email or password");
        }
        User user = userOpt.get();
        String role = getRole(user);

        loginService.recordLogin(user, request.getEmail(), "SUCCESS");

        String token = jwtService.generateToken(user.getEmail(), role);

        UserResponseDTO response = new UserResponseDTO(
                user.getUserid(),
                user.getName().getFirstName(),
                user.getName().getLastName(),
                user.getEmail(),
                role,
                token
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
                .setUserid(UUID.randomUUID().toString())
                .setBuyerName(buyerName)
                .setEmail(request.getEmail())
                .setPassword(passwordEncoder.encode(request.getPassword()))
                .setBuyingPart(request.getBuyingPart())
                .build();

        Buyer saved=  userRepository.save(buyer);

        UserResponseDTO response = new UserResponseDTO(
                saved.getUserid(), saved.getBuyerName().getFirstName(),saved.getBuyerName().getLastName(),
                saved.getEmail(),"buyer", null
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
                .setPassword(passwordEncoder.encode(request.getPassword()))
                .setSellingPart(request.getSellingPart())
                .build();

        Seller saved =  userRepository.save(seller);

        UserResponseDTO response = new UserResponseDTO(
                saved.getUserid(), saved.getSellerName().getFirstName(),saved.getSellerName().getLastName(),
                saved.getEmail(),"seller", null
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
                .setPassword(passwordEncoder.encode(request.getPassword()))
                .setPhoneNumber(request.getPhoneNumber())
                .setRole(request.getRole())
                .setPermissions(request.getPermissions())
                .build();

        Admin saved =  userRepository.save(admin);

        UserResponseDTO response = new UserResponseDTO(
                saved.getUserid(), saved.getName().getFirstName(),saved.getName().getLastName(),
                saved.getEmail(),"admin", null
        );
        return ResponseEntity.ok(response);
    }

    // Step 1: request a code. Same reply whether or not the account exists.
    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestBody ResetPasswordDTO request) {
        String email = request.getEmail() == null ? "" : request.getEmail().trim().toLowerCase();

        if (userRepository.findByEmail(email).isPresent()) {
            passwordResetService.issueCode(email);
        }
        return ResponseEntity.ok("If an account exists for that email, a reset code has been sent.");
    }

    // Step 2: reset with the code
    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestBody ResetPasswordDTO request) {
        String email = request.getEmail() == null ? "" : request.getEmail().trim().toLowerCase();

        if (request.getNewPassword() == null || request.getNewPassword().length() < 6) {
            return ResponseEntity.badRequest().body("Password must be at least 6 characters.");
        }

        if (!passwordResetService.verifyAndConsume(email, request.getCode())) {
            return ResponseEntity.badRequest().body("Invalid or expired code.");
        }

        Optional<User> userOpt = userRepository.findByEmail(email);
        if (userOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Invalid or expired code.");
        }

        User user = userOpt.get();
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);

        return ResponseEntity.ok("Password reset successful");
    }

    private String getRole(User user) {
        if (user instanceof Admin) return "admin";
        if (user instanceof Seller) return "seller";
        if (user instanceof Buyer) return "buyer";
        return "unknown";
    }
}
