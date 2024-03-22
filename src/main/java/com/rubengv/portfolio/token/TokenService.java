package com.rubengv.portfolio.token;

import com.rubengv.portfolio.exceptions.*;
import com.rubengv.portfolio.user.User;
import com.rubengv.portfolio.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

@Service
public class TokenService {
    private final TokenRepository tokenRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public TokenService(TokenRepository tokenRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.tokenRepository = tokenRepository; 
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Verifies that the token provided is valid and it has permissions for that user.
     * @param tokenID
     * @param userID
     * @return
     */
    public boolean verifyToken(UUID tokenID, UUID userID) throws IncorrectTokenException{
        Token token = tokenRepository.findById(tokenID).orElseThrow(
                () -> new TokenNotFoundException(tokenID.toString())
        );
        if (token.getUser_id().compareTo(userID) != 0)
            throw new IncorrectTokenException(tokenID.toString());
        if(token.getExpires_at().isBefore(LocalDateTime.now(ZoneId.of("UTC")))){
            tokenRepository.deleteById(tokenID);
            throw new OutdatedTokenException(tokenID.toString());
        }
        return true;
    }

    /**
     * Authenticates a user based on the provided email and password, and generates a new token.
     *
     * @param email    The email of the user attempting to log in.
     * @param password The password of the user attempting to log in.
     * @return A Token object granted to the user upon successful authentication.
     * @throws UserNotFoundException           If no user with the specified email is found.
     * @throws IncorrectCredentialsException    If the provided password does not match the email.
     */
    public Token addNewToken(String email, String password) throws UserNotFoundException, IncorrectCredentialsException {
        User userOptional = userRepository.findUserByEmail(email).orElseThrow(
                () -> new UserNotFoundException(email)
        );
        if (!passwordEncoder.matches(password, userOptional.getEncrypted_password())) {
            throw new IncorrectCredentialsException(email);
        }

        return tokenRepository.save(new Token(userOptional.getId()));
    }

    public void deleteToken(UUID userID, UUID tokenID){
        Token token = tokenRepository.findById(tokenID).orElseThrow(
                () -> new TokenNotFoundException(tokenID.toString())
        );
        if (token.getUser_id().compareTo(userID) != 0)
            throw new IncorrectTokenException(tokenID.toString());
        tokenRepository.deleteById(tokenID);
    }
}
