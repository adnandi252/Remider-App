package com.example.reminderapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DetailActivity extends AppCompatActivity {
    EditText subject_update, content_update, location_update;
    TextView status_text;
    Button kembali, update_simpan;
    String id, subject, content, location, status;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        subject_update = findViewById(R.id.subject_update);
        content_update = findViewById(R.id.content_update);
        location_update = findViewById(R.id.location_update);
        status_text = findViewById(R.id.status_text);
        kembali = findViewById(R.id.kembali);
        update_simpan = findViewById(R.id.update_simpan);

        getIntentData();

        update_simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = getIntent().getStringExtra("id");
                subject = subject_update.getText().toString();
                content = content_update.getText().toString();
                location = content_update.getText().toString();
                MyDatabaseHelper myDB = new MyDatabaseHelper(DetailActivity.this);
                myDB.updateData(id, subject, content, location);
                Intent intent = new Intent(DetailActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    void getIntentData() {
        if(getIntent().hasExtra("subject")
                && getIntent().hasExtra("content")
                && getIntent().hasExtra("location") && getIntent().hasExtra("status")) {
            subject_update.setText(getIntent().getStringExtra("subject"));
            content_update.setText(getIntent().getStringExtra("content"));
            location_update.setText(getIntent().getStringExtra("location"));
            status_text.setText(getIntent().getStringExtra("status"));
        } else {
            Toast.makeText(this, "No data..", Toast.LENGTH_SHORT).show();
        }
    }
}
