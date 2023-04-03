package com.example.notesapp;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "notes_db")
public class Note implements Serializable {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "header")
    public String header;

    @ColumnInfo(name = "date")
    public String date;

    @ColumnInfo(name = "symbol_count")
    public String symbolCount;

    @ColumnInfo(name = "body")
    public String body;

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", header='" + header + '\'' +
                ", date='" + date + '\'' +
                ", symbolCount='" + symbolCount + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
