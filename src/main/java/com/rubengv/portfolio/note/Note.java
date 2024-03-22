package com.rubengv.portfolio.note;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

@Entity
@Table
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private UUID user_id;
    @Column(length = 1000)
    private String title;
    @Column(length = 100000)
    private String content;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    public Note() {
    }

    public Note(UUID user_id, String title, String content) {
        this.user_id = user_id;
        this.title = title;
        this.content = content;
        this.created_at = LocalDateTime.now(ZoneId.of("UTC"));
        this.updated_at = LocalDateTime.now(ZoneId.of("UTC"));
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getUser_id() {
        return user_id;
    }

    public void setUser_id(UUID user_id) {
        this.user_id = user_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public LocalDateTime getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(LocalDateTime updated_at) {
        this.updated_at = updated_at;
    }
}
