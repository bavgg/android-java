package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.data.BookRepository;
import com.example.myapplication.data.model.Book;
import com.example.myapplication.ui.adapters.BookAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private BookAdapter bookAdapter;
    private BookRepository bookRepository;
    private RecyclerView recyclerView;
    private FloatingActionButton addButton;
    private ActivityResultLauncher<Intent> launcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        launcher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK) {
                        loadBooks();
                    }
                }
        );

        bookAdapter = new BookAdapter(new ArrayList<>(), launcher);
        recyclerView.setAdapter(bookAdapter);

        loadBooks();

        findViewById(R.id.addButton).setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddBookActivity.class);
            launcher.launch(intent);
        });
    }

    private void loadBooks() {
        bookRepository = new BookRepository(this);
        List<Book> books = bookRepository.getAllBooks();

        bookAdapter.updateBooks(books);

    }
}