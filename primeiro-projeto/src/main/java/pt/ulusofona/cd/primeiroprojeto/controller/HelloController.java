package pt.ulusofona.cd.primeiroprojeto.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HelloController {

    // Basic endpoint
    @GetMapping("/hello")
    public String hello() {
        return "Hello, Spring Boot with Java 23! 🚀";
    }
}