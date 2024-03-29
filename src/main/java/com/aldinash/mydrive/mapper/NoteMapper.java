package com.aldinash.mydrive.mapper;

import com.aldinash.mydrive.model.Note;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface NoteMapper {
    @Select("SELECT * FROM NOTES")
    List<Note> findAll();

    @Select("SELECT * FROM NOTES WHERE noteid = #{noteid}")
    public Note findOne(int noteid);

    @Select("SELECT * FROM NOTES WHERE userid = #{userid}")
    public List<Note> findByUserId(int userid);

    @Insert("INSERT INTO NOTES (notetitle, notedescription, userid) VALUES (#{note.notetitle}, #{note.notedescription}, #{userid})")
    public int insertNote(Note note, int userid);

    @Delete("DELETE FROM NOTES WHERE noteid = #{noteid}")
    public int deleteNote(int noteid);

    @Update("UPDATE NOTES SET notetitle = #{notetitle}, notedescription = #{notedescription} WHERE noteid = #{noteid}")
    public int updateNote(Note note);
}
