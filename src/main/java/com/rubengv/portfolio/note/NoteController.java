package com.rubengv.portfolio.note;

import com.rubengv.portfolio.CustomResponse;
import com.rubengv.portfolio.exceptions.IncorrectTokenException;
import com.rubengv.portfolio.exceptions.NoteEmptyException;
import com.rubengv.portfolio.exceptions.NoteNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "api/v1/note")
//@CrossOrigin(origins = {"http://localhost:3000", "https://www.rubengv.com", "https://www.rubengv.com/", "www.rubengv.com"}, allowCredentials = "true", exposedHeaders = "Authorization")
public class NoteController {
    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping(path = "{userId}")
    public ResponseEntity<CustomResponse<List<Note>>> getNotesByUserID(
            @PathVariable("userId") UUID userID,
            @CookieValue UUID token) {
        try {
            List<Note> notes = noteService.getAllNotes(userID, token);
            return ResponseEntity.ok(new CustomResponse<>(notes, null));
        } catch (IncorrectTokenException e) {
            return ResponseEntity.status(e.getStatus()).body(new CustomResponse<>(null, e.getMessage()));
        }
    }

    @PostMapping(path = "{userId}")
    public ResponseEntity<CustomResponse<Note>> addNewNote(
            @PathVariable("userId") UUID userID,
            @CookieValue UUID token,
            @RequestBody NoteDTO noteDTO) {
        try {
            Note note = noteService.addNewNote(userID, token, noteDTO.title, noteDTO.content);
            return ResponseEntity.ok(new CustomResponse<>(note, null));
        } catch (IncorrectTokenException e) {
            return ResponseEntity.status(e.getStatus()).body(new CustomResponse<>(null, e.getMessage()));
        }
    }

    @PutMapping(path = "{userId}/{noteId}")
    public ResponseEntity<CustomResponse<Note>> editNote(
            @PathVariable("userId") UUID userID,
            @PathVariable("noteId") UUID noteID,
            @CookieValue UUID token,
            @RequestBody(required = false) NoteDTO noteDTO) {
        try {
            Note note = noteService.editNote(userID, noteID, token, noteDTO.title, noteDTO.content);
            return ResponseEntity.ok(new CustomResponse<>(note, null));
        } catch (IncorrectTokenException | NoteNotFoundException | NoteEmptyException e) {
            return ResponseEntity.status(e.getStatus()).body(new CustomResponse<>(null, e.getMessage()));
        }
    }

    @DeleteMapping(path = "{userId}/{noteId}")
    public ResponseEntity<CustomResponse<String>> deleteNote(
            @PathVariable("userId") UUID userID,
            @PathVariable("noteId") UUID noteID,
            @CookieValue UUID token) {
        try {
            noteService.deleteNote(userID, noteID, token);
            return ResponseEntity.ok(new CustomResponse<>("Deleted", null));
        } catch (IncorrectTokenException e) {
            return ResponseEntity.ok(new CustomResponse<>(null, e.getMessage()));
        }
    }

    public static class NoteDTO {
        private String title;
        private String content;

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
    }
}
