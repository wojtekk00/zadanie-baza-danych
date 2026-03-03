package com.example.myapplication;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "uczniowie")
public class Uczen {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String imie;
    private String nazwisko;
    private int pesel;
    private String klasa;

    public Uczen(String imie, String nazwisko, int pesel, String klasa) {
        this.id = 0;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.pesel = pesel;
        this.klasa = klasa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public int getPesel() {
        return pesel;
    }

    public void setPesel(int pesel) {
        this.pesel = pesel;
    }

    public String getKlasa() {
        return klasa;
    }

    public void setKlasa(String klasa) {
        this.klasa = klasa;
    }

    @Override
    public String toString() {
        return "Uczen{" +
                "id=" + id +
                ", imie='" + imie + '\'' +
                ", nazwisko='" + nazwisko + '\'' +
                ", pesel=" + pesel +
                ", klasa='" + klasa + '\'' +
                '}';
    }
}
