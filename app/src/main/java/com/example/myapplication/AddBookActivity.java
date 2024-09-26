package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.data.BookRepository;
import com.example.myapplication.data.local.DatabaseHelper;
import com.example.myapplication.data.model.Book;

public class AddBookActivity extends AppCompatActivity {
    EditText titleText, authorText, pagesText;
    Button addBookButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_book);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        titleText = findViewById(R.id.titleText);
        authorText = findViewById(R.id.authorText);
        pagesText = findViewById(R.id.pagesText);
        addBookButton = findViewById(R.id.addBookButton);

        addBookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BookRepository bookRepository = new BookRepository(AddBookActivity.this);
                Book book = new Book(titleText.getText().toString(), authorText.getText().toString(), pagesText.getText().toString());
                bookRepository.addBook(book);
                setResult(RESULT_OK);
                finish();
            }
        });
    }
}