package pl.gren.oze_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import pl.gren.oze_app.model.db.entity.Salesman;
import pl.gren.oze_app.service.SalesmanService;

@Controller
public class RegistrationController {

    @Autowired
    private SalesmanService salesmanService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register/user")
    public Salesman createUser(@RequestBody Salesman user) {
        user.setPasswordHash(passwordEncoder.encode(user.getPasswordHash()));
        return salesmanService.save(user);
    }

}