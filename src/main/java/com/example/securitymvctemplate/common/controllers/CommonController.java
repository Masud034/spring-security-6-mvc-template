package com.example.securitymvctemplate.common.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CommonController {
    @GetMapping(value = "/dashboard")
    public String getDashboard() {
        return "dashboard";
    }
}
