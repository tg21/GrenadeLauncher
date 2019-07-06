package com.example.grenade

import android.app.WallpaperManager
import android.content.Intent
import android.content.pm.ResolveInfo
import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.core.view.GravityCompat
import androidx.appcompat.app.ActionBarDrawerToggle
import android.view.MenuItem
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.view.Menu
import android.view.Window
import android.view.WindowManager
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.*
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.reflect.AccessibleObject

class MainActivity : AppCompatActivity()/*, NavigationView.OnNavigationItemSelectedListener*/ {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //setting wallpaer and status-bar

        val wm = WallpaperManager.getInstance(this)
        val wd = wm.drawable

        var dl:DrawerLayout = findViewById(R.id.drawer_layout)
        dl.background = wd

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            var w = window
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        }



        /// getting all package info
        val pm = this.packageManager
        var AppArray : ArrayList<appInfo> = ArrayList()

        val i = Intent(Intent.ACTION_MAIN,null)
        i.addCategory(Intent.CATEGORY_LAUNCHER)

        val allApps :List<ResolveInfo> = pm.queryIntentActivities(i,0)
        for ( ri:ResolveInfo in allApps){
            AppArray.add(appInfo(ri.loadLabel(pm) as String, ri.activityInfo.packageName, ri.activityInfo.loadIcon(pm)))
        }

//        inline fun <ri> Map<out allApps>.forEach(
//            action:(AppArray((String) ri.loadLabel(pm), ri.activityInfo.packageName, ri.activityInfo.loadIcon(pm)))->unit)


        /// buttons
        /*val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)*/
        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener { /*view ->
            Snackbar.make(view, "Bravo You clicked a button", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()*/
            drawer_layout.openDrawer(GravityCompat.START)
        }


        ///setting widht of navigation drawer to 100%
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val metrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(metrics)
        val params = navView.layoutParams
        params.width = metrics.widthPixels
        navView.layoutParams = params


        val toggle = ActionBarDrawerToggle(
            this, drawerLayout/*, toolbar*/, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

//        drawer_items.layoutManager = LinearLayoutManager(this)
//        drawer_items.adapter = AnimalAdapter(AppArray, this)
        var rowx = 6
        
        val gridLayoutManager = GridLayoutManager(applicationContext,rowx,RecyclerView.VERTICAL,false)
        drawer_items.layoutManager = gridLayoutManager
        val customAdapter = CustomAdapter(AppArray,this);
        drawer_items.adapter = customAdapter
    }

//        navView.setNavigationItemSelectedListener(this)


    override fun onBackPressed() {
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

//    override fun onNavigationItemSelected(item: MenuItem): Boolean {
//        // Handle navigation view item clicks here.
//        when (item.itemId) {
//            R.id.nav_home -> {
//                // Handle the camera action
//            }
//            R.id.nav_gallery -> {
//
//            }
//            R.id.nav_slideshow -> {
//
//            }
//            R.id.nav_tools -> {
//
//            }
//            R.id.nav_share -> {
//
//            }
//            R.id.nav_send -> {
//
//            }
//        }
//        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
//        drawerLayout.closeDrawer(GravityCompat.START)
//        return true
//    }
}
