package com.tushar.gautam.grenade;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class DrawerActivity extends AppCompatActivity {
    RecyclerView recycler_drawer;
    ArrayList<AppInfo> AppArray = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);
        recycler_drawer = findViewById(R.id.recyclerView);

        PackageManager pm = this.getPackageManager();
        AppArray = new ArrayList<AppInfo>();

        Intent i = new Intent(Intent.ACTION_MAIN, null);
        i.addCategory(Intent.CATEGORY_LAUNCHER);

        List<ResolveInfo> allApps = pm.queryIntentActivities(i, 0);
        for(ResolveInfo ri:allApps) {
            /*AppInfo app = new AppInfo();
            app.label = (String) ri.loadLabel(pm);
            app.packageName = ri.activityInfo.packageName;
            app.appicon = ri.activityInfo.loadIcon(pm);
            appsList.add(app);*/
            AppArray.add(new AppInfo((String) ri.loadLabel(pm), ri.activityInfo.packageName, ri.activityInfo.loadIcon(pm)));

        }

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),6, LinearLayoutManager.VERTICAL,false);
        recycler_drawer.setLayoutManager(gridLayoutManager); // set LayoutManager to RecyclerView
        //  call the constructor of CustomAdapter to send the reference and data to Adapter
        CustomAdapter customAdapter = new CustomAdapter(this, AppArray);
        recycler_drawer.setAdapter(customAdapter); // set the Adapter to RecyclerView

    }

}
