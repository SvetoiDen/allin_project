package com.example.allin_project;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterNotes extends RecyclerView.Adapter<AdapterNotes.MyNotes> {

    private Context context;
    Activity activity;
    private ArrayList note_id, note_name, note_desc;

    AdapterNotes(Activity activity, Context context, ArrayList note_id, ArrayList note_name, ArrayList note_desc) {
        this.context = context;
        this.activity = activity;
        this.note_id = note_id;
        this.note_name = note_name;
        this.note_desc = note_desc;
    }

    @NonNull
    @Override
    public MyNotes onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.my_notes, parent, false);
        return new MyNotes(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyNotes holder, @SuppressLint("RecyclerView") final int position) {
        holder.note_id_txt.setText(String.valueOf(note_id.get(position)));
        holder.note_name_txt.setText(String.valueOf(note_name.get(position)));
        holder.note_desc_txt.setText(String.valueOf(note_desc.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, update_notes.class);
                intent.putExtra("id", String.valueOf(note_id.get(position)));
                intent.putExtra("name", String.valueOf(note_name.get(position)));
                intent.putExtra("description", String.valueOf(note_desc.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return note_id.size();
    }

    public class MyNotes extends RecyclerView.ViewHolder {

        TextView note_id_txt, note_name_txt, note_desc_txt;
        LinearLayout mainLayout;

        public MyNotes(@NonNull View itemView) {
            super(itemView);
            note_id_txt = itemView.findViewById(R.id.notes_id_1);
            note_name_txt = itemView.findViewById(R.id.name_notes_2);
            note_desc_txt = itemView.findViewById(R.id.name_desc_3);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
