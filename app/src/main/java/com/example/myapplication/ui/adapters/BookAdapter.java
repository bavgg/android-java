package com.example.myapplication.ui.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.UpdateBookActivity;
import com.example.myapplication.data.model.Book;
import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {

    private List<Book> books;
    private ActivityResultLauncher<Intent> launcher;

    public BookAdapter(List<Book> books, ActivityResultLauncher<Intent> launcher) {
        this.books = books;
        this.launcher = launcher;
    }

    public void updateBooks(List<Book> newBooks) {
        this.books = newBooks;
        notifyDataSetChanged(); // Notify RecyclerView of data change
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_book, parent, false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        Book book = books.get(position);
        holder.titleTextView.setText(book.getTitle());
        holder.authorTextView.setText(book.getAuthor());
        holder.pagesTextView.setText(book.getPages());
        holder.bookCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), UpdateBookActivity.class);
                intent.putExtra("title", String.valueOf(book.getTitle()));
                intent.putExtra("author", String.valueOf(book.getAuthor()));
                intent.putExtra("pages", String.valueOf(book.getPages()));
                launcher.launch(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    static class BookViewHolder extends RecyclerView.ViewHolder {

        TextView titleTextView, authorTextView, pagesTextView;
        ConstraintLayout bookCard;


        public BookViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.bookTitle);
            authorTextView = itemView.findViewById(R.id.bookAuthor);
            pagesTextView = itemView.findViewById(R.id.bookPages);
            bookCard = itemView.findViewById(R.id.bookCard);
        }
    }
}

