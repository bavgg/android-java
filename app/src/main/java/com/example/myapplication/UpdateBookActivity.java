package com.example.myapplication;

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

public class UpdateBookActivity extends AppCompatActivity {

    private EditText titleText, authorText, pagesText;
    private Button updateBookButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_update_book);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        titleText = findViewById(R.id.titleText);
        authorText = findViewById(R.id.authorText);
        pagesText = findViewById(R.id.pagesText);
        updateBookButton = findViewById(R.id.updateBookButton);

        if (getIntent().hasExtra("title") &&
                        getIntent().hasExtra("author") &&
                        getIntent().hasExtra("pages")) {

            String title = getIntent().getStringExtra("title");
            String author = getIntent().getStringExtra("author");
            String pages = getIntent().getStringExtra("pages");

            titleText.setText(title);
            authorText.setText(author);
            pagesText.setText(pages);

        } else {
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }

        updateBookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

}