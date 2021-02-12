package com.example.pragatienterprise;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.ArrayList;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;
/*
public class myadapter extends RecyclerView.Adapter<myadapter.myViewHolder> implements Filterable
{
ArrayList<User>data;
ArrayList<User> backup;
Context context;

static class  myViewHolder extends RecyclerView.ViewHolder{
TextView name, email, city;



    public myViewHolder(@NonNull View itemView) {
        super(itemView);
        name=(TextView)itemView.findViewById(R.id.user_fullname);
        city=(TextView)itemView.findViewById(R.id.user_city);
        email=(TextView)itemView.findViewById(R.id.user_emailId);
    }


}


public  myadapter(ArrayList<User> data,Context context)
{
    this.data=data;
    this.context=context;
    backup =new ArrayList<>(data);

}
@NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
{
    LayoutInflater inflater= LayoutInflater.from( parent.getContext());
    View view=inflater.inflate(R.layout.list_layout,parent,false);
    return new myViewHolder(view);

}

    @Override
    public void onBindViewHolder(@NonNull final  myViewHolder holder, int position) {
        final  User temp=data.get(position);

        holder.name.setText(data.get(position).getName());
        holder.email.setText(data.get(position).getEmail());
         holder.city.setText(data.get(position).getName());
 }




    @Override
    public int getItemCount() {
        return 0;
    }


    @Override
    public Filter getFilter() {
        return null;
    }
    Filter filter=new Filter() {
        @Override
        // background thread
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<User> filtereddata = new ArrayList<>();
            if (constraint.toString().isEmpty())
                filtereddata.addAll((backup));
            else {
for(User obj :backup)
{
    if (obj.getName().toString().toLowerCase().contains(constraint.toString().toLowerCase()))
        filtereddata.add(obj);
}
            }
            FilterResults results=new FilterResults();
            results.values=filtereddata;
            return results;

        }


        @Override
        //main ui thread
        protected void publishResults(CharSequence constraint, FilterResults results) {
data.clear();
data.addAll((ArrayList<User>)results.values);
notifyDataSetChanged();
        }
    };
*/


