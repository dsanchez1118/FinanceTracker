package com.financetracker.app;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.context.annotation.Bean;

@RestController
public class MainController {

    @GetMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }
}