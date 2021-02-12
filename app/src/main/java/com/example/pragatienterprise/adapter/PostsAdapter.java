package com.example.pragatienterprise.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.pragatienterprise.R;
import com.example.pragatienterprise.User;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PostsAdapter extends RecyclerView.Adapter<com.example.pragatienterprise.adapter.PostsAdapter.MyAdapterViewHolder> {

    public Context context;
    public ArrayList<User> postsArrayList;

    public PostsAdapter(Context context, ArrayList<User> postsArrayList) {
        this.context = context;
        this.postsArrayList = postsArrayList;
    }

    @NonNull
    @Override
    public MyAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_layout, parent, false);

        return new MyAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapterViewHolder holder, int position) {
        User posts = postsArrayList.get(position);
        holder.textView.setText(posts.getName());
      holder.textView1.setText((posts.getContent()));
    }

    @Override
    public int getItemCount() {
        return postsArrayList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class MyAdapterViewHolder extends RecyclerView.ViewHolder {

        public TextView textView;
        public TextView textView1;

        public MyAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.custom_post_textView);
            textView1 = itemView.findViewById(R.id.contentlist);

        }
    }
}
