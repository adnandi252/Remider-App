package com.example.reminderapp;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class DoneFragment extends Fragment {

    RecyclerView rv_done;
    MyDatabaseHelper myDB;
    ArrayList<String> id, subject, content, location, status;
    DoneAdapter doneAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_done, container, false);
        rv_done = view.findViewById(R.id.rv_done);

        myDB = new MyDatabaseHelper(getActivity());
        id = new ArrayList<>();
        subject = new ArrayList<>();
        content = new ArrayList<>();
        location = new ArrayList<>();
        status = new ArrayList<>();

        storeDoneDataInArrays();

        doneAdapter = new DoneAdapter(getActivity(), id, subject, content, location, status);
        rv_done.setAdapter(doneAdapter);
        rv_done.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        return view;
    }

    void storeDoneDataInArrays() {
        Cursor cursor = myDB.readDoneData();
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