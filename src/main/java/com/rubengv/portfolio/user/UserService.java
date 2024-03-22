package com.rubengv.portfolio.user;

import com.rubengv.portfolio.Helper;
import com.rubengv.portfolio.exceptions.EmailTakenException;
import com.rubengv.portfolio.exceptions.IncorrectCredentialsException;
import com.rubengv.portfolio.exceptions.InvalidFormatException;
import com.rubengv.portfolio.exceptions.UserNotFoundException;
import com.rubengv.portfolio.token.Token;
import com.rubengv.portfolio.token.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, TokenService tokenService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
    }

    public User getUser(UUID userID, UUID token) throws UserNotFoundException {
        if (tokenService.verifyToken(token, userID)) {
            User user = userRepository.findById(userID).orElseThrow(
                    () -> new UserNotFoundException(userID.toString())
            );
            user.setEncrypted_password("*".repeat(user.getEncrypted_password().length()));
            return user;
        } else
            return null;
    }

    public User addNewUser(String email, String password, String username, String user_type) throws EmailTakenException, InvalidFormatException {
        if (!email.matches(Helper.EMAIL_REGEX) || email.length() > 255)
            throw new InvalidFormatException("The email " + email + " is not valid.");
        if (password.length() < 6)
            throw new InvalidFormatException("password too short");
        if (password.length() > 255)
            throw new InvalidFormatException("password too long");
//        if (username.length() < 3 || username.length() > 255)
//            throw new InvalidFormatException(username);
        Optional<User> existingUser = userRepository.findUserByEmail(email);
        if (existingUser.isPresent()) {
            throw new EmailTakenException(email);
        }
        String encryptedPassword = passwordEncoder.encode(password);
        User finalUser = new User(email, encryptedPassword, username, user_type);
        return userRepository.save(finalUser);
    }

    @Transactional
    public Token addNewGuestUser() throws UserNotFoundException, IncorrectCredentialsException {
        System.out.println("Adding new Guest User");
        String email = Helper.generateRandomString(8)
                + "@noteitguest-"
                + Helper.generateRandomString(4)
                + ".com";
        String password = "PaSsWorD1269-#"; //Guest accounts are a trial, NOT SECURE.
        String encryptedPassword = passwordEncoder.encode(password);
        String username = "Guest-" + Helper.generateRandomString(4);
        User finalUser = new User(email, encryptedPassword, username, "guest");
        userRepository.save(finalUser);
        return tokenService.addNewToken(email, password);
    }

    public void deleteUser(UUID uid, String password, String token) {
        //TODO verify token
        if (!userRepository.existsById(uid)) {
            throw new IllegalStateException("User with id " + uid + " does not exist.");
        }
        //TODO delete all related data/notes/anything
        userRepository.deleteById(uid);
    }

    public void updateUser(UUID uid, String token, String username, String prevPassword, String newPassword) {

    }
}
