package za.ac.cput.carpartmarket.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.carpartmarket.Domain.Role;
import za.ac.cput.carpartmarket.Domain.Vehicle;
import za.ac.cput.carpartmarket.Service.RoleService;

@RestController
@RequestMapping("/roles")

public class RoleController {

    private RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

 @PostMapping("/create")
    public Role createRole(@RequestBody Role role) {
        return roleService.create(role);
 }
    @GetMapping("/{id}")
    public Role read(@PathVariable("id") String roleId){
        return roleService.read(roleId);
    }

    @PutMapping
    public Role update(@RequestBody Role role){
        return roleService.update(role);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") String roleId){
        roleService.delete(roleId);
    }

}
