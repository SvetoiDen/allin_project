package com.example.allin_project;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NotesAcrivity extends AppCompatActivity {

    BDNotes db;
    RecyclerView recyclerView;
    ArrayList<String> note_id, note_name, note_desc;
    AdapterNotes adapterNotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_notes_acrivity);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recyclerView = findViewById(R.id.recyclerView);

        db = new BDNotes(NotesAcrivity.this);
        note_id = new ArrayList<>();
        note_name = new ArrayList<>();
        note_desc = new ArrayList<>();

        storeDataInArrrys();

        adapterNotes = new AdapterNotes(NotesAcrivity.this, this, note_id, note_name, note_desc);
        recyclerView.setAdapter(adapterNotes);
        recyclerView.setLayoutManager(new LinearLayoutManager(NotesAcrivity.this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            recreate();
        }
    }

    public void back(View v) {
        NotesAcrivity.this.startActivity(new Intent(NotesAcrivity.this, MainActivity.class));
    }

    public void create_notes_get(View v) {
        NotesAcrivity.this.startActivity(new Intent(NotesAcrivity.this, create_notes.class));
    }

    void storeDataInArrrys() {
        Cursor cursor = db.readAllDate();

        if (!(cursor.getCount() == 0)) {
            while (cursor.moveToNext()) {
                note_id.add(cursor.getString(0));
                note_name.add(cursor.getString(1));
                note_desc.add(cursor.getString(2));
            }
        }
    }
}