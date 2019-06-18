package com.tushar.gautam.grenade;

import android.graphics.drawable.Drawable;

public class AppInfo {
    String label;
    String packageName;
    Drawable appicon;

    public AppInfo(String label,String packageName, Drawable appicon){
        this.label = label;
        this.packageName = packageName;
        this.appicon = appicon;
    }

    public String getLabel(){
        return label;
    }
    public String getPackageName(){
        return packageName;
    }
    public Drawable getAppicon(){
        return appicon;
    }
}
