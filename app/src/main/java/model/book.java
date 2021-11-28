package model;

import java.util.ArrayList;

public class book {
    private int id;
    private String title;
    private String author;
    private ArrayList<String> genre;
    private String year;
    private float price;
    private String description;
    private int quantity;

    public book(int id, String title, String author, ArrayList<String> genre, String year, float price, String description, int quantity) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.year = year;
        this.price = price;
        this.description = description;
        this.quantity = quantity;
    }

    public book(String title, String author, ArrayList<String> genre, String year, float price, String description, int quantity) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.year = year;
        this.price = price;
        this.description = description;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public ArrayList<String> getGenre() {
        return genre;
    }

    public void setGenre(ArrayList<String> genre) {
        this.genre = genre;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
