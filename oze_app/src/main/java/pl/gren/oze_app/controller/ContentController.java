package pl.gren.oze_app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContentController {

    @GetMapping("/home")
    public String handleWelcome() {
        return "home";
    }

    @GetMapping("/admin/home")
    public String handleAdminHome() {
        return "home_admin";
    }

    @GetMapping("/user/home")
    public String handleUserHome() {
        return "home_salesman";
    }

    @GetMapping("/login")
    public String handleLogin() {
        return "custom_login";
    }
}
