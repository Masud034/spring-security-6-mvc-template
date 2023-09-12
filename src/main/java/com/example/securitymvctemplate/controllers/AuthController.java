package com.example.securitymvctemplate.controllers;

import com.example.securitymvctemplate.model.SignupRequest;
import com.example.securitymvctemplate.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;
    @GetMapping(value = "/login")
    public String login() {
        return "login";
    }

    @GetMapping(value = "/dashboard")
    public String getDashboard() {
        return "dashboard";
    }

    @GetMapping(value = {"/register", "/"})
    public String getRegisterPage(Model model) {
        model.addAttribute(new SignupRequest());
        return "signup";
    }

    @PostMapping(value = "/register")
    public String submitRegisterForm(SignupRequest signupRequest) {
       userService.saveUser(signupRequest);
       return "redirect:/login";
    }
}
