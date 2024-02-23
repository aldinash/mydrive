package com.aldinash.mydrive.service;

import com.aldinash.mydrive.model.Note;
import com.aldinash.mydrive.mapper.NoteMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    private NoteMapper notesMapper;

    public NoteService(NoteMapper notesMapper) {
        this.notesMapper = notesMapper;
    }

    public List<Note> getAllNotes(int userid) throws Exception {
        List<Note> notes = notesMapper.findByUserId(userid);
        if (notes == null) {
            throw new Exception();
        }
        return notes;
    }

    public void addNote(Note note, int userid) {
        notesMapper.insertNote(note, userid);
    }

    public void updateNote(Note note) {
        notesMapper.updateNote(note);
    }

    public void deleteNote(int noteid) {
        notesMapper.deleteNote(noteid);
    }
}
