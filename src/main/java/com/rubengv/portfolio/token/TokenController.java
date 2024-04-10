package com.rubengv.portfolio.token;

import com.rubengv.portfolio.CustomResponse;
import com.rubengv.portfolio.exceptions.IncorrectCredentialsException;
import com.rubengv.portfolio.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/auth")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true", exposedHeaders = "Authorization")
public class TokenController {
    private final TokenService tokenService;

    @Autowired
    public TokenController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @PostMapping
    public ResponseEntity<CustomResponse<Token>> registerNewToken(@RequestBody CredentialsDTO credentialsDTO) {
        try {
            Token token = tokenService.addNewToken(credentialsDTO.getEmail(), credentialsDTO.getPassword());
            return ResponseEntity.ok(new CustomResponse<>(token, null));
        } catch (UserNotFoundException | IncorrectCredentialsException e) {
            return ResponseEntity.status(e.getStatus()).body(new CustomResponse<>(null, e.getMessage()));
        }
    }

    public static class CredentialsDTO {
        private String email;
        private String password;

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}
