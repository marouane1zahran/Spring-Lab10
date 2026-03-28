package com.example.connexion.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NavigationController {

    @GetMapping("/signin")
    public String showSignin() {
        return "signin-page";
    }

    @GetMapping("/welcome")
    public String showWelcome() {
        return "welcome-page";
    }

    @GetMapping("/manager/panel")
    public String showManagerPanel() {
        return "manager-space";
    }

    @GetMapping("/employee/panel")
    public String showEmployeePanel() {
        return "employee-space";
    }
}