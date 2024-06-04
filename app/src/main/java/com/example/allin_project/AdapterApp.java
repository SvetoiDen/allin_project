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

public class AdapterApp extends RecyclerView.Adapter<AdapterApp.MyApp> {

    private Context context;
    Activity activity;
    private ArrayList app_id, app_name, com_app;
    AdapterApp(Activity activity, Context context, ArrayList app_id, ArrayList app_name, ArrayList com_app) {
        this.context = context;
        this.activity = activity;
        this.app_id = app_id;
        this.app_name = app_name;
        this.com_app = com_app;
    }

    @NonNull
    @Override
    public AdapterApp.MyApp onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.my_apps, parent, false);
        return new AdapterApp.MyApp(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterApp.MyApp holder, @SuppressLint("RecyclerView") final int position) {
        holder.name_app_2.setText(String.valueOf(app_name.get(position)));
        holder.mainLayout_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, App_ListActivity.class);
                intent.putExtra("com", String.valueOf(com_app.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return app_id.size();
    }

    public class MyApp extends RecyclerView.ViewHolder {
        TextView app_id_1, name_app_2;
        LinearLayout mainLayout_1;
        public MyApp(@NonNull View itemView) {
            super(itemView);
            app_id_1 = itemView.findViewById(R.id.app_id_1);
            name_app_2 = itemView.findViewById(R.id.name_app_2);
            mainLayout_1 = itemView.findViewById(R.id.mainLayout);
        }
    }
}
