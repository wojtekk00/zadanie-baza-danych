package com.example.myapplication;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Uczen.class}, version = 1)
public abstract class UczniowieDataBase extends RoomDatabase {
    public abstract UczenDAO zwrocUczenDAO();
    private static UczniowieDataBase instancja;
    public static UczniowieDataBase zwrocBazeDanych(Context context){
        if (instancja == null){
            instancja = Room.databaseBuilder(
                            context.getApplicationContext(),
                            UczniowieDataBase.class,
                            "uczniowie_db")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }

        return instancja;
    }
}
