package za.ac.cput.carpartmarket.Config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import za.ac.cput.carpartmarket.Domain.Admin;
import za.ac.cput.carpartmarket.Domain.Name;
import za.ac.cput.carpartmarket.Repository.IUserRepository;

import java.time.LocalDate;
import java.util.UUID;

@Component
public class AdminSeeder implements CommandLineRunner {

    private final IUserRepository userRepository;

    @Value("${app.admin.email}")
    private String adminEmail;

    @Value("${app.admin.password}")
    private String adminPassword;

    public AdminSeeder(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) {
        // Don't create a duplicate if the admin already exists
        if (userRepository.findByEmail(adminEmail).isPresent()) {
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
                .setPassword(adminPassword)
                .setPhoneNumber("0000000000")
                .setCreatedAt(LocalDate.now().toString())
                .setRole("SUPER_ADMIN")
                .setPermissions("ALL")
                .build();

        userRepository.save(admin);
        System.out.println("Default admin created: " + adminEmail);
    }
}