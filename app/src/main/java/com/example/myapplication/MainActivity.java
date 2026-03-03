package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

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
    EditText editTextImie;
    EditText editTextNazwisko;
    EditText editTextPesel;
    EditText editTextKlasa;
    Button buttonZatwierdz;

    Boolean czyEdytuj = false;
    int indexUcznia;

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
        //uczniowieDB.zwrocUczenDAO().dodajUcznia(new Uczen("Jan", "Nowak", 123456456, "5a"));
        //uczniowieDB.zwrocUczenDAO().dodajUcznia(new Uczen("Adam", "Kłos", 234345656, "3c"));
        //uczniowieDB.zwrocUczenDAO().dodajUcznia(new Uczen("Grzegorz", "Małysz", 34565446, "4b"));

        listViewUczniowie = findViewById(R.id.listViewUczniowie);
        listUczniowie = uczniowieDB.zwrocUczenDAO().zwrocWszystkichUczniow();
        arrayAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, listUczniowie);
        listViewUczniowie.setAdapter(arrayAdapter);

        editTextImie = findViewById(R.id.editTextImie);
        editTextNazwisko = findViewById(R.id.editTextNazwisko);
        editTextPesel = findViewById(R.id.editTextPesel);
        editTextKlasa = findViewById(R.id.editTextKlasa);
        buttonZatwierdz = findViewById(R.id.buttonZatwierdz);

        buttonZatwierdz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String imie = editTextImie.getText().toString();
                String nazwisko = editTextNazwisko.getText().toString();
                int pesel = Integer.parseInt(editTextPesel.getText().toString());
                String klasa = editTextKlasa.getText().toString();

                if (czyEdytuj==false){
                    uczniowieDB.zwrocUczenDAO().dodajUcznia(new Uczen(imie, nazwisko, pesel, klasa));
                    listUczniowie.add(new Uczen(imie, nazwisko, pesel, klasa));
                    arrayAdapter.notifyDataSetChanged();
                }

                else if(czyEdytuj){
                    listUczniowie.get(indexUcznia).setImie(editTextImie.getText().toString());
                    listUczniowie.get(indexUcznia).setNazwisko(editTextNazwisko.getText().toString());
                    listUczniowie.get(indexUcznia).setPesel(Integer.parseInt(editTextPesel.getText().toString()));
                    listUczniowie.get(indexUcznia).setKlasa(editTextKlasa.getText().toString());

                    arrayAdapter.notifyDataSetChanged();
                    uczniowieDB.zwrocUczenDAO().edytujDaneUcznia(listUczniowie.get(indexUcznia));

                    czyEdytuj=false;
                }

                editTextImie.setText("");
                editTextNazwisko.setText("");
                editTextPesel.setText("");
                editTextKlasa.setText("");

            }
        });

        listViewUczniowie.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                uczniowieDB.zwrocUczenDAO().usunUcznia(listUczniowie.get(i));
                listUczniowie.remove(i);
                arrayAdapter.notifyDataSetChanged();
                return false;
            }
        });

        listViewUczniowie.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                czyEdytuj = true;
                editTextImie.setText(""+listUczniowie.get(i).getImie());
                editTextNazwisko.setText(""+listUczniowie.get(i).getNazwisko());
                editTextPesel.setText(""+listUczniowie.get(i).getPesel());
                editTextKlasa.setText(""+listUczniowie.get(i).getKlasa());
                indexUcznia = i;
            }
        });
    }
}