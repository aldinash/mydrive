package com.aldinash.mydrive.mapper;

import com.aldinash.mydrive.model.Note;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NoteMapper {
    @Select("SELECT * FROM NOTES")
    List<Note> findAll();

    @Select("SELECT * FROM NOTES WHERE noteid=#{noteid}")
    Note findNote(int noteid);
    @Select("SELECT * FROM NOTES WHERE userid=#{userid}")
    List<Note> findByUserId(int userid);

    @Insert("INSERT INTO NOTES (notetitle, notedescription, userid) VALUES (#{note.noteTitle}, #{note.noteDescription}, #{userid})")
    int insertNote(Note note, int userid);

    @Delete("DELETE FROM NOTES WHERE noteid = #{noteid}")
    int deleteNote(int noteid);

    @Update("UPDATE NOTES SET notetitle=#{noteTitle}, notedescription=#{noteDescription} WHERE noteid=#{noteid}")
    int updateNote(Note note);
}
