package com.example.grenade

import android.content.Context
//import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_app_item.view.*
import kotlinx.android.synthetic.main.animal_list_item.view.*

public class  CustomAdapter(var AppItem : ArrayList<appInfo>, var context: Context) :
    RecyclerView.Adapter<CustomAdapter.MyViewHolder>() {

//    var superview : ViewGroup
    public  fun CustomAdapter(context: Context,AppItem:ArrayList<appInfo>){
        this.AppItem = AppItem
        this.context = context
    }

    override public fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomAdapter.MyViewHolder {
//        superview = parent
        val v = LayoutInflater.from(parent.context).inflate(R.layout.activity_app_item,parent,false)
        val vh = MyViewHolder(v)
        return vh
    }

    override public fun onBindViewHolder( holder: MyViewHolder, position: Int) {

        val appInfo = AppItem.get(position)
        holder.name.text = appInfo.getAppLabel()
        holder.image.setImageDrawable(appInfo.getAppIcon())
        holder.itemView.setOnClickListener{
            val launchIntent = context.packageManager.getLaunchIntentForPackage(appInfo.packageName)
            context.startActivity(launchIntent)
        }
    }

    override public fun getItemCount(): Int {
        return AppItem.size
    }


    public  class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
//        var itemView: View,var name: TextView, var image:ImageView
//lateinit var name:TextView
//        var image:ImageView

//        fun MyViewHolder(itemView: View) {
//        super(itemView)

           val name = itemView.textView_label
        val image = itemView.imageView_app
//    }

    }





}


