package com.example.securitymvctemplate.common.controllers;

import com.example.securitymvctemplate.common.model.SignupRequest;
import com.example.securitymvctemplate.common.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @GetMapping(value = {"/register", "/"})
    public String getRegisterPage(Model model) {
        model.addAttribute(new SignupRequest());
        return "register";
    }

    @PostMapping(value = "/register")
    public String submitRegisterForm(SignupRequest signupRequest) {
        userService.saveUser(signupRequest);
        return "redirect:/login";
    }

    @GetMapping(value = "/login")
    public String login() {
        return "login";
    }
}
