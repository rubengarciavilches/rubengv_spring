package com.rubengv.portfolio.user;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

@Entity //Establishes this as an entity that will be mapped to the database.
@Table(name = "users") //Just clarifies that the default name will be used "User"
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String email;
    private String encrypted_password;
    private String username;
    private LocalDateTime created_at;
    private String user_type;

    public User() {
    }

    public User(String email, String encrypted_password, String username, String user_type) {
        this.email = email;
        this.encrypted_password = encrypted_password;
        this.username = username;
        this.created_at = LocalDateTime.now(ZoneId.of("UTC"));
        this.user_type = user_type;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEncrypted_password() {
        return encrypted_password;
    }

    public void setEncrypted_password(String encrypted_password) {
        this.encrypted_password = encrypted_password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", encrypted_password='" + encrypted_password + '\'' +
                ", username='" + username + '\'' +
                ", created_at=" + created_at +
                ", user_type='" + user_type + '\'' +
                '}';
    }
}
