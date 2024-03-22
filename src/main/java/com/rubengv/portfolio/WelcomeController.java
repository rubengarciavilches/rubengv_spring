package com.rubengv.portfolio;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://noteit.localhost:3000")
public class WelcomeController {
    @GetMapping("api/v1/")
    public ResponseEntity<CustomResponse<String>> welcome() {
        return ResponseEntity.ok(new CustomResponse<>("Welcome to the API! Please refer to the documentation for available endpoints.", null));
    }
}