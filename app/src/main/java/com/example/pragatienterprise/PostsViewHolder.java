package com.example.pragatienterprise;

import android.view.View;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PostsViewHolder extends RecyclerView.ViewHolder {
    public TextView textView;
    public TextView textView1;

    public PostsViewHolder(@NonNull View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.custom_post_textView);
        textView1 = itemView.findViewById(R.id.contentlist);
    }
}
