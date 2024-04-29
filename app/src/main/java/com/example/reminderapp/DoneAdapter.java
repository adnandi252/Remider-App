package com.example.reminderapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DoneAdapter extends RecyclerView.Adapter<DoneAdapter.MyViewHolder>{

    private Context context;
    private ArrayList id, subject, content, location, status;


    //constructor
    DoneAdapter(Context context, ArrayList id, ArrayList subject, ArrayList content, ArrayList location, ArrayList status) {
        this.context = context;
        this.id = id;
        this.subject = subject;
        this.content = content;
        this.location = location;
        this.status = status;
    }

    @NonNull
    @Override
    public DoneAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_done, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DoneAdapter.MyViewHolder holder, int position) {
        holder.textsubject_done.setText(String.valueOf(subject.get(position)));
    }

    @Override
    public int getItemCount() {
        return id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textsubject_done;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textsubject_done = itemView.findViewById(R.id.textsubject_done);
        }
    }
}
