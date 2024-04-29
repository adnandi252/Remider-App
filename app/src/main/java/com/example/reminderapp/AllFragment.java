package com.example.reminderapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class AllFragment extends Fragment {

    RecyclerView rv_all;
    FloatingActionButton add_button;
    MyDatabaseHelper myDB;
    ArrayList<String> id, subject, content, location, status;

    AllAdapter allAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all, container, false);
        rv_all = view.findViewById(R.id.rv_all);
        add_button = view.findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddActivity.class);
                startActivity(intent);
            }
        });

        myDB = new MyDatabaseHelper(getActivity());
        id = new ArrayList<>();
        subject = new ArrayList<>();
        content = new ArrayList<>();
        location = new ArrayList<>();
        status = new ArrayList<>();
        storeDataInArrays();

        allAdapter = new AllAdapter(getActivity(), id, subject, content, location, status);
        rv_all.setAdapter(allAdapter);
        rv_all.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        return view;

    }

    void storeDataInArrays() {
        Cursor cursor = myDB.readAllData();
        if(cursor.getCount() == 0) {
            Toast.makeText(getActivity(), "Tidak ada Data...", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                id.add(cursor.getString(0));
                subject.add(cursor.getString(1));
                content.add(cursor.getString(2));
                location.add(cursor.getString(3));
                status.add(cursor.getString(4));
            }
        }
    }
}