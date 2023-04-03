package com.example.notesapp.model;

import android.content.Context;

import androidx.room.Room;

import com.example.notesapp.Note;
import com.example.notesapp.NotesDatabase;
import com.example.notesapp.controler.Contract;

import java.util.List;

public class DatabaseModel implements Contract.Model {

    @Override
    public List<Note> getAll(Context context) {
        NotesDatabase db = Room.databaseBuilder(
                        context,
                        NotesDatabase.class,
                        "notes_db")
                .build();
        return db.noteDao().getAll();
    }

    @Override
    public void addItem(Context context, Note note) {
        NotesDatabase db = Room.databaseBuilder(
                        context,
                        NotesDatabase.class,
                        "notes_db")
                .build();
        db.noteDao().addNotes(note);
    }

    @Override
    public void deleteItem(Context context, Note note) {
        NotesDatabase db = Room.databaseBuilder(
                        context,
                        NotesDatabase.class,
                        "notes_db")
                .build();
        db.noteDao().deleteNotes(note);
    }
}
