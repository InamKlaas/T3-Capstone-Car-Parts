package za.ac.cput.carpartmarket.Controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.carpartmarket.DTO.LoginDTO;
import za.ac.cput.carpartmarket.Domain.Login;
import za.ac.cput.carpartmarket.Domain.User;
import za.ac.cput.carpartmarket.Repository.IUserRepository;
import za.ac.cput.carpartmarket.Service.LoginService;

@RestController
@RequestMapping("/login")
public class LoginController {

    private final LoginService loginService;
    private final IUserRepository userRepository;

    @Autowired
    public LoginController(LoginService loginService, IUserRepository userRepository) {
        this.loginService = loginService;
        this.userRepository = userRepository;
    }

    @PostMapping("/create")
    public ResponseEntity<LoginDTO> create(@RequestBody LoginDTO dto) {
        User user = userRepository.findById(dto.getUserid())
                .orElseThrow(() -> new RuntimeException("User not found: " + dto.getUserid()));

        Login login = new Login.Builder()
                .setLoginId(dto.getLoginId())
                .setUser(user)
                .setEmail(dto.getEmail())
                .setPassword(dto.getPassword())
                .setLoginDate(dto.getLoginDate())
                .setStatus(dto.getStatus())
                .build();

        Login saved = loginService.create(login);
        return ResponseEntity.ok(toDTO(saved));
    }

    @GetMapping("/read/{loginId}")
    public ResponseEntity<LoginDTO> read(@PathVariable("loginId") String loginId) {
        Login login = loginService.read(loginId);
        if (login == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(toDTO(login));
    }

    @PutMapping("/update")
    public ResponseEntity<LoginDTO> update(@RequestBody LoginDTO dto) {
        User user = userRepository.findById(dto.getUserid())
                .orElseThrow(() -> new RuntimeException("User not found: " + dto.getUserid()));

        Login login = new Login.Builder()
                .setLoginId(dto.getLoginId())
                .setUser(user)
                .setEmail(dto.getEmail())
                .setPassword(dto.getPassword())
                .setLoginDate(dto.getLoginDate())
                .setStatus(dto.getStatus())
                .build();

        Login updated = loginService.update(login);
        return ResponseEntity.ok(toDTO(updated));
    }

    @DeleteMapping("/delete/{loginId}")
    public void delete(@PathVariable("loginId") String loginId) {
        loginService.delete(loginId);
    }

    private LoginDTO toDTO(Login login) {
        return new LoginDTO(
                login.getLoginId(),
                login.getUser().getUserid(),
                login.getEmail(),
                login.getPassword(),
                login.getLoginDate(),
                login.getStatus()
        );
    }
}