package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/demo")
public class DemoController {
    @GetMapping("")
    public ResponseEntity<Map> demoService(){
        return ResponseEntity.ok(Map.of("status","user is authenticated"));
    }
}
