package com.example.myapplication.data.model;

public class Book {
    private String title;
    private String author;
    private String pages;

    public Book(String title, String author, String pages) {
        this.title = title;
        this.author = author;
        this.pages = pages;
    }

    public String getTitle() {
        return title;
    }
    public String getAuthor() {
        return author;
    }
    public String getPages() {
        return pages;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public void setPages(String pages) {
        this.pages = pages;
    }


}
