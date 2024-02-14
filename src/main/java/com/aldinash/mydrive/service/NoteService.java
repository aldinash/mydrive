package com.aldinash.mydrive.service;

import com.aldinash.mydrive.model.Note;
import com.aldinash.mydrive.mapper.NoteMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {
    private final NoteMapper noteMapper;

    public NoteService(NoteMapper noteMapper) {
        this.noteMapper = noteMapper;
    }

    public List<Note> getAllNotes(int userid) throws Exception {
        List<Note> notes = noteMapper.findByUserId(userid);
        if (notes == null) {
            throw new Exception();
        }
        return notes;
    }

    public int createNote(Note note, int userid) {
        return noteMapper.insertNote(note, userid);
    }

    public int editNote(Note note) {
        return noteMapper.updateNote(note);
    }

    public int deleteNote(int noteid) {
        return noteMapper.deleteNote(noteid);
    }
}
