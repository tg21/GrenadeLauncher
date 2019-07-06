package com.example.grenade

import android.graphics.drawable.Drawable

public   class  appInfo(var applabel:String,var packageName:String,var appicon:Drawable){


    public  fun appInfo(applabel:String, packageName:String,appicon:Drawable) {
        this.applabel = applabel
        this.packageName = packageName
        this.appicon = appicon
    }

    public fun getAppLabel(): String {
        return applabel
    }
    public fun getpackageName(): String {
        return packageName
    }
    public fun getAppIcon(): Drawable {
        return appicon
    }

}

