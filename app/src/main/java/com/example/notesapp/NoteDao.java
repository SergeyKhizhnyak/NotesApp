package com.example.notesapp;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface NoteDao {
    @Query("SELECT * FROM  notes_db")
    List<Note> getAll();

    @Insert
    void addNotes(Note... notes);

    @Delete
    void deleteNotes(Note... notes);
}
