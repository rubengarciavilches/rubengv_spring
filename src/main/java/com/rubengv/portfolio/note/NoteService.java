package com.rubengv.portfolio.note;

import com.rubengv.portfolio.exceptions.IncorrectTokenException;
import com.rubengv.portfolio.exceptions.NoteEmptyException;
import com.rubengv.portfolio.exceptions.NoteNotFoundException;
import com.rubengv.portfolio.token.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class NoteService {
    private final NoteRepository noteRepository;
    private final TokenService tokenService;

    @Autowired
    public NoteService(NoteRepository noteRepository, TokenService tokenService) {
        this.noteRepository = noteRepository;
        this.tokenService = tokenService;
    }

    public List<Note> getAllNotes(UUID userID, UUID tokenID) throws IncorrectTokenException {
        if (tokenService.verifyToken(tokenID, userID))
            return noteRepository.findNotesByUserId(userID);
        else return null;
    }

    public Note addNewNote(UUID userID, UUID token, String title, String content) throws IncorrectTokenException {
        if (tokenService.verifyToken(token, userID)) {
            Note Note = new Note(userID, title, content);
            return noteRepository.save(Note);
        } else {
            return null;
        }
    }

    public void deleteNote(UUID userID, UUID noteID, UUID token) throws IncorrectTokenException {
        if (tokenService.verifyToken(token, userID))
            noteRepository.deleteById(noteID);
    }

    @Transactional
    public Note editNote(UUID userID, UUID noteID, UUID token, String title, String content)
            throws IncorrectTokenException, NoteNotFoundException, NoteEmptyException {
        if (tokenService.verifyToken(token, userID)) {
            Note note = noteRepository.findById(noteID).orElseThrow(
                    () -> new NoteNotFoundException(noteID.toString())
            );
            if ((title == null || title.isEmpty())
                    && (content == null || content.isEmpty()))
                throw new NoteEmptyException(noteID.toString());
            if (title != null)
                note.setTitle(title);
            if (content != null)
                note.setContent(content);
            return note;
        } else {
            return null;
        }
    }
}
