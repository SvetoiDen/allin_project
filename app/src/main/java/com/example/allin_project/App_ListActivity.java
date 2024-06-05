package com.example.allin_project;

import static android.Manifest.permission.MANAGE_DEVICE_POLICY_APP_USER_DATA;
import static android.Manifest.permission.POST_NOTIFICATIONS;
import static android.Manifest.permission.MANAGE_EXTERNAL_STORAGE;
import static android.Manifest.permission_group.CAMERA;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.DropBoxManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class App_ListActivity extends AppCompatActivity {

    Button but_app;
    private static int app_date = 0;
    BDNotes db;
    RecyclerView recyclerView;
    ArrayList<String> app_id, app_name, com_desc;
    AdapterApp adapterApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_app_list);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recyclerView = findViewById(R.id.recyclerView_1);

        getAndSetIntentData();

        db = new BDNotes(App_ListActivity.this);
        app_id = new ArrayList<>();
        app_name = new ArrayList<>();
        com_desc = new ArrayList<>();

        db.appDate();

        storeDataInArrrys();

        adapterApp = new AdapterApp(App_ListActivity.this, this, app_id, app_name, com_desc);
        recyclerView.setAdapter(adapterApp);
        recyclerView.setLayoutManager(new LinearLayoutManager(App_ListActivity.this));
    }

    @Override
    protected void onPause() {
        super.onPause();
        BDNotes db = new BDNotes(App_ListActivity.this);
        db.deleteApp();
    }

    public void back2(View v) {
        App_ListActivity.this.startActivity(new Intent(App_ListActivity.this, MainActivity.class));
    }

    void getAndSetIntentData() {
        if (getIntent().hasExtra("com")) {
            String desc = getIntent().getStringExtra("com");
            startApplication(desc);
        }
    }

    void storeDataInArrrys() {
        Cursor cursor = db.readAllDate_App();

        if (!(cursor.getCount() == 0)) {
            while (cursor.moveToNext()) {
                app_id.add(cursor.getString(0));
                app_name.add(cursor.getString(1));
                com_desc.add(cursor.getString(2));
            }
        }
    }

    public void startApplication(String packageName)
    {
        try
        {
            Intent intent = new Intent("android.intent.action.MAIN");
            intent.addCategory("android.intent.category.LAUNCHER");

            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            @SuppressLint("QueryPermissionsNeeded") List<ResolveInfo> resolveInfoList = getPackageManager().queryIntentActivities(intent, 0);

            for(ResolveInfo info : resolveInfoList)
                if(info.activityInfo.packageName.equalsIgnoreCase(packageName))
                {
                    launchComponent(info.activityInfo.packageName, info.activityInfo.name);
                    return;
                }
            showInMarket(packageName);
        }
        catch (Exception e)
        {
            showInMarket(packageName);
        }
    }

    private void launchComponent(String packageName, String name)
    {
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.LAUNCHER");
        intent.setComponent(new ComponentName(packageName, name));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void showInMarket(String packageName)
    {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + packageName));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}