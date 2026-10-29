package za.ac.cput.carpartmarket.Config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import za.ac.cput.carpartmarket.Domain.Admin;
import za.ac.cput.carpartmarket.Domain.Name;
import za.ac.cput.carpartmarket.Domain.User;
import za.ac.cput.carpartmarket.Repository.IUserRepository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Component
public class AdminSeeder implements CommandLineRunner {

    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${app.admin.email}")
    private String adminEmail;

    @Value("${app.admin.password}")
    private String adminPassword;

    public AdminSeeder(IUserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        Optional<User> existing = userRepository.findByEmail(adminEmail);

        if (existing.isPresent()) {

            User admin = existing.get();
            if (admin.getPassword() == null || !admin.getPassword().startsWith("$2")) {
                admin.setPassword(passwordEncoder.encode(adminPassword));
                userRepository.save(admin);
                System.out.println("Default admin password upgraded to BCrypt: " + adminEmail);
            }
            return;
        }

        Name name = new Name.Builder()
                .setFirstName("System")
                .setLastName("Admin")
                .build();

        Admin admin = new Admin.Builder()
                .setUserid(UUID.randomUUID().toString())
                .setName(name)
                .setEmail(adminEmail)
                .setPassword(passwordEncoder.encode(adminPassword))
                .setPhoneNumber("0000000000")
                .setCreatedAt(LocalDate.now().toString())
                .setRole("SUPER_ADMIN")
                .setPermissions("ALL")
                .build();

        userRepository.save(admin);
        System.out.println("Default admin created: " + adminEmail);
    }
}