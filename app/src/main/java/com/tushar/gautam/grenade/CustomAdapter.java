package com.tushar.gautam.grenade;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    ArrayList AppItem;
    Context context;
    public ViewGroup superview;

    public CustomAdapter(Context context, ArrayList AppItem) {
        this.context = context;
        this.AppItem = AppItem;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        superview = parent;
        // infalte the item Layout
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_app_item, parent, false);
        // set the view's size, margins, paddings and layout parameters
        MyViewHolder vh = new MyViewHolder(v); // pass the view to View Holder
        return vh;
    }

    @Override
   public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        // set the data in items
        final AppInfo appInfo = (AppInfo) AppItem.get(position);
        holder.name.setText(String.valueOf(appInfo.getLabel()));
        /*holder.image.setImageResource(appInfo.getAppicon());*/
        holder.image.setImageDrawable(appInfo.getAppicon());
        // implement setOnClickListener event on item view.
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent launchIntent = context.getPackageManager().getLaunchIntentForPackage(String.valueOf(appInfo.getPackageName()));
                context.startActivity(launchIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return AppItem.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        // init the item view's
        TextView name;
        ImageView image;

        public MyViewHolder(View itemView) {
            super(itemView);
            // get the reference of item view's
            name =  itemView.findViewById(R.id.textView_label);
            image = itemView.findViewById(R.id.imageView_app);
        }
    }
}

