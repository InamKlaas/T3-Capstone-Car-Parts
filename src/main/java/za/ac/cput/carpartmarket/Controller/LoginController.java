package za.ac.cput.carpartmarket.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.carpartmarket.Domain.Login;
import za.ac.cput.carpartmarket.Service.LoginService;

@RestController
@RequestMapping("/login")
public class LoginController {

    private final LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/create")
    public Login create(@RequestBody Login login) {
        return loginService.create(login);
    }

    @GetMapping("/read/{loginId}")
    public Login read(@PathVariable("loginId") Long loginId) {
        return loginService.read(loginId);
    }

    @PutMapping("/update")
    public Login update(@RequestBody Login login) {
        return loginService.update(login);
    }

    @DeleteMapping("/delete/{loginId}")
    public void delete(@PathVariable("loginId") Long loginId) {
        loginService.delete(loginId);
    }
}