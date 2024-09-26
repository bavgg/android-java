package com.example.myapplication.data;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.example.myapplication.data.local.DatabaseHelper;
import com.example.myapplication.data.model.Book;

import java.util.ArrayList;
import java.util.List;

public class BookRepository {
    private final DatabaseHelper dbHelper;
    private final Context context; // Add context for showing Toast messages

    public BookRepository(Context context) {
        this.context = context;
        dbHelper = new DatabaseHelper(context);
    }

    public void addBook(Book book) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("title", book.getTitle());
        values.put("author", book.getAuthor());
        values.put("pages", book.getPages());

        long result = db.insert("books", null, values);
        db.close();

        if (result != -1) {
            Toast.makeText(context, "Book added successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Failed to add book", Toast.LENGTH_SHORT).show();
        }
    }

    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query("books", null, null, null, null, null, null);

        while (cursor.moveToNext()) {
            @SuppressLint("Range") String title = cursor.getString(cursor.getColumnIndex("title"));
            @SuppressLint("Range") String author = cursor.getString(cursor.getColumnIndex("author"));
            @SuppressLint("Range") String pages = cursor.getString(cursor.getColumnIndex("pages"));
            books.add(new Book(title, author, pages));
        }
        cursor.close();
        db.close();
        return books;
    }

    // Update an existing book
    public void updateBook(Book book) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("title", book.getTitle());
        values.put("author", book.getAuthor());
        values.put("pages", book.getPages());

        int result = db.update("books", values, "title = ?", new String[]{book.getTitle()});
        db.close();

        if (result > 0) {
            Toast.makeText(context, "Book updated successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Failed to update book", Toast.LENGTH_SHORT).show();
        }
    }

    // Delete a book from the database
    public void deleteBook(String title) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        int result = db.delete("books", "title = ?", new String[]{title});
        db.close();

        if (result > 0) {
            Toast.makeText(context, "Book deleted successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Failed to delete book", Toast.LENGTH_SHORT).show();
        }
    }
}
