package pl.gren.oze_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import pl.gren.oze_app.model.Salesman;
import pl.gren.oze_app.oldrepository.SalesmanRepository;

@Controller
public class RegistrationController {

    @Autowired
    private SalesmanRepository salesmanRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register/user")
    public Salesman createUser(@RequestBody Salesman user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return salesmanRepository.save(user);
    }

}