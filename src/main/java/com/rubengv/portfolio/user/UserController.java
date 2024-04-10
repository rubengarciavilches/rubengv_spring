package com.rubengv.portfolio.user;

import com.rubengv.portfolio.CustomResponse;
import com.rubengv.portfolio.exceptions.EmailTakenException;
import com.rubengv.portfolio.exceptions.IncorrectCredentialsException;
import com.rubengv.portfolio.exceptions.InvalidFormatException;
import com.rubengv.portfolio.exceptions.UserNotFoundException;
import com.rubengv.portfolio.token.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(path = "api/v1/user")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true", exposedHeaders = "Authorization")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Get all data relevant to a user, it hides the encrypted password data.
     * @param userID user id to search for.
     * @param token token necessary to authorize the request.
     * @return User object containing all data.
     */
    @GetMapping(path = "{userId}")
    public ResponseEntity<CustomResponse<User>> getUser(
            @PathVariable("userId") UUID userID,
            @CookieValue UUID token){
        try {
            User user = userService.getUser(userID, token);
            return ResponseEntity.ok(new CustomResponse<>(user, null));
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(e.getStatus()).body(new CustomResponse<>(null, e.getMessage()));
        }
    }

    /***
     * Registers a new user in the database and encrypts the given password.
     * @param userDTO email, password, username
     * @return User ID
     */
    @PostMapping(path = "signup")
    public ResponseEntity<CustomResponse<UUID>> registerNewUser(@RequestBody UserDTO userDTO) {
        try {
            UUID userID = userService.addNewUser(userDTO.getEmail(), userDTO.getPassword(), userDTO.getUsername(), "registered").getId();
            return ResponseEntity.ok(new CustomResponse<>(userID, null));
        } catch (EmailTakenException | InvalidFormatException e) {
            return ResponseEntity.status(e.getStatus()).body(new CustomResponse<>(null, e.getMessage()));
        }
    }

    /**
     * Registers a new user in the database as a guest, it has randomized credentials and is finite in time.
     * @return Response containing token for the guest user or error message.
     */
    @PostMapping(path = "signup/guest")
    public ResponseEntity<CustomResponse<Token>> registerNewGuestUser() {
        try {
            Token token = userService.addNewGuestUser();
            return ResponseEntity.ok(new CustomResponse<>(token, null));
        } catch (UserNotFoundException | IncorrectCredentialsException e) {
            return ResponseEntity.status(e.getStatus()).body(new CustomResponse<>(null, e.getMessage()));
        }
    }

    //TODO PUT: updateUser(),
    //TODO DEL: deleteUser(),

    /**
     * User Data Transfer Object, used exclusively to receive the credentials from the client.
     */
    public static class UserDTO { //Data Transfer Object
        private String email;
        private String password;
        private String username;

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

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }
}
