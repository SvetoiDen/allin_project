package com.example.allin_project;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class create_notes extends AppCompatActivity {

    BDNotes db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_create_notes);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void back(View v) {
        create_notes.this.startActivity(new Intent(create_notes.this, NotesAcrivity.class));
    }

    public void create_notes(View v) {
        db = new BDNotes(create_notes.this);
        EditText name = findViewById(R.id.name_1);
        EditText desc = findViewById(R.id.desc_2);
        db.addNotes(name.getText().toString(), desc.getText().toString());

        create_notes.this.startActivity(new Intent(create_notes.this, NotesAcrivity.class));
    }
}