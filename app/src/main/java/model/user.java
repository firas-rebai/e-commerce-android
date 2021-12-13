package model;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class user {
    private int id;
    private String username;
    private String password;
    private String email;
    //private boolean isAdmin;
    private ArrayList<bookOrder> books;

    public user() {
    }

    public user(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    //public boolean isAdmin() {return isAdmin;}

    //public void setAdmin(boolean admin) {isAdmin = admin;}

    public ArrayList<bookOrder> getBooks() {
        return books;
    }

    public void setBooks(ArrayList<bookOrder> books) {
        this.books = books;
    }

    public user(int id, String username, String password, String email,  ArrayList<bookOrder> books) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.books = books;
    }

    public user(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }
}
