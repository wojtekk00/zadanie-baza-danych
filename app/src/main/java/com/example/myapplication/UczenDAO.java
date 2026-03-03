package com.example.myapplication;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UczenDAO {
    @Insert
    void dodajUcznia(Uczen uczen);
    @Delete
    void usunUcznia(Uczen uczen);
    @Update
    void edytujDaneUcznia(Uczen uczen);
    @Query("SELECT * FROM uczniowie")
    List<Uczen> zwrocWszystkichUczniow();
}
