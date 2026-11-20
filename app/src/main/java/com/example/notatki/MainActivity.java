package com.example.notatki;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView lista;
    private EditText tytulEdit;
    private EditText trescEdit;
    private Button dodajButton;

    private final ArrayList<String> notatki = new ArrayList<>();
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Dopasowanie marginesów do pasków systemowych ekranu
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        inicjalizujWidoki();
        skonfigurujListe();

        // Obsługa kliknięcia przycisku dodawania notatki
        dodajButton.setOnClickListener(v -> dodajNowaNotatke());
    }

    private void inicjalizujWidoki() {
        lista = findViewById(R.id.lista);
        tytulEdit = findViewById(R.id.tytul);
        trescEdit = findViewById(R.id.tresc);
        dodajButton = findViewById(R.id.dodaj);
    }

    private void skonfigurujListe() {
        notatki.add("Tytuł \n treść");
        // Użycie standardowego układu listy z Android SDK zamiast brakującego layoutu
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, notatki);
        lista.setAdapter(adapter);
    }

    private void dodajNowaNotatke() {
        String tytul = tytulEdit.getText().toString();
        String tresc = trescEdit.getText().toString();

        // Połączenie tytułu i treści w pojedynczy wpis listy
        String wpis = tytul + "\n" + tresc;
        notatki.add(wpis);
        adapter.notifyDataSetChanged();
    }
}