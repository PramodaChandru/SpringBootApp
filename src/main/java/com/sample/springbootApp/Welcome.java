package com.sample.springbootApp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
public class Welcome {

    @GetMapping("/greet")
    public String greet() {
        return "Welcome to SpringBoot demo app";
    }

}
