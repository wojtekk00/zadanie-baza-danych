package com.example.myapplication;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    UczniowieDataBase uczniowieDB;
    ListView listViewUczniowie;
    ArrayAdapter<Uczen> arrayAdapter;
    List<Uczen> listUczniowie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        uczniowieDB = UczniowieDataBase.zwrocBazeDanych(MainActivity.this);
        //uczniowieDB.zwrocUczenDAO().dodajUcznia(new Uczen("Jan", "Nowak", 00000001, "5a"));
        //uczniowieDB.zwrocUczenDAO().dodajUcznia(new Uczen("Adam", "Kłos", 00000002, "3c"));
        //uczniowieDB.zwrocUczenDAO().dodajUcznia(new Uczen("Grzegorz", "Małysz", 00000003, "4b"));

        listViewUczniowie = findViewById(R.id.listViewUczniowie);
        listUczniowie = uczniowieDB.zwrocUczenDAO().zwrocWszystkichUczniow();
        arrayAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, listUczniowie);
        listViewUczniowie.setAdapter(arrayAdapter);
    }
}