package com.example.voiceassistance.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.voiceassistance.model.Note;

import java.util.List;

@Dao
public interface NotesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)//if note already exist then it will be replaced
    void insertNote(Note note);
    @Delete
    void deleteNote(Note note);
    @Update
    void updateNote(Note note);
  /*  @Query("SELECT*FROM notes")//list all notes from database
    List<Note> getNotes();
    @Query("SELECT * FROM notes WHERE id = :noteId") //get note by Id
    Note getNoteById(int noteId);
    @Query("DELETE FROM notes WHERE id = :noteId")
    void deleteNoteById(int noteId);*/
}
