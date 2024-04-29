package com.example.reminderapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AllAdapter extends RecyclerView.Adapter<AllAdapter.MyViewHolder> {
    private Context context;
    private ArrayList id, subject, content, location, status;

    //Constructor
    AllAdapter(Context context, ArrayList id, ArrayList subject, ArrayList content, ArrayList location, ArrayList status) {
        this.context = context;
        this.id = id;
        this.subject = subject;
        this.content = content;
        this.location = location;
        this.status = status;
    }

    @NonNull
    @Override
    public AllAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_note, parent, false);

        return new MyViewHolder(view);
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull AllAdapter.MyViewHolder holder,  int position) {
        holder.textsubject.setText(String.valueOf(subject.get(position)));
        holder.item_note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("id", String.valueOf(id.get(position)));
                intent.putExtra("subject", String.valueOf(subject.get(position)));
                intent.putExtra("content", String.valueOf(content.get(position)));
                intent.putExtra("location", String.valueOf(location.get(position)));
                intent.putExtra("status", String.valueOf(status.get(position)));
                context.startActivity(intent);
            }
        });

        holder.checkdone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(context);
                myDB.updateDone(String.valueOf(id.get(position)));
                refreshMainActivity(); // untuk refresh/restart activity main
            }
        });
    }

    @Override
    public int getItemCount() {
        return id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
            TextView textsubject;
            LinearLayout item_note;
            CheckBox checkdone;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textsubject = itemView.findViewById(R.id.textsubject);
            item_note = itemView.findViewById(R.id.item_note);
            checkdone = itemView.findViewById(R.id.checkdone);
        }

        public void refreshMainActivity() {
            Intent intent = new Intent(context, MainActivity.class);
            context.startActivity(intent);
            ((Activity) context).finish();
        }
    }

    void refreshMainActivity() {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
        ((Activity) context).finish();
    }

}
