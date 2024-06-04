package com.example.allin_project;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class update_notes extends AppCompatActivity {

    EditText title_2, desc_2;
    Button update_but;

    String id, name, desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_update_notes);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        title_2 = findViewById(R.id.name_update);
        desc_2 = findViewById(R.id.desc_update);

        getAndSetIntentData();
    }

    public void update_notes(View v) {
        BDNotes db = new BDNotes(update_notes.this);
        db.updateData(id, title_2.getText().toString(), desc_2.getText().toString());
    }

    public void delete_notes(View v) {
        BDNotes db = new BDNotes(update_notes.this);
        db.deleteData(id);
    }

    public void back(View v) {
        update_notes.this.startActivity(new Intent(update_notes.this, NotesAcrivity.class));
    }

    void getAndSetIntentData() {
        if (getIntent().hasExtra("id") && getIntent().hasExtra("name") && getIntent().hasExtra("description")) {
            this.id = getIntent().getStringExtra("id");
            name = getIntent().getStringExtra("name");
            desc = getIntent().getStringExtra("description");

            title_2.setText(name);
            desc_2.setText(desc);
        }
    }
}