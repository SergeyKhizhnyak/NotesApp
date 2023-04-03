package com.example.notesapp;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Note.class}, version = 1)
public abstract class NotesDatabase extends RoomDatabase {
    private static volatile NotesDatabase instance;

    public static NotesDatabase getDatabase(Context context) {
        if (instance == null) {
            synchronized (NotesDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context,
                            NotesDatabase.class, "notes_db").build();
                }
            }
        }

        return instance;
    }

    public abstract NoteDao noteDao();
}
