package com.example.reminderapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddActivity extends AppCompatActivity {
    EditText subject_input, content_input, location_input;
    Button add_batal, add_simpan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        subject_input = findViewById(R.id.subject_input);
        content_input = findViewById(R.id.content_input);
        location_input = findViewById(R.id.location_input);
        add_batal = findViewById(R.id.add_batal);
        add_simpan = findViewById(R.id.add_simpan);

        add_simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!subject_input.getText().toString().isEmpty() ||
                        !content_input.getText().toString().isEmpty() ||
                        !location_input.getText().toString().isEmpty()) {
                    MyDatabaseHelper myDB = new MyDatabaseHelper(AddActivity.this);
                    myDB.createNote(subject_input.getText().toString().trim(),
                            content_input.getText().toString(),
                            location_input.getText().toString());
                    Intent intent = new Intent(AddActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getBaseContext(), "Isi semua inputan", Toast.LENGTH_SHORT).show();
                }
            }
        });

        add_batal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}